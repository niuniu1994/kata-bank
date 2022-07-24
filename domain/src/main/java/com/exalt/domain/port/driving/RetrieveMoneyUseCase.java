package com.exalt.domain.port.driving;

import com.exalt.domain.model.Money;

public interface RetrieveMoneyUseCase {

    /**
     * Retrieve an amount of money from given account
     * @param accountId
     * @param money
     * @return
     */
    boolean retrieveMoney(Long accountId, Money money);
}
