package com.exalt.domain.model;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author kainingxin
 */
public class Money {

    private static final DecimalFormat DECIMAL_FORMAT =new DecimalFormat("#.00");

    @Getter
    @NonNull
    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = new BigDecimal(DECIMAL_FORMAT.format(value));
    }

    public Money(String value) {
        this.value = new BigDecimal(value).setScale(2, RoundingMode.DOWN);
    }

    public Money add(Money money){
        return new Money(this.value.add(money.getValue()));
    }

    public Money subtract( Money money){
        return new Money(this.value.subtract(money.getValue()));
    }

    public boolean isPositive(){
        return this.value.signum() == 1;
    }

    public boolean isNegative(){
        return this.value.signum() == -1;
    }

    public boolean isZero(){
        return this.value.signum() == 0;
    }

    public boolean isGreaterOrEqual(Money money){
        return this.value.compareTo(money.getValue()) >= 0;
    }

}
