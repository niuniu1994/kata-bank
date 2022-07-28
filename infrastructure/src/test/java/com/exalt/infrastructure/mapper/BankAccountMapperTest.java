package com.exalt.infrastructure.mapper;

import com.exalt.domain.model.BankAccount;
import com.exalt.infrastructure.db.mapper.BankAccountMapper;
import com.exalt.infrastructure.db.model.BankAccountEntity;
import com.exalt.infrastructure.db.model.TransactionEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountMapperTest {

    @Test
    void when_bankAccountEntity2BankAccount_then_should_return_bankAccount(){
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountId(1L);
        bankAccountEntity.setBalance("20.00");
        TransactionEntity transactionEntity = new TransactionEntity(1L, LocalDateTime.now(),"20.00","30.00","DEPOSIT",null);
        bankAccountEntity.addTransaction(transactionEntity);

        BankAccount bankAccount = BankAccountMapper.bankAccountEntity2BankAccount(bankAccountEntity);

        assertEquals(bankAccount.getAccountId(),bankAccountEntity.getAccountId());
        assertEquals(bankAccount.getTransactions().size(),bankAccountEntity.getTransactionEntitySet().size());


    }
}
