package com.oracle;

public class Main {

    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "سلام";
        String s3 = "helloسلام";

        System.out.println(s1.getBytes().length);
        System.out.println(s2.getBytes().length);
        System.out.println(s3.getBytes().length);

    }
}
