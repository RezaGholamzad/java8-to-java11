package com.oracle;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("alex");
        names.add("brian");
        names.add("charles");

        String[] names1 = names.toArray(new String[0]); //Before Java 11

        String[] names2 = names.toArray(String[]::new); //Since Java 11
    }
}
