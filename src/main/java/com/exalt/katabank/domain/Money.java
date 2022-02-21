package com.exalt.katabank.domain;

import lombok.Getter;
import lombok.NonNull;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * @author kainingxin
 */
public class Money {

    private final static DecimalFormat DECIMAL_FORMAT =new DecimalFormat("#.00");

    @Getter
    @NonNull
    private final BigDecimal value;

    public Money(BigDecimal value) {

        this.value = new BigDecimal(DECIMAL_FORMAT.format(value));
    }

    public Money(String value) {
        this.value = new BigDecimal(value);
    }

    public Money add(Money money){
        return null;
    }

    public Money subtract( Money money){
        return null;
    }

    public boolean isPositive(){
        return false;
    }

    public boolean isNegativeOrZero(){
        return false;
    }

    public boolean isGreaterThan(Money money){
        return false;
    }

}
