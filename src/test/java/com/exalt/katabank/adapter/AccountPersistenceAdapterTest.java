package com.exalt.katabank.adapter;

import com.exalt.katabank.domain.model.BankAccount;
import com.exalt.katabank.domain.model.Money;
import com.exalt.katabank.infrastructure.db.adapter.AccountPersistenceAdapter;
import com.exalt.katabank.infrastructure.db.model.BankAccountEntity;
import com.exalt.katabank.infrastructure.db.model.TransactionEntity;
import com.exalt.katabank.infrastructure.db.repositry.BankAccountEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class AccountPersistenceAdapterTest {

    @Autowired
    private BankAccountEntityRepository bankAccountEntityRepository;

    private AccountPersistenceAdapter adapter;

    @BeforeEach
    void setup(){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setBalance("100");
        transactionEntity.setDateTime(LocalDateTime.now());
        transactionEntity.setAmount("10");
        transactionEntity.setType("DEPOSIT");
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setBalance("100");
        bankAccountEntity.addTransaction(transactionEntity);
        bankAccountEntityRepository.saveAndFlush(bankAccountEntity);
        adapter = new AccountPersistenceAdapter(bankAccountEntityRepository);
    }

    @Test
    void  when_bankAccount_id_correct_then_return_account(){
        BankAccount bankAccount = adapter.loadAccount(1L);
        assertNotNull(bankAccount);
        assertNotNull(bankAccount.getTransactions());
    }

    @Test
    void  when_bankAccount_correct_correct_then_update_success(){
        BankAccount bankAccount = new BankAccount(null,new Money("100"),null);
        assertTrue(adapter.updateAccount(bankAccount));

    }
}
