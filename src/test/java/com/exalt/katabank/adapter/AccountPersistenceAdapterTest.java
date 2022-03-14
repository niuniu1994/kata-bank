package com.exalt.katabank.adapter;

import com.exalt.katabank.adapter.port.out.entity.BankAccountEntity;
import com.exalt.katabank.adapter.port.out.entity.TransactionEntity;
import com.exalt.katabank.adapter.port.out.repositry.BankAccountEntityRepository;
import com.exalt.katabank.adapter.port.out.repositry.TransactionEntityRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
public class AccountPersistenceAdapterTest {

    @Autowired
    private BankAccountEntityRepository bankAccountEntityRepository;

    @BeforeAll
    void setup(){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setBalance("100");
        transactionEntity.setDateTime(LocalDateTime.now());
        transactionEntity.setAmount("10");
        transactionEntity.setType("DEPOSIT");
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setBalance("100");
        bankAccountEntity.addTransaction(transactionEntity);
        bankAccountEntityRepository.save(bankAccountEntity);
    }

    @Test
    void  when_bankAccount_id_correct_then_return_account(){

    }

    @Test
    void  when_bankAccount_correct_correct_then_update_success(){

    }
}
