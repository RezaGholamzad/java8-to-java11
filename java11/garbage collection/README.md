# An Introduction to Garbage Collection : 

The OpenJDK has three collectors suitable for production, another that is deprecated in JDK 11 but still  quite  popular  in  JDK  8,  
and  some  experimental  collectors  that  will  (ideally)  be production-ready  in  future  releases.  
Other  Java  implementations  such  as  Open  J9  orthe Azul JVM have their own collectors.
The  performance  characteristics  of  all  these  collectors  are  quite  different.

## Garbage Collection Overview : 

When  the  GC  algorithm  finds  unused  objects,  the  JVM  can  free  the  memory  occupied  by  those  objects  and  use  it  to  allocate  additional  objects.  However,  it  is  usually insufficient simply to keep track of that free memory and use it for future allocations;at some point, memory must be compacted to prevent memory fragmentation.

The  implementations  are  a  little  more  detailed,  but  the  performance  of  GC  is  dominated by these basic operations: finding unused objects, making their memory available, and compacting the heap. Different collectors take different approaches to these operations,  particularly  compaction:  some  algorithms  delay  compaction  until  absolutely necessary, some compact entire sections of the heap at a time, and some compact  the  heap  by  relocating  small  amounts  of  memory  at  a  time.  These  different approaches are why different algorithms have different performance characteristics.

It  is  simpler  to  perform  these  operations  if  no  application  threads  are  running  whilethe  garbage  collector  is  running.  Java  programs  are  typically  heavily  multithreaded,and the garbage collector itself often runs multiple threads. This discussion considers two logical groups of threads: those performing application logic (often called mutator threads, since they are mutating objects as part of the application logic) and those performing GC. When GC threads track object references or move objects around in memory,  they  must  make  sure  that  application  threads  are  not  using  those  objects.This is particularly true when GC moves objects around: the memory location of the object changes during that operation, and hence no application threads can be accessing the object.

The pauses when all application threads are stopped are called stop-the-world pauses.These pauses generally have the greatest impact on the performance of an application,and minimizing those pauses is one important consideration when tuning GC.

## Generational Garbage Collectors : 

Though  the  details  differ  somewhat,  most  garbage  collectors  work  by  splitting  the heap into generations. These are called the old (or tenured) generation and the young generation. The young generation is further divided into sections known as eden andthe survivor spaces (though sometimes, eden is incorrectly used to refer to the entire young generation).

This kind of operation is common in Java, so the garbage collector is designed to take advantage  of  the  fact  that  many  (and  sometimes  most)  objects  are  only  used  temporarily. This is where the generational design comes in. Objects are first allocated in the  young  generation,  which  is  a  subset  of  the  entire  heap.  When  the  young  generation fills up, the garbage collector will stop all the application threads and empty out the young generation. Objects that are no longer in use are discarded, and objects that are  still  in  use  are  moved  else where.  This  operation  is  called  a  minor  GC  or  a  youngGC.

The advantage arises from the way objects are allocated in the young genera‐tion. Objects are allocated in eden (which encompasses the vast majority of the young generation). When the young generation is cleared during a collection, all objects ineden are either moved or discarded: objects that are not in use can be discarded, and objects in use are moved either to one of the survivor spaces or to the old generation.Since   all   surviving   objects   are   moved,   the   young   generation   is   automatically compacted when it is collected: at the end of the collection, eden and one of the survivor  spaces  are  empty,  and  the  objects  that  remain  in  the  young  generation  are  compacted within the other survivor space.Common  GC  algorithms  have  stop-the-world  pauses  during  collection  of  the  young generation.

As objects are moved to the old generation, eventually it too will fill up, and the JVM will need to find any objects within the old generation that are no longer in use and discard them. This is where GC algorithms have their biggest differences. The simpler algorithms  stop  all  application  threads,  find  the  unused  objects,  free  their  memory,and then compact the heap. This process is called a full GC, and it generally causes a relatively long pause for the application threads.

On  the  other  hand,  it  is  possible though  more  computationally  complex to  find unused  objects  while  application  threads  are  running.  Because  the  phase  where  they scan  for  unused  objects  can  occur  without  stopping  application  threads,  these  algorithms are called concurrent collectors. They are also called low pause (and sometimes,incorrectly, pauseless) collectors since they minimize the need to stop all the applica‐tion threads. Concurrent collectors also take different approaches to compacting theold generation.

When  using  a  concurrent  collector,  an  application  will  typically  experience  fewer(and much shorter) pauses. The biggest trade-off is that the application will use more CPU overall. Concurrent collectors can also be more difficult to tune in order to get their  best  performance  (though  in  JDK  11,  tuning  concurrent  collectors  like  the  G1GC  is  much  easier  than  in  previous  releases,  which  reflects  the  engineering  progress that has been made since the concurrent collectors were first introduced).

As  you  consider  which  garbage  collector  is  appropriate  for  your  situation,  thinkabout the overall performance goals that must be met. Trade-offs exist in every situa‐tion. In an application (such as a REST server) measuring the response time of indi‐vidual requests, consider these points:

