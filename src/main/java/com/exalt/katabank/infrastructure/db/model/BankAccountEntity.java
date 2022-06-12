package com.exalt.katabank.infrastructure.db.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "bankAccount")
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    private String balance;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bankAccount",orphanRemoval = true,fetch = FetchType.EAGER)
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BankAccountEntity that = (BankAccountEntity) o;
        return accountId != null && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
