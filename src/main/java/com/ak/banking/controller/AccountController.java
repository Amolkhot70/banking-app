package com.ak.banking.controller;

import com.ak.banking.dto.AccountDto;
import com.ak.banking.dto.TransferFundTto;
import com.ak.banking.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(
        name = "CRUD REST APIS for Banking-App",
        description = "CRUD REST APIS for Banking-Application" +
                "CREATE Account,UPDATE Account ,DELETE Account,TRANSFER Amount"
)
@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    //    Add account rest API
    @Operation(
            summary = "CREATE an bank account",
            description = "This API is used to create an account for new user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status code 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED); // 201
    }

    //   Get Account Rest API
    @Operation(
            summary = "Find Account details by accountId",
            description = "This API is used to fetch an account details of individual user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code 200 OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable(value = "id") Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto); // This internally calls HttpStatus.ok which is 200
    }

    //    Deposite Amount
    @Operation(
            summary = "Deposit amount to an account",
            description = "This API is used to deposit amount to an user account "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code 200 OK"
    )
    @PutMapping("/{id}/deposite")
    public ResponseEntity<AccountDto> deposite(@PathVariable(value = "id") Long id,
                                              @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto deposite = this.accountService.deposite(id, amount);
        return ResponseEntity.ok(deposite);
    }

//    Withdraw Amount
@Operation(
        summary = "Withdraw amount from an account",
        description = "This API is used to Withdraw amount from an user account "
)
@ApiResponse(
        responseCode = "200",
        description = "Http status code 200 OK"
)
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable(value = "id") Long id,
                                               @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto withdraw = this.accountService.withdraw(id, amount);
        return ResponseEntity.ok(withdraw);

    }

//    Get All accounts
@Operation(
        summary = "fetch all accounts",
        description = "This API is used to fetch all accounts within a bank "
)
@ApiResponse(
        responseCode = "200",
        description = "Http status code 200 OK"
)
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

//   Delete Account
@Operation(
        summary = "Delete an account",
        description = "This API is used to delete an customer account "
)
@ApiResponse(
        responseCode = "200",
        description = "Http status code 200 OK"
)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable(value = "id") Long id){
        this.accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }

//    Transfer funds
@Operation(
        summary = "Transfer funds ",
        description = "This API is used to transfer funds between two accounts "
)
@ApiResponse(
        responseCode = "200",
        description = "Http status code 200 OK"
)
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFund(@RequestBody TransferFundTto transferFundTto){
        accountService.transferFunds(transferFundTto);
        return ResponseEntity.ok("Transfer successfully");
    }

    // Demo Endpoint to Test the Interceptor
    @GetMapping("/greetings")
    public String getGreetings() {
        return "Hello, Welcome to Banking App";
    }

}
