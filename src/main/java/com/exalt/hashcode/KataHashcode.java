package com.exalt.hashcode;

import java.util.Arrays;
import java.util.Random;

/**
 * @author kainingxin
 */
public class KataHashcode {

    // s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
    public static String[] generateThreeStringWithSameHashCode(){
        String[] strings = new String[3];
        Random random = new Random();
        //at least two char, so the smallest value should bigger than 127(for the single char the biggest value is 126 in our program)
        int hash = random.nextInt(10, 99999);
        char[] chars = String.valueOf(hash).toCharArray();
        strings[0] = String.valueOf(chars);
        for (int i =1 ; i < 3; i++){
            chars[0] -= 1;
            chars[1] += 31;
            strings[i] = String.valueOf(chars);
        }
        return strings;
    }



    public static void main(String[] args) {
       String[] strings =  generateThreeStringWithSameHashCode();
        Arrays.stream(strings).forEach(System.out::println);
        System.out.println(strings[0].hashCode() == strings[1].hashCode() &&strings[0].hashCode() == strings[2].hashCode());
        System.out.println(Arrays.stream(strings).distinct().count() == 3);
    }
}
