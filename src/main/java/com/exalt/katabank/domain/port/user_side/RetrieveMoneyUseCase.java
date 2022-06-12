package com.exalt.katabank.domain.port.user_side;

import com.exalt.katabank.domain.model.Money;

public interface RetrieveMoneyUseCase {

    /**
     * Retrieve an amount of money from given account
     * @param accountId
     * @param money
     * @return
     */
    boolean retrieveMoney(Long accountId, Money money);
}
