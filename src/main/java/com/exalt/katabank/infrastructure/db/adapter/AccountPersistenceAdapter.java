package com.exalt.katabank.infrastructure.db.adapter;

import com.exalt.katabank.domain.mapper.BankAccountMapper;
import com.exalt.katabank.domain.model.BankAccount;
import com.exalt.katabank.domain.port.server_side.AccountPersistencePort;
import com.exalt.katabank.infrastructure.db.model.BankAccountEntity;
import com.exalt.katabank.infrastructure.db.repositry.BankAccountEntityRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final BankAccountEntityRepository bankAccountEntityRepository;

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
