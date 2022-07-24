package com.exalt.domain.port.driving;

import com.exalt.domain.model.Money;

/**
 * @author kainingxin
 */
public interface DepositMoneyUseCase {

    /**
     * deposit an amount of money in to given account
     * @param accountId
     * @param money
     * @return
     */
    boolean depositMoney(Long accountId, Money money);

}
