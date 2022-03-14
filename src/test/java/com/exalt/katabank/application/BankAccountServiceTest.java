package com.exalt.katabank.application;

import com.exalt.katabank.adapter.port.out.entity.BankAccountEntity;
import com.exalt.katabank.adapter.port.out.entity.TransactionEntity;
import com.exalt.katabank.application.port.out.AccountPersistencePort;
import com.exalt.katabank.application.port.service.BankAccountService;
import com.exalt.katabank.domain.Money;
import static  org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {


    @Mock
    AccountPersistencePort accountPersistencePort;

    @InjectMocks
    private BankAccountService bankAccountService;

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
    }

    @Test
    void  when_deposit_money_the_balance_then_should_return_true(){
        assertTrue(bankAccountService.depositMoney(1L,new Money("100")));
    }

    @Test
    void  when_retrieve_money_the_balance_then_should_true(){
        assertTrue(bankAccountService.retrieveMoney(1L,new Money("100")));

    }

    @Test
    void  when_check_transaction_then_should_return_items(){
        assertNotNull(bankAccountService.checkTransactions(1L));
    }
}
