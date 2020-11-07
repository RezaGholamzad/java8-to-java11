package com.oracle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
	    /*
	        Using these overloaded methods, Java 11 aims to reduce a lot of boilerplate code
	        which makes much easier to read and write files.
	     */
        // reading/Writing Strings to and from the Files
        Path path = Files.writeString(Files.createTempFile("test", "txt"),
                "This was posted on JD");
        System.out.println(path);

        String s = Files.readString(path);
        System.out.println(s);

    }
}
