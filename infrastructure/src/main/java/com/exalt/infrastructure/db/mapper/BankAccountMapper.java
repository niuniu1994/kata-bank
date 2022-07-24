package com.exalt.infrastructure.db.mapper;


import com.exalt.domain.model.BankAccount;
import com.exalt.domain.model.Money;
import com.exalt.domain.model.Transaction;
import com.exalt.infrastructure.db.model.BankAccountEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankAccountMapper {

    /**
     * Map bankAccountEntity to bankAccount model
     * @param bankAccountEntity
     * @return
     */
    public static BankAccount bankAccountEntity2BankAccount(BankAccountEntity bankAccountEntity){
        Money money = new Money(bankAccountEntity.getBalance());
        List<Transaction> transactions = new ArrayList<>(bankAccountEntity
                .getTransactionEntitySet()
                .stream()
                .map(TransactionMapper::transactionEntity2Transaction)
                .toList());
        return new BankAccount(bankAccountEntity.getAccountId(),money,transactions);
    }


    public static BankAccountEntity bankAccount2BankAccountEntity(BankAccount bankAccount){
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountId(bankAccount.getAccountId());
        bankAccountEntity.setBalance(bankAccount.getBalance().getValue().toString());
        if (bankAccount.getTransactions() != null){
            bankAccount.getTransactions().forEach( transaction ->
                    bankAccountEntity.addTransaction(TransactionMapper.transaction2TransactionEntity(transaction))
            );
        }
        return bankAccountEntity;
    }

}
