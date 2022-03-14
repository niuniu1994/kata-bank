package com.exalt.katabank.adapter.port.out.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private LocalDateTime dateTime;

    private String amount;

    private String balance;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private BankAccountEntity bankAccount = new BankAccountEntity();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TransactionEntity that = (TransactionEntity) o;
        return transactionId != null && Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
