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

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bankAccount",orphanRemoval = true,fetch = FetchType.LAZY)
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
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        return transactionEntitySet != null ? transactionEntitySet.equals(that.transactionEntitySet) : that.transactionEntitySet == null;
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}
