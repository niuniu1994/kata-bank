package com.exalt.katabank.application.port.service;

import com.exalt.katabank.application.port.in.CheckTransactionsUseCase;
import com.exalt.katabank.application.port.in.DepositMoneyUseCase;
import com.exalt.katabank.application.port.in.RetrieveMoneyUseCase;
import com.exalt.katabank.application.port.out.AccountPersistencePort;
import com.exalt.katabank.domain.BankAccount;
import com.exalt.katabank.domain.Money;
import com.exalt.katabank.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@Service
public class BankAccountService implements CheckTransactionsUseCase, DepositMoneyUseCase, RetrieveMoneyUseCase {

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
    public boolean depositMoney(Long accountId,Money money) {
        Assert.notNull(accountId,"accountId is undefined");
        Assert.notNull(accountId,"money is undefined");
        BankAccount bankAccount = accountPersistencePort.loadAccount(accountId);
        if (bankAccount.deposit(money)){
            accountPersistencePort.updateAccount(bankAccount);
            return true;
        }
        return false;
    }

    @Override
    public boolean retrieveMoney( Long accountId, Money money) {
        Assert.notNull(accountId,"accountId is undefined");
        Assert.notNull(accountId,"money is undefined");
        BankAccount bankAccount = accountPersistencePort.loadAccount(accountId);
        if (bankAccount.withDraw(money)){
            accountPersistencePort.updateAccount(bankAccount);
            return true;
        }
        return false;
    }
}
