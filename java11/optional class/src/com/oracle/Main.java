package com.oracle;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        /*
            isEmpty() method is reverse of isPresent() method and returns false
            if a value is present, otherwise true.
         */
	    String currentTime = null;
        System.out.println(Optional.ofNullable(currentTime).isEmpty());
        currentTime = "java";
        System.out.println(Optional.ofNullable(currentTime).isEmpty());
    }
}
