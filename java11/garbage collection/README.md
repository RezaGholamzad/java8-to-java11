# An Introduction to Garbage Collection : 

The OpenJDKhas three collectors suitable for production, another that is deprecated in JDK 11 but still  quite  popular  in  JDK  8,  
and  some  experimental  collectors  that  will  (ideally)  beproduction-ready  in  future  releases.  
Other  Java  implementations  such  as  Open  J9  orthe Azul JVM have their own collectors.
The  performance  characteristics  of  all  these  collectors  are  quite  different.

## The serial garbage collector : 

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

## The throughput collector :

In JDK 8, the throughput collector is the default collector for any 64-bit machine withtwo  or  more  CPUs.  The  throughput  collector  uses  multiple  threads  to  collect  theyoung generation, which makes minor GCs much faster than when the serial collec‐tor is used. This uses multiple threads to process the old generation as well. Because ituses multiple threads, the throughput collector is often called the parallel collector.

The  throughput  collector  stops  all  application  threads  during  both  minor  and  fullGCs, and it fully compacts the old generation during a full GC. Since it is the defaultin most situations where it would be used, it needn’t be explicitly enabled. To enable itwhere necessary, use the flag -XX:+UseParallelGC.

Note  that  old  versions  of  the  JVM  enabled  parallel  collection  in  the  young andold generations separately, so you might see references to the flag-XX:+UseParallelOldGC.  This  flag  is  obsolete  (though  it  still  functions,  and  youcould disable this flag to collect only the young generation in parallel if for some rea‐son you really wanted to).

## The G1 GC collector : 

The G1 GC (or garbage first garbage collector) uses a concurrent collection strategy tocollect the heap with minimal pauses. It is the default collector in JDK 11 and later for64-bit JVMs on machines with two or more CPUs.

G1 GC divides the heap into regions, but it still considers the heap to have two gener‐ations. Some of those regions make up the young generation, and the young genera‐tion  is  still  collected  by  stopping  all  application  threads  and  moving  all  objects  thatare  alive  into  the  old  generation  or  the  survivor  spaces.  (This  occurs  using  multiplethreads.

In G1 GC, the old generation is processed by background threads that don’t need tostop  the  application  threads  to  perform  most  of  their  work.  Because  the  old  genera‐tion  is  divided  into  regions,  G1  GC  can  clean  up  objects  from  the  old  generation  bycopying  from  one  region  into  another,  which  means  that  it  (at  least  partially)  com‐pacts the heap during normal processing. This helps keep G1 GC heaps from becom‐ing fragmented, although that is still possible.

The trade-off for avoiding the full GC cycles is CPU time: the (multiple) backgroundthreads G1 GC uses to process the old generation must have CPU cycles available atthe same time the application threads are running.

G1 GC is enabled by specifying the flag -XX:+UseG1GC. In most cases, it is the defaultin JDK 11, and it is functional in JDK 8 as well—particularly in later builds of JDK 8,which  contains  many  important  bug  fixes  and  performance  enhancements  that  havebeen  back-ported  from  later  releases.  Still,  as  you’ll  see  when  we  explore  G1  GC  indepth, one major performance feature is missing from G1 GC in JDK 8 that can makeit unsuitable for that release.
