Beginning with JDK 8, the classes called Optional, OptionalDouble,OptionalInt, and OptionalLong offer 
a way to handle situations in which a value may or may not be present. In the past, you would normally 
use the value null to indicate that no value is present. However, this can lead to null pointerexceptions 
if an attempt is made to dereference a null reference. As a result,frequent checks for a null value were 
necessary to avoid generating an exception. These classes provide a better way to handle such situations. 
One other point: These classes are value-based; as such they are immutable and various restrictions apply, 
such as not using instances for synchronization and avoiding any use of reference equality. 
Consult the Java documentation for the latest details on value-based classes.
The first and most general of these classes is Optional.

class Optional<T>

Here, T specifies the type of value stored. It is important to understand that anOptional instance can 
either contain a value of type T or be empty. In other words, an Optional object does not necessarily 
contain a value. Optional does not define any constructors, but it does define several methods that 
let you work with Optional objects.

At the foundation of Optional are isPresent() and get(). You can determine if a value is present by 
calling isPresent(). If a value is available,it will return true. Otherwise, false is returned. 
If a value is present in an Optional instance, you can obtain it by calling get(). However, 
if you call get()on an object that does not contain a value, NoSuchElementException is thrown.
For this reason, you should always first confirm that a value is present before calling get() on an 
Optional object. **Beginning with JDK 10, the parameterless version of orElseThrow() can be used instead 
of get(), and its name adds clarity to the operation.**

Of course, having to call two methods to retrieve a value adds overhead to each access. 
Fortunately, Optional defines methods that combine the check for a value with the retrieval of the value. 
One such method is orElse(). If the object on which it is called contains a value, the value is returned. 
Otherwise, a default value is returned.Optional does not define any constructors. Instead, you will use 
one of its methods to create an instance. For example, you can create an Optional instance with a 
specified value by using of(). You can create an instance of Optional that does not contain a 
value by using empty().

**In java 9, three new methods are added to improve its functionality :**

**1) stream() :**

Since Java 9 we can treat the Optional instance as a Stream and utilize all methods from Stream API. 
This makes it also much more convenient to work with streams of Optionals.

If a value is present, returns a sequential Stream containing only that value, 
otherwise returns an empty Stream.

**2) ifPresentOrElse() :**

If a value is present, performs the given action with the value, 
otherwise performs the given empty-based action.

**3) or() :**

If a value is present, returns an Optional describing the value, otherwise returns an Optional 
produced by the supplying function.
