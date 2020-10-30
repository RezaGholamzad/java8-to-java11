# JIT Compiler : 

When we compile our Java program (e.g., using the javac command), we'll end up with our source code compiled into 
the binary representation of our code – a JVM bytecodehis bytecode is simpler and more compact than our source code, 
but conventional processors in our computers cannot execute it.

To be able to run a Java program, the JVM interprets the bytecode. Since interpreters are usually a lot slower than native code 
executing on a real processor, the JVM can run another compiler which will now compile our bytecode into the machine code that 
can be run by the processor. This so-called just-in-time compiler is much more sophisticated than the javac compiler, 
and it runs complex optimizations to generate high-quality machine code.

## More Detailed Look into the JIT Compiler : 

The JDK implementation by Oracle is based on the open-source OpenJDK project. This includes the HotSpot virtual machine, 
available since Java version 1.3. It contains two conventional JIT-compilers: the client compiler, also called 
C1 and the server compiler, called opto or C2.

C1 is designed to run faster and produce less optimized code, while C2, on the other hand, takes a little more time 
to run but produces a better-optimized code. The client compiler is a better fit for desktop applications since we 
don't want to have long pauses for the JIT-compilation. The server compiler is better for long-running server 
applications that can spend more time on the compilation.

## Tiered Compilation : 

Today, Java installation uses both JIT compilers during the normal program execution.

As we mentioned in the previous section, our Java program, compiled by javac, starts its execution in an interpreted mode. The JVM tracks each frequently called method and compiles them. In order to do that, it uses C1 for the compilation. But, the HotSpot still keeps an eye on the future calls of those methods. If the number of calls increases, the JVM will recompile these methods once more, but this time using C2.

This is the default strategy used by the HotSpot, called tiered compilation.

## The Server Compiler : 

Let's now focus for a bit on C2, since it is the most complex of the two. C2 has been extremely optimized and produces code that can compete with C++ or be even faster. The server compiler itself is written in a specific dialect of C++.

However, it comes with some issues. Due to possible segmentation faults in C++, it can cause the VM to crash. Also, no major improvements have been implemented in the compiler over the last several years. The code in C2 has become difficult to maintain, so we couldn't expect new major enhancements with the current design. With that in mind, the new JIT compiler is being created in the project named GraalVM.

# GraalVM : 

Project GraalVM is a research project created by Oracle. We can look at Graal as several connected projects: a new JIT compiler that builds on HotSpot and a new polyglot virtual machine. It offers a comprehensive ecosystem supporting a large set of languages (Java and other JVM-based languages; JavaScript, Ruby, Python, R,  C/C++, and other LLVM-based languages).

## Graal – a JIT Compiler Written in Java : 

Graal is a high-performance JIT compiler. It accepts the JVM bytecode and produces the machine code.

There are several key advantages of writing a compiler in Java. First of all, safety, meaning no crashes but exceptions instead and no real memory leaks. Furthermore, we'll have a good IDE support and we'll be able to use debuggers or profilers or other convenient tools. Also, the compiler can be independent of the HotSpot and it would be able to produce a faster JIT-compiled version of itself.

The Graal compiler was created with those advantages in mind. It uses the new JVM Compiler Interface – JVMCI to communicate with the VM. To enable the use of the new JIT compiler, we need to set the following options when running Java from the command line:

`-XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler`

**What this means is that we can run a simple program in three different ways: with the regular tiered compilers, with the JVMCI version of Graal on Java 10 or with the GraalVM itself.**

## JVM Compiler Interface : 

The JVMCI is part of the OpenJDK since JDK 9, so we can use any standard OpenJDK or Oracle JDK to run Graal.

What JVMCI actually allows us to do is to exclude the standard tiered compilation and plug in our brand new compiler (i.e. Graal) without the need of changing anything in the JVM.(enables us to integrate it with the HotSpot.)

The interface is quite simple. When Graal is compiling a method, it'll pass the bytecode of that method as the input to the JVMCI'. As an output, we'll get the compiled machine code. Both the input and the output are just byte arrays:

`interface JVMCICompiler {`

    byte[] compileMethod(byte[] bytecode);
    
`}`

In real-life scenarios, we'll usually need some more information like the number of local variables, the stack size, and the information collected from profiling in the interpreter so that we know how the code is running in practice.

Essentially, when calling the compileMethod() of the JVMCICompiler interface, we'll need to pass a CompilationRequest object. It'll then return the Java method we want to compile, and in that method, we'll find all the information we need.

## The Data Structure Behind Graal : 

Basic compiler's job, in general, is to act upon our program. This means that it must symbolize 
it with an appropriate data structure. Graal uses a graph for such a purpose, 
the so-called program-dependence-graph.

In a simple scenario, where we want to add two local variables, i.e., x + y, 
we would have one node for loading each variable and another node for adding them. 
Beside it, we'd also have two edges representing the data flow.

### Actual Graphs : 

We can examine the real Graal graphs with the IdealGraphVisualiser. To run it, we use the mx igv command. 
We also need to configure the JVM by setting the -Dgraal.Dump flag.

This data structure is sometimes called a sea-of-nodes, or a soup-of-nodes. We need to mention that 
the C2 compiler uses a similar data structure, so it's not something new, innovated exclusively for Graal.

It is noteworthy remember that Graal optimizes and compiles our program by modifying the above-mentioned 
data structure. We can see why it was an actually good choice to write the Graal JIT compiler in Java: 
a graph is nothing more than a set of objects with references connecting them as the edges. 
That structure is perfectly compatible with the object-oriented language, which in this case is Java.

## Ahead-of-Time Compiler Mode : 

It is also important to mention that we can also use the Graal compiler in the Ahead-of-Time compiler 
mode in Java 10. As we said already, the Graal compiler has been written from scratch. It conforms 
to a new clean interface, the JVMCI, which enables us to integrate it with the HotSpot. 
That doesn't mean that the compiler is bound to it though.

One way of using the compiler is to use a profile-driven approach to compile only the hot methods, 
but we can also make use of Graal to do a total compilation of all methods in an offline 
mode without executing the code.

The main reason why we would use Graal in this manner is to speed up startup time until 
the regular Tiered Compilation approach in the HotSpot can take over.

