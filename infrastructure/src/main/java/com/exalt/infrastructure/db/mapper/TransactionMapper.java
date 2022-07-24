package com.exalt.infrastructure.db.mapper;

import com.exalt.domain.model.Money;
import com.exalt.domain.model.Transaction;
import com.exalt.domain.model.TransactionType;
import com.exalt.infrastructure.db.model.TransactionEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    /**
     * Map transactionEntity to transaction model
     * @param transactionEntity
     * @return
     */
    public static Transaction transactionEntity2Transaction(TransactionEntity transactionEntity){
        return new Transaction(transactionEntity.getTransactionId(),transactionEntity.getDateTime(),
                new Money(transactionEntity.getAmount()), TransactionType.valueOf(transactionEntity.getType()),new Money(transactionEntity.getBalance()));
    }

    /**
     * Map transaction to transactionEntity
     * @param transaction
     * @return
     */
    public static TransactionEntity transaction2TransactionEntity(Transaction transaction){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(transaction.transactionId());
        transactionEntity.setType(transaction.transactionType().toString());
        transactionEntity.setAmount(transaction.amount().getValue().toString());
        transactionEntity.setBalance(transaction.balance().getValue().toString());
        transactionEntity.setDateTime(transaction.dateTime());
        return transactionEntity;
    }

}
