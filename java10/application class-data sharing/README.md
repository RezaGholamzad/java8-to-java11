# Application Class-Data Sharing : 

Class-Data Sharing, introduced in JDK 5, allows a set of classes to be pre-processed into a shared archive file that can then be memory-mapped at runtime 
to reduce startup time which can also reduce dynamic memory footprint when multiple JVMs share the same archive file.

The general idea was that, when the JVM first launched, anything loaded by the bootstrap classloader was serialized and stored in a file on disk that could be reloaded on future launches of the JVM. This meant that multiple instances of the JVM shared the class metadata, so it wouldn’t have to load them all every time.

CDS only allowed the bootstrap class loader, limiting the feature to system classes only. Application CDS (AppCDS) extends CDS to allow the built-in 
system class loader (a.k.a., the “app class loader”), the built-in platform class loader, and custom class loaders to load archived classes. 
This makes it possible to use the feature for application classes.

## The advantages of loading classes from the shared archive :

1) The classes stored in the archive are preprocessed, which means that the JVM memory mapping is stored in the archive too. It reduces the overhead of class-loading when a JVM instance starts.

2) The memory region can even be shared between the JVM instances running on the same computer, which reduces overall memory consumption by eliminating the need to replicate the same information in each instance.

The new JVM functionality allows us to create a list of classes to be shared, then use this list to create a shared archive, and use the shared archive to fast-load archived classes into memory.

## creating and using an archive : 

We can use the following steps to make use of this feature:

1. Get the list of classes to archive

    The following command will dump the classes loaded by the HelloWorld application into hello.lst:

    `$ java -Xshare:off -XX:+UseAppCDS -XX:DumpLoadedClassList=hello.lst \ 
      -cp hello.jar HelloWorld`
    
2. Create the AppCDS archive

    Following command creates hello.jsa (jsa = java shared archive) using the hello.lst as input:

    `$ java -Xshare:dump -XX:+UseAppCDS -XX:SharedClassListFile=hello.lst \
        -XX:SharedArchiveFile=hello.jsa -cp hello.jar`
    
3. Use the AppCDS archive

    Following command starts the HelloWorld application with hello.jsa as input:

    `$ java -Xshare:on -XX:+UseAppCDS -XX:SharedArchiveFile=hello.jsa \
        -cp hello.jar HelloWorld`
    
AppCDS was a commercial feature in Oracle JDK for JDK 8 and JDK 9. Now it is open sourced and made publicly available.
