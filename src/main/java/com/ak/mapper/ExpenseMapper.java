package com.ak.mapper;

import com.ak.dto.CategoryDto;
import com.ak.dto.ExpenseDto;
import com.ak.entity.Category;
import com.ak.entity.Expense;

public class ExpenseMapper {
    //    Map Expense to ExpenseDto
    public static ExpenseDto mapToExpenseDto(Expense expense) {
        return new ExpenseDto(
                expense.getId(),
                expense.getAmount(),
                expense.getExpenseDate(),
                new CategoryDto(
                        expense.getCategory().getId(),
                        expense.getCategory().getName()
                )
        );
    }

    //    Map ExpenseDto to Expense
    public static Expense mapToExpense(ExpenseDto expenseDto) {
//        Category category = new Category();
//        category.setId(expenseDto.id());

       return new Expense (
                expenseDto.getId(),
                expenseDto.getAmount(),
                expenseDto.getExpenseDate(),
                new Category(
                        expenseDto.getCategoryDto().getId(),
                        expenseDto.getCategoryDto().getName()
                )
        );

    }
}
