package com.exalt.application;

import com.exalt.application.mapper.TransactionMapper;
import com.exalt.application.model.TransactionDTO;
import com.exalt.domain.model.Money;
import com.exalt.domain.model.Transaction;
import com.exalt.domain.model.TransactionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionMapperTest {

    @Test
    void when_transaction2TransactionDTO_then_should_return_transactionDTO(){
        Transaction transaction = new Transaction(1L, LocalDateTime.now(),new Money("20"), TransactionType.WITHDRAW,new Money("20"));
        TransactionDTO transactionDTO = TransactionMapper.transaction2TransactionDTO(transaction);

        assertEquals(transaction.dateTime(),transactionDTO.operationTime());
        assertEquals(transaction.amount().getValue().toPlainString(),transactionDTO.amount());
        assertEquals(transaction.balance().getValue().toPlainString(),transactionDTO.balanceAfterOperation());
        assertEquals(transaction.transactionType().name(),transactionDTO.operationType());
    }

    @Test
    void when_transactionSet2TransactionDTOSet_then_should_return_transactionDTOSet(){
        Transaction transaction = new Transaction(1L, LocalDateTime.now(),new Money("20"), TransactionType.WITHDRAW,new Money("20"));
        Set<TransactionDTO> transactionDTOSet = TransactionMapper.transactionSet2TransactionDTOSet(Set.of(transaction));

        TransactionDTO transactionDTO = transactionDTOSet.stream().findFirst().get();

        assertEquals(transaction.dateTime(),transactionDTO.operationTime());
        assertEquals(transaction.amount().getValue().toPlainString(),transactionDTO.amount());
        assertEquals(transaction.balance().getValue().toPlainString(),transactionDTO.balanceAfterOperation());
        assertEquals(transaction.transactionType().name(),transactionDTO.operationType());
    }


}
