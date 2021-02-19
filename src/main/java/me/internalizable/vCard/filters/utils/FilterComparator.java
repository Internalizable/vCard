package me.internalizable.vCard.filters.utils;

import java.util.Comparator;

public class FilterComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        IFilter firstFilter = (IFilter) o1;
        IFilter secondFilter = (IFilter) o2;

        if(firstFilter.getFilterID() > secondFilter.getFilterID())
            return 1;

        if(firstFilter.getFilterID() == secondFilter.getFilterID())
            return 0;

        return -1;
    }

}
