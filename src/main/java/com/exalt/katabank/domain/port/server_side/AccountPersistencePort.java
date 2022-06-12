package com.exalt.katabank.domain.port.server_side;

import com.exalt.katabank.domain.model.BankAccount;


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
