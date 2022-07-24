package com.exalt.domain;

import com.exalt.domain.model.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class MoneyTest {

    @Test
    void when_init_money_then_only_keep_two_decimal_place() {
        assertEquals("120.23", new Money("120.2333").getValue().toString());
    }

    @Test
    void when_two_positive_numbers_add_then_return_correct_result() {
        Money m1 = new Money("100");
        Money m2 = new Money("20.40");
        assertEquals("120.40", m2.add(m1).getValue().toString());
    }

    @Test
    void when_negative_and_positive_numbers_add_then_return_correct_result() {
        Money m1 = new Money("100.40");
        Money m2 = new Money("-20.40");
        assertEquals("80.00", m2.add(m1).getValue().toString());
    }

    @Test
    void when_positive_and_positive_numbers_subtract_then_return_correct_result() {
        Money m1 = new Money("100.40");
        Money m2 = new Money("20.40");
        assertEquals("80.00", m1.subtract(m2).getValue().toString());
    }

    @Test
    void when_negative_and_positive_numbers_subtract_then_return_correct_result() {
        Money m1 = new Money("100.40");
        Money m2 = new Money("-20.40");
        assertEquals("120.80", m1.subtract(m2).getValue().toString());
    }

    @Test
    void when_m1_positive_then_return_true() {
        Money m1 = new Money("100.40");
        assertTrue(m1.isPositive());
    }


    @Test
    void when_m1_is_not_a_number_then_exception_throw() {
        assertThrows(NumberFormatException.class, () -> new Money("12d"));
    }

    @Test
    void when_m1_negative_return_true_otherwise_false() {
        Money m1 = new Money("-100.40");
        assertFalse(m1.isPositive());
        m1 = new Money("0");
        assertFalse(m1.isPositive());
    }

    @Test
    void when_m1_is_zero_then_return_true_otherwise_false() {
        Money m1 = new Money("0");
        assertTrue(m1.isZero());
        m1 = new Money("-1");
        assertFalse(m1.isZero());
    }

    @Test
    void when_m1_equal_m2_then_return_true_otherwise_false() {
        Money m1 = new Money("-12");
        assertTrue(m1.isNegative());
        m1 = new Money("0");
        assertFalse(m1.isNegative());
    }

}
