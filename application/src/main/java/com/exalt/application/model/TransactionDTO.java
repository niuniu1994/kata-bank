package com.exalt.application.model;


import java.time.LocalDateTime;

public record TransactionDTO(LocalDateTime operationTime,String amount,
                             String operationType, String balanceAfterOperation) {
}
