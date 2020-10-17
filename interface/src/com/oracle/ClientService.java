package com.oracle;

import java.util.List;
import java.util.function.Predicate;

public interface ClientService {

    private long getNumberOfAvailableItems(List<Integer> list, Predicate<Integer> filtering){
        return list.stream().filter(filtering).count();
    }

    default long getNumberOfEvenItems(List<Integer> list){
        return getNumberOfAvailableItems(list, item -> item % 2 == 0);
    }

    default long getNumberOfOddItems(List<Integer> list){
        return getNumberOfAvailableItems(list, item -> item % 2 == 1);
    }
}
