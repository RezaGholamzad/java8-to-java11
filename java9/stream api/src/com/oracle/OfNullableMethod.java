package com.oracle;

import java.util.stream.Stream;

public class OfNullableMethod {
    public static void main(String[] args) {
        /*
            static <T> Stream<T> ofNullable(T t)

            This method is introduced to avoid NullPointerException.
            This method returns an empty stream if the stream is null. It can also
            be used on a non-empty stream where it returns a sequential stream
            of single element.
         */
        long count = Stream.ofNullable(100).count();
        System.out.println(count);

        count = Stream.ofNullable(null).count();
        System.out.println(count);

    }
}
