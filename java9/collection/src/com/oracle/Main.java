package com.oracle;

import java.util.*;

public class Main {

    /*
        Immutable Collection Factory:
        Java 9 came with static methods on the List, Set and Map interfaces for creating unmodifiable
        instances of those collections. Till now, to create immutable collection, programmers
        had to construct it, store it in a local variable, fill with add method and wrap into
        a unmodifiable collection.
    */
    private Set<String> createImmutableSet(){

        Set<String> names = new HashSet<>();

        names.add("john");
        names.add("George");
        names.add("Betty");

        return Collections.unmodifiableSet(names);


    }

    private Set<String> createImmutableSetInJava9(){

        //Now, you can :
        return Set.of("john", "George", "Betty");
    }

    private Map<String, Integer> createImmutableMapInJava9(){

        //Now, you can :
        return Map.of("john", 1, "George", 2, "Betty", 3);
    }

    private List<String> createImmutableListInJava9(){

        //Now, you can :
        return List.of("john", "George", "Betty");
    }

    /*
        Java 9 has added three important methods to Arrays class:
        Arrays.equals(), Arrays.compare() and Arrays.mismatch().
    */
    public static void arrayEqualsTest(){
        int[] existRows = {0, 1, 2, 3, 4, 5};
        int[] newRows = {3, 4, 5, 1, 2, 0};
        System.out.println(Arrays.equals(existRows, newRows));
        System.out.println(Arrays.equals(existRows, 1, 3, newRows, 3, 5));
        System.out.println(Arrays.equals(existRows, 3, 5, newRows, 0, 2));
    }

    public static void compareSliceArraysTest(){
        int[] tomMarks = {5, 6, 7, 8, 9, 10};
        int[] daisyMarks = {5, 6, 7, 10, 9, 10};
        int[] maryMarks = {5, 6, 7, 8};
        System.out.println(Arrays.compare(tomMarks, 0, 3, daisyMarks, 0, 3));
        System.out.println(Arrays.compare(tomMarks, 0, 4, maryMarks, 0, maryMarks.length));
        System.out.println(Arrays.compare(daisyMarks, 0, 4, maryMarks, 0, maryMarks.length));
    }

    public static void mismatchArraysTest() {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {1, 2, 3, 4, 5};
        int[] c = {1, 2, 4, 4, 5, 6};
        System.out.println(Arrays.mismatch(a, b));
        System.out.println(Arrays.mismatch(a, c));
        System.out.println(Arrays.mismatch(a, 0, 2, c, 0, 2));
        System.out.println(Arrays.mismatch(a, 0, 3, c, 0, 3));
        System.out.println(Arrays.mismatch(a, 2, a.length, c, 2, 5));
    }

    //Enumeration asIterator() Method :
    public static void asIteratorTest(){

        // create enumeration
        Enumeration Days;
        Vector week = new Vector();

        week.add("Sunday");
        week.add("Monday");
        week.add("Tuesday");
        week.add("Wednesday");
        week.add("Thursday");
        week.add("Friday");
        week.add("Saturday");
        Days = week.elements();

        // get the iterator
        Days.asIterator()
                .forEachRemaining(s -> System.out.println(s));
    }

    public static void main(String[] args) {
    }
}
