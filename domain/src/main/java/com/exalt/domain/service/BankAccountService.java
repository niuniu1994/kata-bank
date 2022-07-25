package com.exalt.domain.service;

import com.exalt.domain.model.BankAccount;
import com.exalt.domain.model.Money;
import com.exalt.domain.model.Transaction;
import com.exalt.domain.model.enums.ExceptionEnum;
import com.exalt.domain.model.exception.BusinessLogicException;
import com.exalt.domain.port.driven.AccountPersistencePort;
import com.exalt.domain.port.driving.CheckTransactionsUsecase;
import com.exalt.domain.port.driving.DepositMoneyUseCase;
import com.exalt.domain.port.driving.RetrieveMoneyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class BankAccountService implements CheckTransactionsUsecase, DepositMoneyUseCase, RetrieveMoneyUseCase {



    private final AccountPersistencePort accountPersistencePort;

    @Override
    public Set<Transaction> checkTransactions(Long accountId) {
        BankAccount bankAccount = accountPersistencePort.loadAccount(
                Optional.ofNullable(accountId)
                        .orElseThrow(
                                () -> new BusinessLogicException(ExceptionEnum.UNDEFINED_ACCOUNT)
                        )
        );
        return new HashSet<>(bankAccount.getTransactions());
    }

    @Override
    public boolean depositMoney(Long accountId, Money money) {
        accountId = Optional.ofNullable(accountId).orElseThrow(()-> new BusinessLogicException(ExceptionEnum.UNDEFINED_ACCOUNT));
        money = Optional.ofNullable(money).orElseThrow(()-> new BusinessLogicException(ExceptionEnum.ILLEGAL_MONEY));

        log.info("User {} try to  deposit {}", accountId, money.getValue());
        BankAccount bankAccount = accountPersistencePort.loadAccount(accountId);
        log.info("User {} account found with balance {}", accountId, bankAccount.getBalance().getValue());

        if (bankAccount.deposit(money) && accountPersistencePort.updateAccount(bankAccount)) {
            log.info("{} is saved in {} account", money.getValue(), accountId);
            bankAccount = accountPersistencePort.loadAccount(accountId);
            log.info("balance {}", bankAccount.getBalance().getValue());
            return true;
        }
        log.error("User {} failed to deposit {}", accountId, money.getValue());
        return false;
    }


    @Override
    public synchronized boolean retrieveMoney(Long accountId, Money money) {
        accountId = Optional.ofNullable(accountId).orElseThrow(()-> new BusinessLogicException(ExceptionEnum.UNDEFINED_ACCOUNT));
        money = Optional.ofNullable(money).orElseThrow(()-> new BusinessLogicException(ExceptionEnum.ILLEGAL_MONEY));
        log.info("User {} try to withdraw {}", accountId, money.getValue());

        BankAccount bankAccount = accountPersistencePort.loadAccount(accountId);
        log.info("User {} account found", accountId);

        if (bankAccount.withDraw(money)) {
            log.info("{} is withdraw from {} account", money.getValue(), accountId);
            accountPersistencePort.updateAccount(bankAccount);
            return true;
        }
        log.error("User {} failed to withdraw {}", accountId, money);
        return false;
    }

}
