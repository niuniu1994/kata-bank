package com.exalt.infrastructure.db.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "bank_account")
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String balance;

    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH,CascadeType.MERGE},
            mappedBy = "bankAccount",orphanRemoval = true,fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<TransactionEntity> transactionEntitySet = new HashSet<>();

    public void addTransaction(TransactionEntity transactionEntity){
        transactionEntitySet.add(transactionEntity);
        transactionEntity.setBankAccount(this);
    }

    public void removeTransaction(TransactionEntity transactionEntity){
        transactionEntitySet.remove(transactionEntity);
        transactionEntity.setBankAccount(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccountEntity that = (BankAccountEntity) o;

        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
