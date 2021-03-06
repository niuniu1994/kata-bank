package com.exalt.domain.service;


import com.exalt.domain.model.BankAccount;
import com.exalt.domain.model.Money;
import com.exalt.domain.model.Transaction;
import com.exalt.domain.model.TransactionType;
import com.exalt.domain.port.driven.AccountPersistencePort;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {


    @Mock
    AccountPersistencePort accountPersistencePort;

    @InjectMocks
    private BankAccountService bankAccountService;

    BankAccount bankAccount;

    @BeforeEach
    void setup(){
        bankAccount = new BankAccount(1L,
                new Money("100"), new ArrayList<>(List.of(new Transaction(1L, LocalDateTime.now(),new Money("10"), TransactionType.WITHDRAW,new Money("100")))) );
    }

    @Test
    void  when_deposit_money_the_balance_then_should_return_true(){
        Mockito.when(accountPersistencePort.loadAccount(1L)).thenReturn(bankAccount);
        Mockito.when(accountPersistencePort.updateAccount(bankAccount)).thenReturn(true);

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

    @Test
    void  when_multi_thread_deposit_money_then_should_return_correct_result(){
        Mockito.when(accountPersistencePort.loadAccount(1L)).thenReturn(bankAccount);
        assertNotNull(bankAccountService.checkTransactions(1L));
    }
}
