package com.exalt.infrastructure.db.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private LocalDateTime dateTime;

    private String amount;

    private String balance;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @ToString.Exclude
    private BankAccountEntity bankAccount = new BankAccountEntity();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionEntity that = (TransactionEntity) o;

        if (transactionId != null ? !transactionId.equals(that.transactionId) : that.transactionId != null) return false;
        return  true;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
