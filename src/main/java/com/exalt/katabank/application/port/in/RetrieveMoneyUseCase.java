package com.exalt.katabank.application.port.in;

import com.exalt.katabank.domain.Money;

public interface RetrieveMoneyUseCase {

    /**
     * Retrieve an amount of money from given account
     * @param accountId
     * @param money
     * @return
     */
    boolean retrieveMoney(Long accountId, Money money);
}
