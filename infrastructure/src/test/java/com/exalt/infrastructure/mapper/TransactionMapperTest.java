package com.exalt.infrastructure.mapper;

import com.exalt.domain.model.Money;
import com.exalt.domain.model.Transaction;
import com.exalt.domain.model.TransactionType;
import com.exalt.infrastructure.db.mapper.TransactionMapper;
import com.exalt.infrastructure.db.model.TransactionEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionMapperTest {

    @Test
    void when_transactionEntity2Transaction_then_should_return_transaction(){
        TransactionEntity transactionEntity = new TransactionEntity(1L, LocalDateTime.now(),"20.00","30.00","DEPOSIT",null);
        Transaction transaction = TransactionMapper.transactionEntity2Transaction(transactionEntity);

        assertEquals(transactionEntity.getTransactionId(),transaction.transactionId());
        assertEquals(transactionEntity.getAmount(),transaction.amount().getValue().toPlainString());
        assertEquals(transactionEntity.getBalance(),transaction.balance().getValue().toPlainString());
        assertEquals(transactionEntity.getType(),transaction.transactionType().name());
    }

    @Test
    void when_transaction2TransactionEntity_then_should_return_transactionEntity(){
        Transaction transaction = new Transaction(1L,LocalDateTime.now(),new Money("20.00"), TransactionType.WITHDRAW,new Money("20.00"));
        TransactionEntity transactionEntity = TransactionMapper.transaction2TransactionEntity(transaction);

        assertEquals(transaction.transactionId(),transactionEntity.getTransactionId());
        assertEquals(transaction.amount().getValue().toPlainString(),transactionEntity.getAmount());
        assertEquals(transaction.balance().getValue().toPlainString(),transactionEntity.getBalance());
        assertEquals(transaction.transactionType().name(),transactionEntity.getType());
    }
}
