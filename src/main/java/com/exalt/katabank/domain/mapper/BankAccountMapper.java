package com.exalt.katabank.domain.mapper;


import com.exalt.katabank.domain.model.BankAccount;
import com.exalt.katabank.domain.model.Money;
import com.exalt.katabank.domain.model.Transaction;
import com.exalt.katabank.infrastructure.db.model.BankAccountEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BankAccountMapper {

    /**
     * Map bankAccountEntity to bankAccount model
     * @param bankAccountEntity
     * @return
     */
    public static BankAccount bankAccountEntity2BankAccount(BankAccountEntity bankAccountEntity){
        Money money = new Money(bankAccountEntity.getBalance());
        List<Transaction> transactions = new ArrayList<>(bankAccountEntity.getTransactionEntitySet().stream().map(TransactionMapper::transactionEntity2Transaction).toList());
        return new BankAccount(bankAccountEntity.getAccountId(),money,transactions);
    }


    public static BankAccountEntity bankAccount2BankAccountEntity(BankAccount bankAccount){
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountId(bankAccount.getAccountId());
        bankAccountEntity.setBalance(bankAccount.getBalance().getValue().toString());
        if (bankAccount.getTransactions() != null){
            bankAccountEntity.setTransactionEntitySet(bankAccount.getTransactions().stream().map(TransactionMapper::transaction2TransactionEntity).collect(Collectors.toSet()));
        }
        return bankAccountEntity;
    }

}
