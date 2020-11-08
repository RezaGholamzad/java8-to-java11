# Nested Based Access Control : 

When we have nested classes in a class, after compilation different files are created. 
Before Java 11 for accessing the private member of the nested class or outer class we 
need to use the synthetic generated accessibility-broadening bridge methods.

But in Java 11 for accessing Classes and Interfaces Oracle has introduced another 
access control context known as nest.

Basically, the nest term defines a new access control context that allows classes that 
are logically part of the same code entity, but which are compiled with distinct class files, 
to access each other’s private members without the need for compilers to insert 
accessibility-broadening bridge methods (Java documentation).

Java 11 introduced JVM level support for private access classes using two new terms 
“NestMembers” and “NestHost”.’’NestMembers” corresponds to nested classes and 
“NestHost” corresponds to the enclosing outer class. From Java 11 onwards we use 
these terms for bridging to use the private member of a class as well.

**it does not effect the end user but helps at a JVM level.**