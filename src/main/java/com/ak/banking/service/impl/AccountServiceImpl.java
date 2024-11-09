package com.ak.banking.service.impl;

import com.ak.banking.dto.AccountDto;
import com.ak.banking.dto.TransferFundTto;
import com.ak.banking.entity.Account;
import com.ak.banking.entity.Transaction;
import com.ak.banking.exception.AccountException;
import com.ak.banking.mapper.AccountMapper;
import com.ak.banking.repository.AccountRepository;
import com.ak.banking.repository.TransactionRepository;
import com.ak.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    public static final String DEPOSIT = "DEPOSIT";
    public static final String WITHDRAW = "WITHDRAW";
    public static final String TRANSFER = "TRANSFER";

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository,
                              TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = this.accountRepository.findById(id).orElseThrow(
                () -> new AccountException("Account does not exist")
        );
        AccountDto accountDto = AccountMapper.mapToAccountDto(account);
        return accountDto;
    }

    @Override
    public AccountDto deposite(Long id, double amount) {
        Account account = this.accountRepository.findById(id).orElseThrow(
                () -> new AccountException("Account does not exist")
        );
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = this.accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());
        this.transactionRepository.save(transaction);

        AccountDto accountDto = AccountMapper.mapToAccountDto(savedAccount);
        return accountDto;
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = this.accountRepository.findById(id).orElseThrow(
                () -> new AccountException("Account does not exist")
        );

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = this.accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(WITHDRAW);
        transaction.setTimestamp(LocalDateTime.now());
        this.transactionRepository.save(transaction);

        AccountDto accountDto = AccountMapper.mapToAccountDto(savedAccount);
        return accountDto;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map((account) ->
                        AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {

        Account account = this.accountRepository.findById(id).orElseThrow(
                () -> new AccountException("Account does not exist")
        );

        this.accountRepository.deleteById(id);
    }

    @Override
    public void transferFunds(TransferFundTto transferFundTto) {

//        Retrieve the account from which we send the amount
        Account fromAccount = this.accountRepository.findById(transferFundTto.fromAccountId()).orElseThrow(
                () -> new AccountException("Account does not exist")
        );


//        Retrieve the account to which we send the amount
        Account toAccount = this.accountRepository.findById(transferFundTto.toAccountId()).orElseThrow(
                () -> new AccountException("Account does not exist")
        );

        if(fromAccount.getBalance() < transferFundTto.amount()){
            throw new RuntimeException("Insufficient amount");
        }

//        Debit the amount from fromAccount object
        fromAccount.setBalance(fromAccount.getBalance()- transferFundTto.amount());

//        credit the amount to toAccount object
        toAccount.setBalance(toAccount.getBalance() + transferFundTto.amount());

//        Save updated objects to DB using repository save
        this.accountRepository.save(fromAccount);
        this.accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setAccountId(transferFundTto.fromAccountId());
        transaction.setAmount(transferFundTto.amount());
        transaction.setTransactionType(TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());
        this.transactionRepository.save(transaction);

    }
}
