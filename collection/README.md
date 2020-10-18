# Interface Collection : 

The root interface in the collection hierarchy. A collection represents a group of objects, known as its elements.
Collection is a generic interface that has this declaration: 

`interface Collection<E>`

The interfaces that underpin collections : 

1) Deque : extends Queue to handle a double-ended queue.

2) List : extends Collection to handle sequences (lists of objects).

3) NavigableSet : extends SortedSet to handle retrieval of elements closet-match searches.

4) Queue : extends collection to handle special types of list in which elements are removed only from the head

5) Set : extends collection to handle sets, which must contain unique elements.

6) SortedSet : extends Set to handle sorted sets.

In addition to the collection interfaces, collections also use the Comparator,RandomAccess, Iterator, 
ListIterator, and Spliterator interfaces.

Briefly, Comparator defines how two objects are compared; Iterator, ListIterator, and Spliterator 
enumerate the objects within a collection. By implementing RandomAccess, a list indicates that 
it supports efficient, random access to its elements.

To provide the greatest flexibility in their use, the collection interfaces allow some methods 
to be optional. The optional methods enable you to modify the contents of a collection. 
Collections that support these methods are called modifiable. Collections that do not allow 
their contents to be changed are called unmodifiable. If an attempt is made to use one of 
these methods on an unmodifiable collection, an UnsupportedOperationException is thrown. 
All the built-in collections are modifiable.

Collection extends the Iterable interface. This means that all collections can be cycled through 
by use of the for-each style for loop. (Recall that only classes that
implement Iterable can be cycled through by the for.)

## Exceptions : 

Collection declares the core methods that all collections will have. 
Several of these methods can throw an UnsupportedOperationException. As explained, 
this occurs if a collection cannot be modified. 

A ClassCastException is generated when one 
object is incompatible with another, such as when an attempt is made to add an incompatible 
object to a collection. 

A NullPointerException is thrown if an attempt is made to store a 
null object and null elements are not allowed in the collection. 

An IllegalArgumentException is thrown if an invalid argument is used. 

An IllegalStateException is thrown if an attempt 
is made to add an element to a fixed-length collection that is full.

## Methods :

Objects are added to a collection by calling add(). Notice that add() takes an argument of type E, 
which means that objects added to a collection must be compatible with the type of data expected 
by the collection. 

You can add the entire contents of one collection to another by calling addAll().

You can remove an object by using remove(). To remove a group of objects,call removeAll(). 

You can remove all elements except those of a specified group by calling retainAll(). 

To remove an element only if it satisfies some condition, you can use removeIf(). 

To empty a collection, call clear().

You can determine whether a collection contains a specific object by calling contains(). 

To determine whether one collection contains all the members of another, call containsAll(). 

You can determine when a collection is empty by calling isEmpty(). 

The number of elements currently held in a collection can be determined by calling size().

The toArray() methods return an array that contains the elements stored in the collection. 
The first returns an array of Object. The second returns an array of elements that have the 
same type as the array specified as a parameter.Normally, the second form is more convenient 
because it returns the desired array type. **Beginning with JDK 11, a third form has been added 
that lets you specify a function that obtains the array.** These methods are more important than
it might at first seem. Often, processing the contents of a collection by using array-like 
syntax is advantageous. By providing a pathway between collections and arrays, 
you can have the best of both worlds.

Two collections can be compared for equality by calling equals(). The precise meaning of 
“equality” may differ from collection to collection. For example, you can implement equals() so that 
it compares the values of elements stored in the collection. Alternatively, equals() can compare 
references to those elements.

Another important method is iterator(), which returns an iterator to a collection. 

The spliterator() method returns a spliterator to the collection.Iterators are 
frequently used when working with collections. 

Finally, the stream() and parallelStream() 
methods return a Stream that uses the collection as a source of elements.

*************************************************

# The List Interface :

The List interface extends Collection and declares the behavior of a collection that stores a sequence 
of elements. Elements can be inserted or accessed by their position in the list, using a zero-based index. 
A list may contain duplicate elements.List is a generic interface that has this declaration:

`interface List<E>`

## Exceptions : 

In addition to the methods defined by Collection, List defines some of its own.
Note again that several of these will throw an UnsupportedOperationException if the list cannot be modified.

ClassCastException is generated when one object is incompatible with another, such as when an 
attempt is made to add an incompatible object to a list. 

Also, several methods will throw an IndexOutOfBoundsException if an invalid index is used. 

A NullPointerException is thrown if an attempt is made to store a null object and null elements 
are not allowed in the list. 

An IllegalArgumentException is thrown if an invalid argument is used.

## methods : 

To the versions of add() and addAll() defined by Collection, List adds the methods add(int, E) 
and addAll(int, Collection). These methods insert elements at the specified index. Also, 
the semantics of add(E) and addAll(Collection) defined by Collection are changed by List so that 
they add elements to the end of the list. 

You can modify each element in the collection by using replaceAll().

To obtain the object stored at a specific location, call get() with the index of the object. 

To assign a value to an element in the list, call set(). specifying the index of the object to be changed. 

To find the index of an object, use indexOf()or lastIndexOf().

You can obtain a sublist of a list by calling subList(), specifying the beginning and ending 
indexes of the sublist. As you can imagine, subList()makes list processing quite convenient. 

One way to sort a list is with the sort()method defined by List.

