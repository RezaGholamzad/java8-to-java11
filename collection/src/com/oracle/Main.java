package com.oracle;

import java.util.*;

public class Main {

    private Set<String> createImmutableSet(){

        /*
            Immutable Collection Factory:
            Java 9 came with static methods on the List, Set and Map interfaces for creating unmodifiable
            instances of those collections. Till now, to create immutable collection, programmers
            had to construct it, store it in a local variable, fill with add method and wrap into
            a unmodifiable collection.
         */
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

    public static void main(String[] args) {


    }
}
