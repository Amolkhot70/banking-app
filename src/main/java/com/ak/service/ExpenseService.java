package com.ak.service;

import com.ak.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    ExpenseDto createExpense(ExpenseDto expenseDto);
    ExpenseDto getExpenseById(Long expenseId);
    List<ExpenseDto> getAllExpenses();
    ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto);
    void deleteExpense(Long expenseId);
}
