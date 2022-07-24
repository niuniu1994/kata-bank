package com.exalt.domain.model;

import lombok.NonNull;

import java.time.LocalDateTime;

/**
 * Withdraw or deposit money
 *
 * @author kainingxin
 */
public record Transaction(Long transactionId, @NonNull LocalDateTime dateTime, @NonNull Money amount,
                          @NonNull TransactionType transactionType, @NonNull Money balance) {
}
