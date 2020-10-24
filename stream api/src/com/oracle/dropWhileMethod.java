package com.oracle;

import java.util.stream.Stream;

public class dropWhileMethod {
    public static void main(String[] args) {
        /*
            default Stream<T> dropWhile(Predicate<? super T> predicate)

            dropWhile method throw away all the values at the start until the predicate
            returns true. It returns, in case of ordered stream, a stream consisting of
            the remaining elements of this stream after dropping the longest prefix of
            elements matching the given predicate.
         */
        Stream.of("a","b","c","","e","f")
                .dropWhile(s -> !s.isEmpty())
                .forEach(System.out::print);

        System.out.println();
        Stream.of("a","b","c","","e","","f")
                .dropWhile(s-> !s.isEmpty())
                .forEach(System.out::print);
    }
}
