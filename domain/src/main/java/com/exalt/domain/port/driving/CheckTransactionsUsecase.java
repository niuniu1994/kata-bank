package com.exalt.domain.port.driving;


import com.exalt.domain.model.Transaction;

import java.util.Set;

/**
 * @author kainingxin
 */
public interface CheckTransactionsUsecase {

    /**
     * Check transactions detail of the account given
     * @param accountId
     * @return
     */
    Set<Transaction> checkTransactions(Long accountId);
}
