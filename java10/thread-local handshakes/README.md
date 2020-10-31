# Thread-Local Handshakes : 

During serviceability operations, like collecting stack traces for all threads or performing garbage collections, when the JVM needed to pause one thread, 
it needed to stop them all. Sometimes, these are referred to as “stop-the-world” pauses. This was due to the JVM wanting to create a global safe-point 
from which all application threads could begin again once the JVM was done.

Suppose that you need to pause a particular thread, executing a callback on it. Prior to thread-local handshakes, there wasn't any way to do that. The norm was to perform a global VM safepoint, which pauses all of the executing threads (and what a waste that is, if you meant to pause only one thread). With thread-local handshakes, it is possible to stop individual threads.

By aiming to reduce global VM safepoints, thread-local handshakes will reduce JVM latency and improve its efficiency.

Thread-local handshakes are a JVM implementation feature that can't be used directly by developers.
