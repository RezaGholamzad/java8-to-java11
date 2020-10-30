package com.oracle;

public class Main {

    /*
        Graal itself is executed by the VM, so it'll first be interpreted and JIT-compiled when it becomes hot.

        Now, we'll compile it and run it:

        javac CountUppercase.java
        java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler

        We can see that it takes more time in the beginning. That warm-up time depends on various factors,
        such as the amount of multi-threaded code in the application or the number of threads the VM uses.
        If there are fewer cores, the warm-up time could be longer.

        If we want to see the statistics of Graal compilations we need to add the following
        flag when executing our program:

        -Dgraal.PrintCompilation=true

        This will show the data related to the compiled method, the time taken, the bytecodes processed
        (which includes inlined methods as well), the size of the machine code produced, and
        the amount of memory allocated during compilation.
     */

    public static void main(String[] args) {
        String sentence = String.join(" ", args);

        for (int iter = 0; iter < 1; iter++) {

            long total = 0, start = System.currentTimeMillis(), last = start;

            /*
                test Graal vs Tiered Compilation:

                1) i < 100_000_000 and without graal
                2) i < 100_000_000 and with graal
                3) i < 10_000_000 and without graal
                4) i < 10_000_000 and with graal
             */
            for (int i = 1; i < 100_000_000; i++) {

                total += sentence
                        .chars()
                        .filter(Character::isUpperCase)
                        .count();

                if (i % 1_000_000 == 0) {
                    long now = System.currentTimeMillis();
                    System.out.printf("%d (%d ms)%n", i / 1_000_000, now - last);
                    last = now;
                }

            }
            System.out.printf("total: %d (%d ms)%n", total, System.currentTimeMillis() - start);
        }
    }
}