**Beginning with JDK 9, List includes the of() factory method, which has an umber of overloads. 
Each version returns an unmodifiable, value-based collection that is comprised of the arguments 
that it is passed. The primary purpose of of() is to provide a convenient, 
efficient way to create a small List collection. There are 12 overloads of of(). 
One takes no arguments and creates an empty list.
Ten overloads take from 1 to 10 arguments and create a list that contains the specified elements.
The final of() overload specifies a varargs parameter that takes an arbitrary number 
of elements or an array of elements.For all versions, null elements are not allowed. 
In all cases, the List implementation is unspecified.**

*************************************************

# The Set Interface :

The Set interface defines a set. It extends Collection and specifies the behavior of a collection that 
does not allow duplicate elements. Therefore, the add() method returns false if an attempt is made to 
add duplicate elements to a set.With two exceptions, it does not specify any additional methods of 
its own. Set is a generic interface that has this declaration: 

`interface Set<E>`

**Beginning with JDK 9, Set includes the of() factory method, which has a number of overloads. 
Each version returns an unmodifiable, value-based collection that is comprised of the arguments 
that it is passed. The primary purpose of of() is to provide a convenient, efficient way to create 
a small Set collection. There are 12 overloads of of(). One takes no arguments and creates an empty set.
Ten overloads take from 1 to 10 arguments and create a list that contains the specified elements.
The final of( ) overload specifies a varargs parameter that takes an arbitrary number of 
elements or an array of elements.For all versions, null elements are not allowed. In all cases, 
the Set implementation is unspecified.Beginning with JDK 10, Set includes the static copyOf( ) 
method here :**

`static <E> Set<E> copyOf(Collection <? extends E> from)`

**It returns a set that contains the same elements as from. 
Null values are not allowed. The returned set is unmodifiable.**

*************************************************

# The SortedSet Interface :

The SortedSet interface extends Set and declares the behavior of a set sorted in
ascending order. SortedSet is a generic interface that has this declaration:

`interface SortedSet<E>`

## Exceptions :

throw a NoSuchElementException when no items are contained in the invoking set.
 
A ClassCastException is thrown when an object is incompatible with the elements
in a set. 

A NullPointerException is thrown if an attempt is made to use a null
object and null is not allowed in the set. 

An IllegalArgumentException is thrown if an invalid argument is used.

## methods : 

SortedSet defines several methods that make set processing more convenient.
To obtain the first object in the set, call first(). 

To get the last element, use last(). 

You can obtain a subset of a sorted set by calling subSet(), specifying the first
and last object in the set. 

If you need the subset that starts with the first element in the set, use headSet(). 

If you want the subset that ends the set, use tailSet().

*************************************************

# The NavigableSet Interface : 

The NavigableSet interface extends SortedSet and declares the behavior of a collection that 
supports the retrieval of elements based on the closest match to a given value or values. 
NavigableSet is a generic interface that has this declaration : 

`interface NavigableSet<E>`

## Exception : 

A ClassCastException is thrown when an object is incompatible with the elements in the set. 

A NullPointerException is thrown if an attempt is made to use a null object and null is not allowed in the set. 

An IllegalArgumentException is thrown if an invalid argument is used.

*************************************************

# The Queue Interface : 

The Queue interface extends Collection and declares the behavior of a queue,which is often a first-in, 
first-out list. However, there are types of queues in which the ordering is based upon other criteria. 
Queue is a generic interface that has this declaration:

`interface Queue<E>`

## Exception :

Several methods throw a ClassCastException when an object is incompatible with the elements in the queue. 

A NullPointerException is thrown if an attempt is made to store a null object and null elements 
are not allowed inthe queue. 

An IllegalArgumentException is thrown if an invalid argument is used. 

An IllegalStateException is thrown if an attempt is made to add an element to a fixed-length queue that is full. 

A NoSuchElementException is thrown if an attempt is made to remove an element from an empty queue.

## Methods : 

Despite its simplicity, Queue offers several points of interest. First, element scan only be removed from 
the head of the queue. Second, there are two methods that obtain and remove elements: poll() and remove(). 
The difference between them is that poll() returns null if the queue is empty, but remove() throws an exception. 

Third, there are two methods, element() and peek(), that obtain but don’t remove the element at the head of 
the queue. They differ only in that element() throws an exception if the queue is empty, but peek() 
returns null.

Finally, notice that offer() only attempts to add an element to a queue. 
Because some queues have a fixed length and might be full, offer() can fail.

*************************************************

# The Deque Interface :

The Deque interface extends Queue and declares the behavior of a double-ended queue. 
Double-ended queues can function as standard, first-in, first-out
queues or as last-in, first-out stacks. Deque is a generic interface that has this declaration:

`interface Deque<E>`

## Exception :

Several methods throw a ClassCastException when an object is incompatible with the elements in the deque. 

A NullPointerException is thrown if an attempt is made to store a null object and null elements are not allowed 
in the deque. 

An IllegalArgumentException is thrown if an invalid argument is used. 

An IllegalStateException is thrown if an attempt is made to add an element to a fixed-length deque that is full. 

A NoSuchElementException is thrown if an attempt is made to remove an element from an empty deque. 

## Methods : 

In addition to the methods that it inherits from Queue, Deque adds these methods : 

Notice that Deque includes the methods push() and pop(). These methods enable a Deque to function as a stack. 

Also, notice the descendingIterator( )method. It returns an iterator that returns elements in reverse order. 
In other words, it returns an iterator that moves from the end of the collection to the start.

A Deque implementation can be capacity-restricted, which means that only a limited number of elements 
can be added to the deque. When this is the case, an attempt to add an element to the deque can fail. 
Deque allows you to handle such a failure in two ways. First, methods such as addFirst() and addLast() 
throw an IllegalStateException if a capacity-restricted deque is full. 
Second,methods such as offerFirst() and offerLast() return false if the element cannot be added.







