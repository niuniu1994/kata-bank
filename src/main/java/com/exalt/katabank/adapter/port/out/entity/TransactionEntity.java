package com.exalt.katabank.adapter.port.out.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
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
    private BankAccountEntity bankAccount = new BankAccountEntity();
}
