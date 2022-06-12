package com.exalt.katabank.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Domain object
 * @author kainingxin
 */
@Getter
@AllArgsConstructor
public class BankAccount {
    private Long accountId;
    private Money balance;
    private List<Transaction> transactions;


    public boolean withDraw(Money amount){
        if (amount == null || !amount.isPositive() || !this.balance.isGreaterOrEqual(amount)) return false;
        this.balance = this.balance.subtract(amount);
        this.transactions.add(new Transaction(null,LocalDateTime.now(),amount, TransactionType.WITHDRAW,this.balance));
        return true;
    }

    public boolean deposit(Money amount){
        if (amount == null || !amount.isPositive()) return false;
        this.balance = this.balance.add(amount);
        this.transactions.add(new Transaction(null,LocalDateTime.now(),amount,TransactionType.DEPOSIT,this.balance));
        return true;
    }

    public List<Transaction> getHistory(){
        return this.transactions;
    }


}
