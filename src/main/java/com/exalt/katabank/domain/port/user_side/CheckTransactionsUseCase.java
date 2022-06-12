package com.exalt.katabank.domain.port.user_side;

import com.exalt.katabank.domain.model.Transaction;

import java.util.Set;

public interface CheckTransactionsUseCase {

    /**
     * Check transactions detail of the account given
     * @param accountId
     * @return
     */
    Set<Transaction> checkTransactions(Long accountId);
}
