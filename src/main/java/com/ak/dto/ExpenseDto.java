package com.ak.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(
        description = "Expense DTO (Data Transfer Object) to transfer data between client and server"
)
public class ExpenseDto implements Serializable {

    @Schema(description = "Expense Id")
    private Long id;

    @Schema(description = "Expense amount")
    private BigDecimal amount;

    @Schema(description = "Expense date")
    private LocalDate expenseDate;

    @Schema(description = "Expense category")
    private CategoryDto categoryDto;

    // Default constructor for Jackson deserialization
    public ExpenseDto() {}

    // Constructor
    public ExpenseDto(Long id, BigDecimal amount, LocalDate expenseDate, CategoryDto categoryDto) {
        this.id = id;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.categoryDto = categoryDto;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    // toString method for printing object
    @Override
    public String toString() {
        return "ExpenseDto{" +
                "id=" + id +
                ", amount=" + amount +
                ", expenseDate=" + expenseDate +
                ", categoryDto=" + categoryDto +
                '}';
    }

    // Optional: Equals and HashCode if you need them
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpenseDto that = (ExpenseDto) o;

        if (!id.equals(that.id)) return false;
        if (!amount.equals(that.amount)) return false;
        if (!expenseDate.equals(that.expenseDate)) return false;
        return categoryDto.equals(that.categoryDto);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + expenseDate.hashCode();
        result = 31 * result + categoryDto.hashCode();
        return result;
    }


}
