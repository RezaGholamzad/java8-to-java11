package com.oracle;

import java.util.stream.IntStream;

public class IterateMethod {
    public static void main(String[] args) {
        /*
            static <T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)

            iterate method now has hasNext predicate as parameter which stops the loop
            once hasNext predicate returns false.

            First argument is the initialising value, the returned stream starts with this value.
            Second argument is the predicate, the iteration continues until this given predicate returns false.
            Third argument updates the value of previous iteration.
         */
        IntStream.iterate(3, x -> x < 10, x -> x+ 3)
                .forEach(System.out::println);
    }
}
