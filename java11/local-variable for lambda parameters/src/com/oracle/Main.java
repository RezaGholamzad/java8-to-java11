package com.oracle;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        var arrInteger = new Integer[]{5, 9, 3, 6, 2, 4, 8, 7, 1};
        long counter = Arrays.stream(arrInteger)
                .filter(x -> (x != null && x > 5))
                .count();

        System.out.println(counter);

        // in java 11
        var array = new Integer[]{5, 9, 3, 6, 2, 4, 8, 7, 1};
        long count = Arrays.stream(array)
                .filter( (var x) -> x != null && x > 5)
                .count();
        System.out.println(count);
    }
}
