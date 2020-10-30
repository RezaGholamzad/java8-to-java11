# Application Class-Data Sharing : 

Class-Data Sharing, introduced in JDK 5, allows a set of classes to be pre-processed into a shared archive file that can then be memory-mapped at runtime 
to reduce startup time which can also reduce dynamic memory footprint when multiple JVMs share the same archive file.

CDS only allowed the bootstrap class loader, limiting the feature to system classes only. Application CDS (AppCDS) extends CDS to allow the built-in 
system class loader (a.k.a., the “app class loader”), the built-in platform class loader, and custom class loaders to load archived classes. 
This makes it possible to use the feature for application classes.

We can use the following steps to make use of this feature:

1. Get the list of classes to archive

    The following command will dump the classes loaded by the HelloWorld application into hello.lst:

    `$ java -Xshare:off -XX:+UseAppCDS -XX:DumpLoadedClassList=hello.lst \ 
      -cp hello.jar HelloWorld`
    
2. Create the AppCDS archive

    Following command creates hello.js a using the hello.lst as input:

    `$ java -Xshare:dump -XX:+UseAppCDS -XX:SharedClassListFile=hello.lst \
        -XX:SharedArchiveFile=hello.jsa -cp hello.jar`
    
3. Use the AppCDS archive

    Following command starts the HelloWorld application with hello.jsa as input:

    `$ java -Xshare:on -XX:+UseAppCDS -XX:SharedArchiveFile=hello.jsa \
        -cp hello.jar HelloWorld`
    
AppCDS was a commercial feature in Oracle JDK for JDK 8 and JDK 9. Now it is open sourced and made publicly available.
