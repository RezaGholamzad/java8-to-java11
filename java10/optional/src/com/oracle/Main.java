package com.oracle;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        /*
            java.util.Optional, java.util.OptionalDouble, java.util.OptionalInt and
            java.util.OptionalLong each got a new method orElseThrow() which
            doesn't take any argument and throws NoSuchElementExceptionif no value is present.

            It's synonymous with and is now the preferred alternative to the existing get()method.
         */
        var list = Arrays.asList(1, 5, 3);

        var firstEven = list.stream()
                .filter(i -> i % 2 == 0)
                .findFirst()
                .orElseThrow();

    }
}
