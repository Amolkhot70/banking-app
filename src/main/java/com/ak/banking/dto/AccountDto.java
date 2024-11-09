package com.ak.banking.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record AccountDto(Long id,
                         String accountHolderName,
                         double balance) {
}
