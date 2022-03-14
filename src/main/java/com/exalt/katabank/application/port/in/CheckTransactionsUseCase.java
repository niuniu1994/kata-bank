package com.exalt.katabank.application.port.in;

import com.exalt.katabank.domain.Transaction;

import java.util.Set;

public interface CheckTransactionsUseCase {

    /**
     * Check transactions detail of the account given
     * @param accountId
     * @return
     */
    Set<Transaction> checkTransactions(Long accountId);
}
