package com.exalt.katabank.adapter.port.out;

import com.exalt.katabank.adapter.port.out.entity.BankAccountEntity;
import com.exalt.katabank.adapter.port.out.mapper.BankAccountMapper;
import com.exalt.katabank.adapter.port.out.repositry.BankAccountEntityRepository;
import com.exalt.katabank.application.port.out.AccountPersistencePort;
import com.exalt.katabank.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final BankAccountEntityRepository bankAccountEntityRepository;

    @Autowired
    public AccountPersistenceAdapter(BankAccountEntityRepository bankAccountEntityRepository) {
        this.bankAccountEntityRepository = bankAccountEntityRepository;
    }

    @Override
    public BankAccount loadAccount(Long accountId) {
        BankAccountEntity bankAccountEntity = bankAccountEntityRepository.findById(accountId).orElseThrow(EntityNotFoundException::new);
        return BankAccountMapper.bankAccountEntity2BankAccount(bankAccountEntity);
    }

    @Override
    public boolean updateAccount(BankAccount bankAccount) {
        BankAccountEntity bankAccountEntity = BankAccountMapper.bankAccount2BankAccountEntity(bankAccount);
        bankAccountEntity = bankAccountEntityRepository.saveAndFlush(bankAccountEntity);
        return bankAccountEntity.getAccountId() != null;
    }
}
