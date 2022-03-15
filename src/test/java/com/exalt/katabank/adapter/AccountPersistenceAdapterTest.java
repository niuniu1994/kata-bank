package com.exalt.katabank.adapter;

import com.exalt.katabank.adapter.port.out.AccountPersistenceAdapter;
import com.exalt.katabank.adapter.port.out.entity.BankAccountEntity;
import com.exalt.katabank.adapter.port.out.entity.TransactionEntity;
import com.exalt.katabank.adapter.port.out.repositry.BankAccountEntityRepository;
import static org.junit.jupiter.api.Assertions.*;

import com.exalt.katabank.domain.BankAccount;
import com.exalt.katabank.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

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
