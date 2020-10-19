# Interface Collection : 

The toArray() methods return an array that contains the elements stored in the collection. 
The first returns an array of Object. The second returns an array of elements that have the 
same type as the array specified as a parameter.Normally, the second form is more convenient 
because it returns the desired array type. **Beginning with JDK 11, a third form has been added 
that lets you specify a function that obtains the array.** These methods are more important than
it might at first seem. Often, processing the contents of a collection by using array-like 
syntax is advantageous. By providing a pathway between collections and arrays, 
you can have the best of both worlds.

*************************************************

# The List Interface :

Beginning with JDK 9, List includes the of() factory method, which has an umber of overloads. 
Each version returns an unmodifiable, value-based collection that is comprised of the arguments 
that it is passed. The primary purpose of of() is to provide a convenient, 
efficient way to create a small List collection. There are 12 overloads of of(). 
One takes no arguments and creates an empty list.
Ten overloads take from 1 to 10 arguments and create a list that contains the specified elements.
The final of() overload specifies a varargs parameter that takes an arbitrary number 
of elements or an array of elements.For all versions, null elements are not allowed. 
In all cases, the List implementation is unspecified.

*************************************************

# The Set Interface :

Beginning with JDK 9, Set includes the of() factory method, which has a number of overloads. 
Each version returns an unmodifiable, value-based collection that is comprised of the arguments 
that it is passed. The primary purpose of of() is to provide a convenient, efficient way to create 
a small Set collection. There are 12 overloads of of(). One takes no arguments and creates an empty set.
Ten overloads take from 1 to 10 arguments and create a list that contains the specified elements.
The final of( ) overload specifies a varargs parameter that takes an arbitrary number of 
elements or an array of elements.For all versions, null elements are not allowed. In all cases, 
the Set implementation is unspecified.Beginning with JDK 10, Set includes the static copyOf( ) 
method here :

`static <E> Set<E> copyOf(Collection <? extends E> from)`

It returns a set that contains the same elements as from. 
Null values are not allowed. The returned set is unmodifiable.