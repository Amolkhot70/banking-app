package com.ak.service.Impl;

import com.ak.Exception.NotFoundException;
import com.ak.dto.ExpenseDto;
import com.ak.entity.Category;
import com.ak.entity.Expense;
import com.ak.mapper.ExpenseMapper;
import com.ak.repository.CategoryRepository;
import com.ak.repository.ExpenseRepository;
import com.ak.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {


    public static final String NO_EXPENSE_FOUND = "Expenses not found with id : ";
    public static final String NO_CATEGORY_FOUND= "Category not found with id : ";
    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;
    private ObjectMapper objectMapper;

    private RedisService redisService;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Expense expense = ExpenseMapper.mapToExpense(expenseDto);
        Expense savedExpense = this.expenseRepository.save(expense);
       return ExpenseMapper.mapToExpenseDto(savedExpense);

    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {

        String cacheKey = String.valueOf(expenseId);

        ExpenseDto expenseDto = this.redisService.get(cacheKey, ExpenseDto.class);
        if (expenseDto != null) {
            System.out.println("Cache hit - Fetched from Redis: " + expenseDto);
            return expenseDto;
        }

        // Fetch from database if not found in Redis
        Expense expense = this.expenseRepository.findById(expenseId)
                .orElseThrow(() -> new NotFoundException(NO_EXPENSE_FOUND + " " + expenseId));

        ExpenseDto newExpenseDto = ExpenseMapper.mapToExpenseDto(expense);

        // Store the newly fetched expense in Redis
        this.redisService.set(cacheKey, newExpenseDto, 300L);
        System.out.println("Cache miss - Stored in Redis: " + newExpenseDto);

        return newExpenseDto;
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
       return this.expenseRepository.findAll().stream()
                .map(ExpenseMapper::mapToExpenseDto).collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {
        Expense expense = this.expenseRepository.findById(expenseId)
                .orElseThrow(() -> new NotFoundException(
                        NO_EXPENSE_FOUND + " " + expenseId));

        expense.setAmount(expenseDto.getAmount());
        expense.setExpenseDate(expenseDto.getExpenseDate());
        if(expenseDto.getCategoryDto() != null){
            Category category = this.categoryRepository.findById(expenseDto.getCategoryDto().getId())
                    .orElseThrow(() -> new NotFoundException(
                            NO_CATEGORY_FOUND + " " + expenseDto.getCategoryDto().getId()));
            expense.setCategory(category);
        }
        Expense savedExpense = this.expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(savedExpense);

    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense = this.expenseRepository.findById(expenseId)
                .orElseThrow(() -> new NotFoundException(
                        NO_EXPENSE_FOUND + " " + expenseId));

        this.expenseRepository.delete(expense);
    }
}
