package com.oracle;

import java.util.*;

public class CopyOfMethod {

    public static void main(String[] args) {

    	/*
    		In Java 10, following new static methods to create unmodifiable collections have been added.

			java.util.List:	<E> List<E> copyOf(Collection<? extends E> coll)
			java.util.Set:	<E> Set<E> copyOf(Collection<? extends E> coll)
			java.util.Map:	<K,V> Map<K,V> copyOf(Map<? extends K, ? extends V> coll)

			Since the return collection/map is unmodifiable, attempting to modify it
			(adding/removing new elements) throws UnsupportedOperationException.

			These methods create shallow copy of the provided collection.
			That means the collection elements (or key/values in case of map) are not copied/cloned,
			so if the original elements (if mutable) are changed they will reflect the changes
			in the new collection and vice-versa.

    	 */
	    var list = new ArrayList<Integer>();
	    list.add(1);
	    list.add(2);

	    var copyList = List.copyOf(list);
        System.out.println("copy list : " + copyList);

//        copyList.add(3); // java.lang.UnsupportedOperationException

		// Set :
		var integerSet = Set.copyOf(list);
		System.out.println("copy Set : " + integerSet);

		// Map :
		var map = new HashMap<Integer, String>();
		map.put(3,"three");
		map.put(4,"four");
		var copyMap = Map.copyOf(map);
		System.out.println("copy Map : " + copyMap);

		System.out.println("************************************");
		/*
			List.CopyOf() vs Collections.unmodifiableList() :

			Collections.unmodifiableList() (and other similar methods) returns a unmodifiable view
			of the source collection, so changes made to the source collection reflects in it.
			Whereas, in case of List.copyOf() method, if the source collection is subsequently
			modified, the returned List will not reflect such modifications.
		 */
		List<Integer> list2 = new ArrayList<>();
		list2.add(1);
		list2.add(2);
		List<Number> unmodifiableList = Collections.unmodifiableList(list2);
		List<Integer> copyOfList = List.copyOf(list2);

		//modifying the source list
		list2.add(3);

		System.out.println("unmodifiableList: " + unmodifiableList);
		System.out.println("copyOfList: " + copyOfList);
	}
}
