package com.exalt.katabank.application.port.out;

import com.exalt.katabank.domain.BankAccount;
import com.exalt.katabank.domain.Money;
import com.exalt.katabank.domain.Transaction;

import java.util.Set;

/**
 * @author kainingxin
 */
public interface AccountPersistencePort {

    BankAccount loadAccount(Long accountId);

    void updateAccount(BankAccount bankAccount);

}
