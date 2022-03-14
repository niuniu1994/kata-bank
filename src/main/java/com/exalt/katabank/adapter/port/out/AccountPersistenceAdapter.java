package com.exalt.katabank.adapter.port.out;

import com.exalt.katabank.adapter.port.out.repositry.BankAccountEntityRepository;
import com.exalt.katabank.application.port.out.AccountPersistencePort;
import com.exalt.katabank.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final BankAccountEntityRepository bankAccountEntityRepository;

    @Autowired
    public AccountPersistenceAdapter(BankAccountEntityRepository bankAccountEntityRepository) {
        this.bankAccountEntityRepository = bankAccountEntityRepository;
    }

    @Override
    public BankAccount loadAccount(Long accountId) {
        return null;
    }

    @Override
    public void updateAccount(BankAccount bankAccount) {

    }
}
