# Heap Allocation on Alternative Memory Devices :

The goal of this change is to enable the HotSpot VM to allocate the Java object heap on an alternative memory device, such as an Non-Volatile DIMM (NV-DIMM), specified by the user.

To allocate the heap in such memory we can add a new option, **-XX:AllocateHeapAt=\<path>**. This option would take a path to the file system and use memory mapping to achieve the desired result of allocating the object heap on the memory device. The existing heap related flags such as -Xmx, -Xms, etc., and garbage-collection related flags would continue to work as before.

An immediate use case is being able to allocate heap on a Non-Volatile DIMM (NVDIMM) module, which is commonly used in big Data applications.

Another use case is where many JVM processes are running on the same machine. In this case, it might be good to have processes that require a lower read latency map to DRAM and the remaining processes mapped to NVDIMM.
