package com.oracle;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        // In java 9, three new methods are added to improve its functionality :

        // 1) stream() method : public Stream<T> stream()

        List<Optional<String>> list = Arrays.asList(
                Optional.empty(),
                Optional.of("A"),
                Optional.empty(),
                Optional.of("B")
        );

        /*
            filter the list based to print non-empty values :
            if optional is non-empty, get the value in stream, otherwise return empty
         */
        List<String> filteredList = list.stream()
                .flatMap(optional -> optional.isPresent() ? Stream.of(optional.get()) : Stream.empty())
                .collect(Collectors.toList());

        /*
            Optional::stream method will return a stream of either one
            or zero element if data is present or not.
         */
        List<String> filteredListJava9 = list.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        System.out.println(filteredList);
        System.out.println(filteredListJava9);
        System.out.println("*****************************");

        // 2) ifPresentOrElse() method : public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)

        /*
            If a value is present, performs the given action with the value,
            otherwise performs the given empty-based action.
         */
        Optional<Integer> integerOptional = Optional.of(1);

        integerOptional.ifPresentOrElse(
                x -> System.out.println("value : " + x),
                () -> System.out.println("not present."));

        integerOptional = Optional.empty();

        integerOptional.ifPresentOrElse(
                x -> System.out.println("value : " + x),
                () -> System.out.println("not present."));

        System.out.println("*****************************");

        // 3) or() method : public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier)

        /*
            If a value is present, returns an Optional describing the value,
            otherwise returns an Optional produced by the supplying function.
         */

        Optional<String> stringOptional = Optional.of("reza");

        Supplier<Optional<String>> supplier = () -> Optional.of("not present.");

        stringOptional = stringOptional.or(supplier);

        stringOptional.ifPresent(x -> System.out.println("value : " + x));

        stringOptional = Optional.empty();

        stringOptional = stringOptional.or(supplier);

        stringOptional.ifPresent(x -> System.out.println("value : " + x));



    }
}
