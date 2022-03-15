package com.exalt.katabank.application.port.service;

import com.exalt.katabank.application.port.in.CheckTransactionsUseCase;
import com.exalt.katabank.application.port.in.DepositMoneyUseCase;
import com.exalt.katabank.application.port.in.RetrieveMoneyUseCase;
import com.exalt.katabank.application.port.out.AccountPersistencePort;
import com.exalt.katabank.domain.BankAccount;
import com.exalt.katabank.domain.Money;
import com.exalt.katabank.domain.Transaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Log4j2
public class BankAccountService implements CheckTransactionsUseCase, DepositMoneyUseCase, RetrieveMoneyUseCase {

    private final Lock accountLock = new ReentrantLock();
    private final AccountPersistencePort accountPersistencePort;

    @Autowired
    public BankAccountService(AccountPersistencePort accountPersistencePort) {
        this.accountPersistencePort = accountPersistencePort;
    }

    @Override
    public Set<Transaction> checkTransactions( Long accountId) {
        Assert.notNull(accountId,"accountId is undefined");
        BankAccount bankAccount = accountPersistencePort.loadAccount(accountId);
        return new HashSet<>(bankAccount.getTransactions());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class,isolation = Isolation.REPEATABLE_READ)
    public boolean depositMoney(Long accountId,Money money) {
        synchronized (this){
            log.info("User {} try to  deposit {}",accountId,money.getValue());
            Assert.notNull(accountId,"accountId is undefined");
            Assert.notNull(accountId,"money is undefined");

                BankAccount bankAccount = accountPersistencePort.loadAccount(accountId);
                log.info("User {} account found with balance {}",accountId,bankAccount.getBalance().getValue());

                if (bankAccount.deposit(money) && accountPersistencePort.updateAccount(bankAccount)){
                    log.info("{} is saved in {} account",money.getValue(),accountId);
                    bankAccount = accountPersistencePort.loadAccount(accountId);
                    log.info("balance {}",bankAccount.getBalance().getValue());
                    return true;
                }
                log.error("User {} failed to deposit {}",accountId,money.getValue());
                return false;
            }


    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean retrieveMoney( Long accountId, Money money) {
        synchronized (this) {
            log.info("User {} try to withdraw {}", accountId, money.getValue());
            Assert.notNull(accountId, "accountId is undefined");
            Assert.notNull(accountId, "money is undefined");
            log.info("User {} account found", accountId);

            BankAccount bankAccount = accountPersistencePort.loadAccount(accountId);
            if (bankAccount.withDraw(money)) {
                log.info("{} is withdraw from {} account", money.toString(), accountId);
                accountPersistencePort.updateAccount(bankAccount);
                return true;
            }
            log.error("User {} failed to withdraw {}", accountId, money);
            return false;
        }
    }
}
