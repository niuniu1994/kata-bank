package com.exalt.application.mapper;

import com.exalt.application.model.TransactionDTO;
import com.exalt.domain.model.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kainingxin
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    public static TransactionDTO transaction2TransactionDTO(Transaction transaction){
        return new TransactionDTO(
                transaction.dateTime(),
                transaction.amount().getValue().toPlainString(),
                transaction.transactionType().name(),
                transaction.balance().getValue().toPlainString()
        );
    }

    public static Set<TransactionDTO> transactionSet2TransactionDTOSet(Set<Transaction> transactions){
        return transactions.stream().map(TransactionMapper::transaction2TransactionDTO).collect(Collectors.toSet());
    }
}
