package com.exalt.katabank.adapter.port.out.mapper;


import com.exalt.katabank.adapter.port.out.entity.BankAccountEntity;
import com.exalt.katabank.domain.BankAccount;
import com.exalt.katabank.domain.Money;
import com.exalt.katabank.domain.Transaction;

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
        List<Transaction> transactions = bankAccountEntity.getTransactionEntitySet().stream().map(TransactionMapper::transactionEntity2Transaction).toList();
        return new BankAccount(bankAccountEntity.getAccountId(),money,transactions);
    }


    public static BankAccountEntity bankAccount2BankAccountEntity(BankAccount bankAccount){
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountId(bankAccountEntity.getAccountId());
        bankAccountEntity.setBalance(bankAccount.getBalance().toString());
        if (bankAccount.getTransactions() != null){
            bankAccountEntity.setTransactionEntitySet(bankAccount.getTransactions().stream().map(TransactionMapper::transaction2TransactionEntity).collect(Collectors.toSet()));
        }
        return bankAccountEntity;
    }

}
