package com.exalt.katabank.application;

import com.exalt.katabank.adapter.port.out.entity.BankAccountEntity;
import com.exalt.katabank.adapter.port.out.entity.TransactionEntity;
import com.exalt.katabank.application.port.out.AccountPersistencePort;
import com.exalt.katabank.application.port.service.BankAccountService;
import com.exalt.katabank.domain.BankAccount;
import com.exalt.katabank.domain.Money;
import static  org.junit.jupiter.api.Assertions.*;

import com.exalt.katabank.domain.Transaction;
import com.exalt.katabank.domain.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {


    @Mock
    AccountPersistencePort accountPersistencePort;

    @InjectMocks
    private BankAccountService bankAccountService;

    BankAccount bankAccount;
    @BeforeEach
    void setup(){
        bankAccount = new BankAccount(1L,
                new Money("100"), new ArrayList<>(List.of(new Transaction(1L,LocalDateTime.now(),new Money("10"), TransactionType.WITHDRAW,new Money("100")))) );

    }

    @Test
    void  when_deposit_money_the_balance_then_should_return_true(){
        Mockito.when(accountPersistencePort.loadAccount(1L)).thenReturn(bankAccount);
        assertTrue(bankAccountService.depositMoney(1L,new Money("100")));
    }

    @Test
    void  when_retrieve_money_the_balance_then_should_true(){
        Mockito.when(accountPersistencePort.loadAccount(1L)).thenReturn(bankAccount);
        assertTrue(bankAccountService.retrieveMoney(1L,new Money("100")));
    }

    @Test
    void  when_check_transaction_then_should_return_items(){
        Mockito.when(accountPersistencePort.loadAccount(1L)).thenReturn(bankAccount);
        assertNotNull(bankAccountService.checkTransactions(1L));
    }
}
