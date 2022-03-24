package com.exalt.calulator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kainingxin
 */
public class KataCalculator {

    /**
     * Since the output type given is int, so we assume that all the input is integer
     * @param numbers
     * @return
     */
    public int add(String numbers){
        if (numbers == null || numbers.length() == 0) return 0;
        String[] numStrs = numbers.split(",");
        long[] longs = Arrays.stream(numStrs).mapToLong(x -> {
            if ("".equals(x)){
                return 0;
            }
            return Long.parseLong(x);
        }).toArray();
        long res = Arrays.stream(longs).sum();
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
            throw new ArithmeticException("Overflow");
        }
        return (int) res;

    }

}
