package com.exalt.domain;


import com.exalt.domain.model.BankAccount;
import com.exalt.domain.model.Money;
import com.exalt.domain.model.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BankAccount Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Feb 21, 2022</pre>
 */
class BankAccountTest {

    BankAccount bankAccount;

    @BeforeEach
    public void setup() {
        Money money = new Money("100.20");
        bankAccount = new BankAccount(1L, money, new ArrayList<>());
    }

    @Test
    void when_save_money_with_negative_or_zero_value_should_return_false() {
        Money money = new Money("-200");
        assertFalse(bankAccount.deposit(money));
        money = new Money("0");
        assertFalse(bankAccount.deposit(money));
    }

    @Test
    void when_save_money_with_positive_value_should_return_true_and_correct_balance() {
        Money money = new Money("200");
        assertTrue(bankAccount.deposit(money));
        assertEquals("300.20", bankAccount.getBalance().getValue().toString());
    }

    @Test
    void when_withdraw_negative_or_zero_amount_should_return_false() {
        Money money = new Money("-200");
        assertFalse(bankAccount.withDraw(money));
        money = new Money("0");
        assertFalse(bankAccount.withDraw(money));
    }


    @Test
    void when_withdraw_amount_smaller_or_equals_to_balance_should_return_true() {
        Money money = new Money("100");
        assertTrue(bankAccount.withDraw(money));
        assertEquals("0.20", bankAccount.getBalance().getValue().toString());
    }

    @Test
    void when_withdraw_amount_bigger_to_balance_should_return_false() {
        Money money = new Money("200");
        assertFalse(bankAccount.withDraw(money));
    }

    @Test
    void when_withdrew_success_then_un_transaction_added() {
        Money money = new Money("100");
        assertTrue(bankAccount.withDraw(money));
        assertEquals("100.00", bankAccount.getTransactions().get(0).amount().getValue().toString());
        assertEquals("0.20", bankAccount.getTransactions().get(0).balance().getValue().toString());
        assertEquals(TransactionType.WITHDRAW, bankAccount.getTransactions().get(0).transactionType());
    }

    @Test
    void when_deposit_success_then_un_transaction_added() {
        Money money = new Money("100");
        assertTrue(bankAccount.deposit(money));
        assertEquals("100.00", bankAccount.getTransactions().get(0).amount().getValue().toString());
        assertEquals("200.20", bankAccount.getTransactions().get(0).balance().getValue().toString());
        assertEquals(TransactionType.DEPOSIT, bankAccount.getTransactions().get(0).transactionType());
    }

} 

