package com.oracle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToUnmodifiableMethod {
    public static void main(String[] args) {

        /*
            java.util.stream.Collectors get additional methods to collect a
            Stream into unmodifiable List, Map or Set:
         */

        // List :
        var list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);

        var copyList = list.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableList());

        System.out.println("copy List : " + copyList);

//        copyList.add(3); //java.lang.UnsupportedOperationException

        // Map :
        var copyListInFormOfMap = list.stream()
                .collect(Collectors.toUnmodifiableMap(i -> i, i -> "element " + i));

        System.out.println("copyListInFormOfMap : " + copyListInFormOfMap);

        /*
            use Java Streams to work with Maps :

            Basic Idea :

            The principal thing to notice is that Streams are sequences of elements which can
            be easily obtained from a Collection.

            Maps have a different structure, with a mapping from keys to values, without sequence.
            This doesn't mean that we can't convert a Map structure into different sequences
            which then allow us to work in a natural way with the Stream API.

            Let's see ways of obtaining different Collections from a Map,
            which we can then pivot into a Stream:
         */
        Map<String, Integer> someMap = new HashMap<>();

        // We can obtain a set of key-value pairs:
        Set<Map.Entry<String, Integer>> entries = someMap.entrySet();

        //We can also get the key set associated with the Map:
        Set<String> keySet = someMap.keySet();

        //Or we could work directly with the set of values:
        Collection<Integer> values = someMap.values();

        //These each give us an entry point to process those collections by obtaining streams from them:
        Stream<Map.Entry<String, Integer>> entryStream = entries.stream();
        Stream<Integer> valuesStream = values.stream();
        Stream<String> keysStream = keySet.stream();

        //Getting a Map‘s Keys Using Streams :
        Map<String, String> books = new HashMap<>();
        books.put(
                "978-0201633610", "Design patterns : elements of reusable object-oriented software");
        books.put(
                "978-1617291999", "Java 8 in Action: Lambdas, Streams, and functional-style programming");
        books.put("978-0134685991", "Effective Java");

        Optional<String> optional = books.entrySet().stream()
                .filter(e -> "Effective Java".equals(e.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();
        if (optional.equals(Optional.of("978-0134685991"))){
            System.out.println("equals");
        }

        // Retrieving Multiple Results :
        List<String> keyList = books.entrySet()
                .stream()
                .filter(e -> e.getValue().startsWith("Effective Java"))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("key map : " + keyList);

        // Getting a Map‘s Values Using Streams :
        List<String> titles = books.entrySet()
                .stream()
                .filter(e -> e.getKey().startsWith("978-0"))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        System.out.println("title map : " + titles);

        // create unmodifiable map of stream :
        Map<String, String> unmodifiableMap = books.entrySet()
                .stream()
                .filter(e -> e.getKey().startsWith("978-0"))
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("unmodifiable map : " + unmodifiableMap);


    }

}
