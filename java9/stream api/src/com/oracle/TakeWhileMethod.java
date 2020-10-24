package com.oracle;

import java.util.stream.Stream;

public class TakeWhileMethod {
    public static void main(String[] args) {
        /*

            takeWhile(Predicate Interface) :
            default Stream<T> takeWhile(Predicate<? super T> predicate)

            takeWhile method takes all the values until the predicate returns false.
            It returns, in case of ordered stream, a stream consisting of the longest
            prefix of elements taken from this stream matching the given predicate.
         */
        Stream.of("a","b","c","","e","f")
                .takeWhile(s -> !s.isEmpty())
                .forEach(System.out::println);
    }
}
