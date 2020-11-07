# Local Variable Syntax for Lambda Parameters : 

One enhancement in Java 11 covered in JEP 323 is to allow var to be used when 
declaring the formal parameters of implicitly typed lambda expressions.

A lambda expression may be implicitly typed, where the types of all its formal parameters are inferred:

`(x, y) -> x.process(y)    // implicitly typed lambda expression`

For uniformity with local variables, Java 11 now allow 'var' for the formal parameters 
of an implicitly typed lambda expression:

`(var x, var y) -> x.process(y)   // implicit typed lambda expression`

**But why is this needed when we can just skip the type in the lambda?**

If you need to apply an annotation just as **@Nullable or @NotNull**, 
you cannot do that without defining the type.

Also we can use '**final**' with var:

`(final var x) -> Math.pow(x, 4)`

**Limitation of this feature:**
 
You must specify the type var on all parameters or none.