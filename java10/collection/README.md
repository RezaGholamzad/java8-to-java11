# What is a View Collection?

A view collection does not store elements but instead relies on a backing collection. 
It provides a few specific methods and delegates to the backing collection for other methods. 
There are two groups of View Collections. The first group contains wrapper collections and 
they extend the contract of the collections by making them more specific. For example, 
Collections.checkedCollection creates a typesafe view of the collection. 
The second group provides a different representation of the same elements. For example, 
List.subList provides a view of elements between a startIndex and endIndex. Any changes 
to the backing collection are reflected in the view and any permitted changes through the 
view are carried to the backing collection.

# What is an Unmodifiable Collection?

An Unmodifiable Collection is a collection that throws an UnsupportedOperationException 
when any of the mutator methods are called. A mutator method attempts to change the state of 
the Collection by adding, modifying or deleting entries from it. If the entries of the collection 
are itself mutable then unmodifiable collection cannot be considered completely mutable. 
For example, consider an unmodifiable list of addresses. Since the actual address object 
can be modified outside the collection, the list is considered mutable. Only if all 
the elements of the list are unmutable, the list is considered effectively unmutable. e.g. 
java.util.Collections.UnmodifiableList

# What is an Unmodifiable View Collection?

An unmodifiable View Collection is an unmodifiable collection that is backed by another collection. 
It, therefore, provides a read only view into the backing collection. e.g. 
Collections.unmodifiableCollection. Its mutator methods throw an exception and the accessor 
methods are delegated to the backing collection.