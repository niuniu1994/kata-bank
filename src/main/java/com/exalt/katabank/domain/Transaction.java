package com.exalt.katabank.domain;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Withdraw or deposit money
 *
 * @author kainingxin
 */
public record Transaction(@NonNull LocalDateTime dateTime, @NonNull BigDecimal amount,
                          @NonNull TransactionType transactionType,@NonNull BigDecimal balance) {
}
