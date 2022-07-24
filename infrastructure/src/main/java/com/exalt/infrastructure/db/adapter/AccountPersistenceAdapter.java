package com.exalt.infrastructure.db.adapter;

import com.exalt.domain.model.BankAccount;
import com.exalt.domain.port.driven.AccountPersistencePort;
import com.exalt.infrastructure.db.mapper.BankAccountMapper;
import com.exalt.infrastructure.db.model.BankAccountEntity;
import com.exalt.infrastructure.db.repositry.BankAccountEntityRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

/**
 * @author kainingxin
 */
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
        bankAccountEntity = bankAccountEntityRepository.save(bankAccountEntity);
        return bankAccountEntity.getAccountId() != null;
    }
}
