package com.exalt.infrastructure.adapter;

import com.exalt.domain.model.BankAccount;
import com.exalt.domain.model.Money;
import com.exalt.infrastructure.db.adapter.AccountPersistenceAdapter;
import com.exalt.infrastructure.db.model.BankAccountEntity;
import com.exalt.infrastructure.db.model.TransactionEntity;
import com.exalt.infrastructure.db.repositry.BankAccountEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("infra")
class AccountPersistenceAdapterTest {


    @Autowired
    private BankAccountEntityRepository bankAccountEntityRepository;

    private AccountPersistenceAdapter adapter;

    @BeforeEach
    void setUp(){
        adapter = new AccountPersistenceAdapter(bankAccountEntityRepository);
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountId(1L);
        bankAccountEntity.setBalance("20.00");
        TransactionEntity transactionEntity = new TransactionEntity(1L, LocalDateTime.now(),"20.00","30.00","DEPOSIT",null);
        bankAccountEntity.addTransaction(transactionEntity);
        bankAccountEntityRepository.save(bankAccountEntity);
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
