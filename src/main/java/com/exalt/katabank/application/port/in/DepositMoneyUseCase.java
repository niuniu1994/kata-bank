package com.exalt.katabank.application.port.in;

import com.exalt.katabank.domain.Money;

public interface DepositMoneyUseCase {

    /**
     * deposit an amount of money in to given account
     * @param accountId
     * @param money
     * @return
     */
    boolean depositMoney(Long accountId, Money money);

}
