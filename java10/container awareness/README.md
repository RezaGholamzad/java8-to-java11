# Container Awareness : 

VMs are now aware of being run in a Docker container and will extract container-specific configuration instead of querying the operating system itself – it applies to data like the number of CPUs and total memory that have been allocated to the container.

JVMs are now aware of being run in a Docker container and will extract container-specific configuration instead of querying the operating system itself – it applies to data like the number of CPUs and total memory that have been allocated to the container.

However, this support is only available for Linux-based platforms. This new support is enabled by default and can be disabled in the command line with the JVM option:

`-XX:-UseContainerSupport`

Also, this change adds a JVM option that provides the ability to specify the number of CPUs that the JVM will use:

`-XX:ActiveProcessorCount=count`

Also, three new JVM options have been added to allow Docker container users to gain more fine-grained control over the amount of system memory that will be used for the Java Heap:

`-XX:InitialRAMPercentage`
`-XX:MaxRAMPercentage`
`-XX:MinRAMPercentage`
