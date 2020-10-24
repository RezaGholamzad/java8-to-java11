package com.oracle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        /*
            The try-with-resources statement ensures that each resource is closed after the requirement finishes.
            Any object implementing java.lang.AutoCloseable or java.io.Closeable, interface can be used as a resource.

            Prior to Java 9, resources are to be declared before try or inside try statement as
            shown below in given example. In this example, we'll use BufferedReader as resource
            to read a string and then BufferedReader is to be closed.

            Java SE 9 is going to provide some improvements to this statement to avoid
            some more verbosity and improve some Readability.
         */
        Reader inputString = new StringReader("name");
        BufferedReader bufferedReader = new BufferedReader(inputString);
        try (bufferedReader){
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
