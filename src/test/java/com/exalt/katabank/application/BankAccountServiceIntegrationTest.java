package com.exalt.katabank.application;

import com.exalt.katabank.domain.model.BankAccount;
import com.exalt.katabank.domain.model.Money;
import com.exalt.katabank.domain.port.server_side.AccountPersistencePort;
import com.exalt.katabank.domain.service.BankAccountService;
import com.exalt.katabank.infrastructure.db.model.BankAccountEntity;
import com.exalt.katabank.infrastructure.db.model.TransactionEntity;
import com.exalt.katabank.infrastructure.db.repositry.BankAccountEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BankAccountServiceIntegrationTest {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    private BankAccountEntityRepository bankAccountEntityRepository;

    @Autowired
    private AccountPersistencePort accountPersistencePort;

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
    }

    @Test
    void when_multi_thread_save_money_then_should_return_correct_balance() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Callable<Boolean>> callableList = new ArrayList<>();
        Stream.generate(()->"1").limit(50).forEach(i -> callableList.add(()-> bankAccountService.depositMoney(1L,new Money(i))));
        executor.invokeAll(callableList);
        BankAccount bankAccount = accountPersistencePort.loadAccount(1L);
        assertEquals(new Money("150").getValue().toString(),bankAccount.getBalance().getValue().toString());
    }

    @Test
    void when_multi_thread_withdraw_money_then_should_return_correct_balance() throws InterruptedException {
        String expected = accountPersistencePort.loadAccount(1L).getBalance().subtract(new Money("50")).getValue().toString();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Callable<Boolean>> callableList = new ArrayList<>();
        Stream.generate(()->"1").limit(50).forEach(i -> callableList.add(()-> bankAccountService.retrieveMoney(1L,new Money(i))));
        executor.invokeAll(callableList);
        BankAccount bankAccount = accountPersistencePort.loadAccount(1L);
        assertEquals(expected,bankAccount.getBalance().getValue().toString());
    }


}
