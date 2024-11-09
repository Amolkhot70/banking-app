package com.ak.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
@Schema(
        description = "Expense DTO (Data transfer Object) to transfer data between client and server"
)
public record ExpenseDto(
        @Schema(description = "Expense Id") Long id,
        @Schema(description = "Expense amount") BigDecimal amount,
        @Schema(description = "Expense date") LocalDate expenseDate,
        @Schema(description = "Expense category") CategoryDto categoryDto) {
}
