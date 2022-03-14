package com.exalt.katabank.application.port.out;

import com.exalt.katabank.domain.BankAccount;


/**
 * @author kainingxin
 */
public interface AccountPersistencePort {

    /**
     * Get bankAccount
     * @param accountId
     * @return
     */
    BankAccount loadAccount(Long accountId);

    /**
     * Save bankAccount
     * @param bankAccount
     */
    boolean updateAccount(BankAccount bankAccount);

}
