package com.exalt.domain.port.driven;

import com.exalt.domain.model.BankAccount;


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
