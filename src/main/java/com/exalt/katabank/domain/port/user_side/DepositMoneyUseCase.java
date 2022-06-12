package com.exalt.katabank.domain.port.user_side;

import com.exalt.katabank.domain.model.Money;

public interface DepositMoneyUseCase {

    /**
     * deposit an amount of money in to given account
     * @param accountId
     * @param money
     * @return
     */
    boolean depositMoney(Long accountId, Money money);

}
