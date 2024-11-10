package com.ak.controller;

import com.ak.dto.ExpenseDto;
import com.ak.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Expense Resource",
        description = "CRUD REST APIs for Expense Resource"+
                "CREATE expense,UPDATE expense ,DELETE expense,READ expense"
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    public static final String DELETE_SUCCESSFUL = "Expenses deleted successfully";
    private ExpenseService expenseService;

    @Operation(
            summary = "CREATE Expenses REST API",
            description = "Save Expenses REST API into Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status code 200 CREATED"
    )
    @PostMapping
    public ResponseEntity<ExpenseDto> saveExpenses(@RequestBody ExpenseDto expenseDto){
        ExpenseDto createdExpense = this.expenseService.createExpense(expenseDto);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @Operation(
            summary = "GET Expenses REST API",
            description = "GET Expenses REST API from Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code 200 OK"
    )
    @GetMapping("{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable("id") Long expenseId){
        ExpenseDto expenseById = this.expenseService.getExpenseById(expenseId);
        return new ResponseEntity<>(expenseById,HttpStatus.OK);
    }

    @Operation(
            summary = "GET All Expenses REST API",
            description = "GET All Expenses REST API from Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(){
        List<ExpenseDto> allExpenses = this.expenseService.getAllExpenses();
        return new ResponseEntity<>(allExpenses,HttpStatus.OK);
    }

    @Operation(
            summary = "UPDATE Expenses REST API",
            description = "UPDATE Expenses details REST API into Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code 200 OK"
    )
    @PutMapping("{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable("id") Long expenseId,
                                                    @RequestBody ExpenseDto expenseDto){
        ExpenseDto expenseDto1 = this.expenseService.updateExpense(expenseId, expenseDto);
        return new ResponseEntity<>(expenseDto1,HttpStatus.OK);
    }

    @Operation(
            summary = "DELETE Expenses REST API",
            description = "DELETE Expenses REST API from Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code 200 OK"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable("id") Long expenseId){
        this.expenseService.deleteExpense(expenseId);
        return new ResponseEntity<>(DELETE_SUCCESSFUL,HttpStatus.OK);
    }
}
