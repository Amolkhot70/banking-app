package com.ak.banking.dto;

public record TransferFundTto(Long fromAccountId,
                              Long toAccountId,
                              Double amount) {
}
