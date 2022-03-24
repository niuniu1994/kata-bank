package com.exalt.calulator;

import java.util.Arrays;
import java.util.List;

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
        String delimiters = ",\n";

        if (numbers.indexOf("//") == 0 && numbers.indexOf("\n",3) == 3 ){
            char d = numbers.charAt(2);
            numbers = numbers.substring(4);
            delimiters = delimiters.chars().anyMatch(x -> x == d) ? delimiters:delimiters + d;
        }

        if (numbers.contains(",\n") || numbers.contains("\n,")){
            throw new IllegalArgumentException("format incorrect");
        }

        String[] numStrs = numbers.split(String.format("[%s]",delimiters));
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
