package com.ak.banking.service;

import com.ak.banking.dto.AccountDto;
import com.ak.banking.dto.TransferFundTto;
import com.ak.banking.entity.Account;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposite(Long id,double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

    void transferFunds(TransferFundTto transferFundTto);
}
