# Issue with Process API until Java 8 : 

What if I want to develop a device driver software or Operating System? Interaction with system 
level component was not suitable until Java 8. The process communication with Java was extremely 
difficult since it was not a machine friendly language. In order to communicate with the process, 
we need to write kilometres of code and most of which was native code, not written in Java and 
you need to use a lot of jar files. Therefore, until Java 8, the communication with 
the underlying operating system processes was not an easy task.

Also, the way process management and handling was done varied from operating system 
to operating system.

# Resolution with Java 9 :

Process Class : Under this, several new methods were introduced such as pid(), info() etc.

ProcessBuilder : This feature is used to create our own java process. You can control one 
java or non-java process using another java process.

Process Handle : It is used to handle the processes like access current running process, 
access the parent/child of a particular process.

Process Handle Info : It is the inner interface inside Process Handle interface. 
It is used to provide the complete information of a current running process.