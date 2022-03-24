package com.exalt.calculator;

import com.exalt.calulator.KataCalculator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class KataCalculatorTest {
    static KataCalculator kataCalculator;

    @BeforeAll
    static void beforeAll() {
        kataCalculator = new KataCalculator();
    }

    @Test
    public void step1(){
        assertEquals(3,kataCalculator.add("1,2"));
        assertEquals(0,kataCalculator.add(""));
    }

    @Test
    public void step2(){
        assertEquals(6,kataCalculator.add("1,2,3"));
        assertEquals(6,kataCalculator.add("1,2,3,,"));
        assertEquals(0,kataCalculator.add(""));
        assertThrows(ArithmeticException.class,()->kataCalculator.add(String.valueOf(Integer.MAX_VALUE).concat(",1")));
    }


}
