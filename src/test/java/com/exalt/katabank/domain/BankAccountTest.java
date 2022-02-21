package com.exalt.katabank.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * BankAccount Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Feb 21, 2022</pre>
 */
public class BankAccountTest {

    BankAccount bankAccount;

    @BeforeEach
    public void setup(){
        Money money = new Money("100.20");
         bankAccount = new BankAccount(1L,money, List.of());
    }

    @Test
    public void when_save_money_with_negative_value_should_return_false() {

    }

    @Test
    public void when_save_money_with_positive_value_should_return_true(){

    }

    @Test
    public void when_withdraw_amount_smaller_or_equals_to_balance_should_return_true(){

    }

    @Test
    public void when_withdraw_amount_bigger_to_balance_should_return_false(){

    }


} 

