package com.exalt.katabank.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
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
        return false;
    }

    public boolean deposit(Money amount){
        return false;
    }

    public List<Transaction> getHistory(){
        return null;
    }


}
