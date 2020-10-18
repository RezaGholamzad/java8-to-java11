# Modules : 

A Module is a set of packages designed for reuse. In Java 9, Java programs are Modules. 
Java Module is the main feature introduced in Java 9 release. In java, we have classes, 
packages and now modules, too. Before Java 9 module, Java programs are packages.

As we know that in Java, a package can access only public methods of another packages. 
In a large code base, where there is large number of packages, any package can access public method 
of any package which was not a better accessibility control. Now using Module, we can control 
the accessibility between packages for public methods of the classes.

A Package is the set of classes and a Module is the set of packages. Public methods of 
packages are only available for packages within the module. We can export the packages 
from its module to make it available to packages of other modules. So only exported packages 
from a module will be available to packages of other modules. We can also export packages to 
only friend modules and not for all.

Java 9 Platform Module System (JPMS) is a very important software engineering technology. 
Java 9 modularity is the result of Project Jigsaw and this will help developers to build 
and maintain large system in simpler way.

*******************************************************************************

# Module Basics : 

A module declaration is contained in a file called module-info.java. Thus, 
amodule is defined in a Java source file. This file is then compiled by javac into a class 
file and is known as its module descriptor. The module-info.java file must contain only a 
module definition. It cannot contain other types of declarations.A module declaration 
begins with the keyword module.

the Java API packages have been incorporated into modules. In fact, the modularization of the API is 
one of the primary benefits realized by the addition of the modules.Because of their special role, 
the API modules are referred to as platform modules, and their names all begin with the prefix java. 
Here are some examples: java.base, java.desktop, and java.xml. By modularizing the API, it 
becomes possible to deploy an application with only the packages that it requires, 
rather than the entire Java Runtime Environment (JRE). Because of the size of the full JRE, 
this is a very important improvement.

java.base module contains the packages such as java.lang, java.io, java.net, java.util etc. 
All the packages exported by java.base module are automatically available to all the modules.
Thus, in much the same way that java.lang is automatically available to all programs without 
the use of an import statement, the java.base module is automatically accessible to all 
module-based programs without explicitly requesting it.

At the foundation of a module’s capabilities are two key features. 
The first is a module’s ability to specify that it requires another module. In other words, 
one module can specify that it depends on another. A dependence relationship is specified by use 
of a requires statement. By default, the presence of the required module is checked at both compile time 
and at run time, This means that the required module must be present in order for the current module 
to compile. The second key feature is a module’s ability to control which, if any, 
of its packages are accessible by another module. This is accomplished by use of the exports keyword.
The public and protected types within a package are accessible to other modules only if they 
are explicitly exported. However, if a package within a module is not exported, then it is private 
to that module, including all of its public types.

*******************************************************************************

# backward capability : 

Support for legacy code is provided by two key features. 

1) The first is the unnamed module. When you use code that is not part of a named module, 
it automatically becomes part of the unnamed module. 
The unnamed module has two important attributes. First, all of the packages in the unnamed module 
are automatically exported. Second, the unnamed module can access any and all other modules. 
Thus, when a program does not use modules, all API modules in the Java platform are automatically 
accessible through the unnamed module.

2) The second key feature that supports legacy code is the automatic use of the class path, 
rather than the module path. When you compile a program that does not use modules, 
the class path mechanism is employed, just as it has been since
not use modules, the class path mechanism is employed, just as it has been since Java’s original release. 
As a result, the program is compiled and run in the same way it was prior to the advent of modules.
Because of the unnamed module and the automatic use of the class path, there was no need to declare 
any modules. They run properly whether you compile them with a modern compiler or an earlier one, 
such as JDK8. Thus, even though modules are a feature that has significant impact on Java, 
compatibility with legacy code is maintained. This approach also provides a smooth, 
nonintrusive, nondisruptivetransition path to modules. Thus, it enables you to move a legacy application 
to modules at your own pace. Furthermore, it allows you to avoid the use of modules when they are not needed.

*******************************************************************************

# Exporting to a Specific Module : 

The basic form of the exports statement makes a package accessible to any and all other modules. 
This is often exactly what you want. However, in some specialized development situations, 
it can be desirable to make a package accessible to only a specific set of modules, 
not all other modules.Adding a to clause to the exports statement provides a means by which 
this can be accomplished.In an exports statement, the to clause specifies a list of one or 
more modules that have access to the exported package. Furthermore, only those modules named 
in the to clause will have access : 

exports packageName to moduleNames;

*******************************************************************************

# requires transitive : 

Consider a situation in which there are three modules, A, B, and C, that have the following dependences:

•  A requires B.

•  B requires C.

Given this situation, it is clear that since A depends on B and B depends on C, Ahas an indirect 
dependence on C. As long as A does not directly use any of the contents of C, then you can simply 
have A require B in its module-info file, and have B export the packages required by A in its 
module-info file.
Although this works as long as A does not need to use anything defined in C, a problem occurs 
if A does want to access a type in C. The first solution is to simply add a requires C 
statement to A’s file. This solution certainly works, but if B will be used by many modules, 
you must add requires C to all module definitions that require B.his is not only tedious,it is 
also error prone.You can create an implied dependence on C. Implied dependence is also 
referred to as implied readability.To create an implied dependence, add the transitive keyword 
after requires in the clause that requires the module upon which an implied readability is needed.

module B{

    export somePack;
    
    requires transitive C;
}

Here, C is now required as transitive. After making this change, any module that depends on 
B will also, automatically, depend on C. Thus, A would automatically have access to C.

*******************************************************************************

# Module Graphs : 

A term you are likely to encounter when working with modules is module graph.During compilation, 
the compiler resolves the dependence relationships between modules by creating a module graph 
that represents the dependences. The process ensures that all dependences are resolved, 
including those that occur indirectly. For example, if module A requires module B, and B requires 
moduleC, then the module graph will contain module C even if A does not use it directly.

*******************************************************************************

# open Modules : 

As you learned earlier in this chapter, by default, the types in a module’spackages are accessible 
only if they are explicitly exported via an exports statement. While this is usually what you will 
want, there can be circumstances in which it is useful to enable run-time access to all packages 
in the module,whether a package is exported or not. To allow this, you can create an openmodule. 
An open module is declared by preceding the module keyword with the open modifier, as shown here :

open module moduleName { 

// module definition

}

In an open module, types in all its packages are accessible at run time.Understand, however, 
that only those packages that are explicitly exported are available at compile time. Thus, 
the open modifier affects only run-time accessibility. The primary reason for an open module 
is to enable the packages in the module to be accessed through reflection.

*******************************************************************************

# The opens Statement : 

It is possible for a module to open a specific package for run-time access by other 
modules and for reflective access rather than opening an entire module. Todo so, 
use the opens statement, shown here:

opens packageName;

Here, packageName specifies the package to open. It is also possible to include a to clause, 
which names those modules for which the package is opened. It is important to understand 
opens does not grant compile-time access. It is used only to open a package for run-time and 
reflective access. However, you can both export and open a module. 
One other point: an opens statement cannot
be used in an open module. Remember, all packages in an open module are already open.

*******************************************************************************

#requires static : 

As you know, requires specifies a dependence that, by default, is enforced both during 
compilation and at run time. However, it is possible to relax this requirement in such 
a way that a module is not required at run time. This is accomplished by use of the static 
modifier in a requires statement.

requires static mymod;

In this case, the addition of static makes mymod optional at run time. This can be helpful 
in a situation in which a program can utilize functionality if it is present, 
but not require it.


