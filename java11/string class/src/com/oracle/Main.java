package com.oracle;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*
             This method simply repeats a string n times. It returns a string whose
             value is the concatenation of given string repeated N times.
         */
        String str = "1".repeat(5);
        System.out.println(str);
        System.out.println("******************************");

        /*
            This method indicates whether a string is empty or contains only white-spaces.
         */
        System.out.println("1".isBlank());
        System.out.println("".isBlank());
        System.out.println("       ".isBlank());
        System.out.println("******************************");

        /*
            This method takes care of removing leading and trailing white-spaces.
            We can be even more specific by removing just the leading characters by
            using String.stripLeading() or just the trailing characters by using
            String.stripTrailing().
         */
        System.out.println("   hi    ".strip());
        System.out.println("   hi    ".stripLeading());
        System.out.println("   hi    ".stripTrailing());
        System.out.println("******************************");

        /*
            This method helps in processing multi-line texts as a Stream.
         */
        String testString = "hello\nworld\nis\nexecuted";

        List<String> lines = new ArrayList<>();

        testString.lines().forEach(lines::add);

        System.out.println(lines);


    }
}
