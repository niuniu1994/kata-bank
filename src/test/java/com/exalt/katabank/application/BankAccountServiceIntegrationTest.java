package com.exalt.katabank.application;

import com.exalt.katabank.adapter.port.out.entity.BankAccountEntity;
import com.exalt.katabank.adapter.port.out.entity.TransactionEntity;
import com.exalt.katabank.adapter.port.out.repositry.BankAccountEntityRepository;
import com.exalt.katabank.application.port.out.AccountPersistencePort;
import com.exalt.katabank.application.port.service.BankAccountService;
import com.exalt.katabank.domain.BankAccount;
import com.exalt.katabank.domain.Money;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
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
        //TODO inorder to avoid data consistence problem under multiThread
        // , I have implement lock and transaction but it still doesn't work.
        // It appears that sometimes it reads the old balance of the account.


        ExecutorService executor = Executors.newFixedThreadPool(1);
        List<Callable<Boolean>> callableList = new ArrayList<>();
        Stream.generate(()->"1").limit(10).forEach(i -> callableList.add(()-> bankAccountService.depositMoney(1L,new Money(i))));
        executor.invokeAll(callableList);
        BankAccount bankAccount = accountPersistencePort.loadAccount(1L);
        assertEquals(new Money("110").getValue().toString(),bankAccount.getBalance().getValue().toString());
    }

    @Test
    void when_multi_thread_withdraw_money_then_should_return_correct_balance() throws InterruptedException {
        //TODO inorder to avoid data consistence problem under multiThread
        // , I have implement lock and transaction but it still doesn't work.
        // It appears that sometimes it reads the old balance of the account.
        ExecutorService executor = Executors.newFixedThreadPool(1);
        List<Callable<Boolean>> callableList = new ArrayList<>();
        Stream.generate(()->"1").limit(100).forEach(i -> callableList.add(()-> bankAccountService.retrieveMoney(1L,new Money(i))));
        executor.invokeAll(callableList);
        assertEquals(bankAccountEntityRepository.getById(1L).getBalance(),new Money("0"));
    }


}