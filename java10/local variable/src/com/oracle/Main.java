package com.oracle;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String message = "Good bye, Java 9";

        var newMessage = "Hello, Java 10";

        /*
            We don't provide the data type of message. Instead, we mark the message as a var,
            and the compiler infers the type of message from the type of the initializer
            present on the right-hand side.

            Note that this feature is available only for local variables with the initializer.
            It cannot be used for member variables, method parameters, return types, etc –
            the initializer is required as without which compiler won't be able to infer the type.

            Another thing to note is that var is not a keyword – this ensures backward compatibility
            for programs using var say, as a function or variable name.
            var is a reserved type name, just like int.

            Finally, note that there is no runtime overhead in using var nor does it make
            Java a dynamically typed language. The type of the variable is still
            inferred at compile time and cannot be changed later.
         */

        Map<Integer, String> map = new HashMap<>();

        //This can now be rewritten as:
        //This also helps to focus on the variable name rather than on the variable type.
        var idToNameMap = new HashMap<Integer, String>();

        /*
            As mentioned earlier, var won't work without the initializer:
            var n; // error: cannot use 'var' on variable without initializer

            Nor would it work if initialized with null:
            var emptyList = null; // error: variable initializer is 'null'

            It won't work for non-local variables:
            public var = "hello"; // error: 'var' is not allowed here

            Lambda expression needs explicit target type, and hence var cannot be used:
            var p = (String s) -> s.length() > 10; // error: lambda expression needs an explicit target-type

            Same is the case with the array initializer:
            var arr = { 1, 2, 3 }; // error: array initializer needs an explicit target-type

            Usage of var may also give unexpected result :
            Using var with non-denotable types could cause unexpected error..
            if we use var with the anonymous class instance:

            var obj = new Object() {};

            Now, if we try to assign another Objectto obj, we would get a compilation error:

            obj = new Object(); // error: Object cannot be converted to <anonymous Object>

            This is because the inferred type of obj isn't Object.

         */

    }
}
