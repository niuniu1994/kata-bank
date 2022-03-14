package com.exalt.katabank.adapter.port.out.entity;

import com.exalt.katabank.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "bankAccount")
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    private String balance;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private Set<TransactionEntity> transactionEntitySet = new HashSet<>();

    public void addTransaction(TransactionEntity transactionEntity){
        transactionEntitySet.add(transactionEntity);
        transactionEntity.setBankAccount(this);
    }

    public void removeTransaction(TransactionEntity transactionEntity){
        transactionEntitySet.remove(transactionEntity);
        transactionEntity.setBankAccount(null);
    }
}