• The individual requests will be impacted by pause times and more importantly by long pause times for full GCs. If minimizing the effect of pauses on response times is the goal, a concurrent collector may be more appropriate.

• If the average response time is more important than the outliers (i.e., the 90th%)response time), a nonconcurrent collector may yield better results.

• The benefit of avoiding long pause times with a concurrent collector comes at the expense of extra CPU usage. If your machine lacks the spare CPU cycles needed by a concurrent collector, a nonconcurrent collector may be the better choice.

Similarly,  the  choice  of  garbage  collector  in  a  batch  application  is  guided  by  the  fol‐lowing trade-off:

• If enough CPU is available, using the concurrent collector to avoid full GC pauses will allow the job to finish faster.

• If  CPU  is  limited,  the  extra  CPU  consumption  of  the  concurrent  collector  will cause the batch job to take more time.

### Quick Summary : 

• GC  algorithms  generally  divide  the  heap  into  old  and  young generations.

• GC algorithms generally employ a stop-the-world approach to clearing objects from the young generation, which is usually aquick operation.

• Minimizing the effect of performing GC in the old generationis a trade-off between pause times and CPU usage.

## GC Algorithms :

### The serial garbage collector : 

The serial garbage collector is the simplest of the collectors. This is the default collec‐tor if the application 
is running on a client-class machine (32-bit JVMs on Windows)or on a single-processor machine. 
At one point, the serial collector seemed like it was destined  for  the  trash  can,  but  containerization  has  changed  that:  
virtual  machinesand Docker containers with one core (even a hyper-threaded core that appears as twoCPUs) have made this algorithm more 
relevant again.

The serial collector uses a single thread to process the heap. It will stop all applicationthreads as the heap is 
processed (for either a minor or full GC). During a full GC, itwill fully compact the old generation.

The serial collector is enabled by using the -XX:+UseSerialGC flag (though usually itis the default in those cases where it might be used). Note that unlike with most 
JVMflags, the serial collector is not disabled by changing the plus sign to a minus sign (i.e.,by  specifying  -XX:-UseSerialGC).  
On  systems  where  the  serial  collector  is  thedefault, it is disabled by specifying a different GC algorithm.

### The throughput collector :

In JDK 8, the throughput collector is the default collector for any 64-bit machine withtwo  or  more  CPUs.  The  throughput  collector  uses  multiple  threads  to  collect  theyoung generation, which makes minor GCs much faster than when the serial collec‐tor is used. This uses multiple threads to process the old generation as well. Because ituses multiple threads, the throughput collector is often called the parallel collector.

The  throughput  collector  stops  all  application  threads  during  both  minor  and  fullGCs, and it fully compacts the old generation during a full GC. Since it is the defaultin most situations where it would be used, it needn’t be explicitly enabled. To enable itwhere necessary, use the flag -XX:+UseParallelGC.

Note  that  old  versions  of  the  JVM  enabled  parallel  collection  in  the  young andold generations separately, so you might see references to the flag-XX:+UseParallelOldGC.  This  flag  is  obsolete  (though  it  still  functions,  and  youcould disable this flag to collect only the young generation in parallel if for some rea‐son you really wanted to).

### The G1 GC collector : 

The G1 GC (or garbage first garbage collector) uses a concurrent collection strategy tocollect the heap with minimal pauses. It is the default collector in JDK 11 and later for64-bit JVMs on machines with two or more CPUs.

G1 GC divides the heap into regions, but it still considers the heap to have two gener‐ations. Some of those regions make up the young generation, and the young genera‐tion  is  still  collected  by  stopping  all  application  threads  and  moving  all  objects  thatare  alive  into  the  old  generation  or  the  survivor  spaces.  (This  occurs  using  multiplethreads.

In G1 GC, the old generation is processed by background threads that don’t need tostop  the  application  threads  to  perform  most  of  their  work.  Because  the  old  genera‐tion  is  divided  into  regions,  G1  GC  can  clean  up  objects  from  the  old  generation  bycopying  from  one  region  into  another,  which  means  that  it  (at  least  partially)  com‐pacts the heap during normal processing. This helps keep G1 GC heaps from becom‐ing fragmented, although that is still possible.

The trade-off for avoiding the full GC cycles is CPU time: the (multiple) backgroundthreads G1 GC uses to process the old generation must have CPU cycles available atthe same time the application threads are running.

G1 GC is enabled by specifying the flag -XX:+UseG1GC. In most cases, it is the defaultin JDK 11, and it is functional in JDK 8 as well—particularly in later builds of JDK 8,which  contains  many  important  bug  fixes  and  performance  enhancements  that  havebeen  back-ported  from  later  releases.  Still,  as  you’ll  see  when  we  explore  G1  GC  indepth, one major performance feature is missing from G1 GC in JDK 8 that can makeit unsuitable for that release.

*reference : chapter 5 - Java Performance - In-Depth Advice for Tuning and Programming Java 8, 11, and Beyond. by Scott Oaks*
