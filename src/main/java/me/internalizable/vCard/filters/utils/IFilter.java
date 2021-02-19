package me.internalizable.vCard.filters.utils;

import me.internalizable.vCard.utils.vCard;

import java.util.function.Predicate;

public interface IFilter {

    default String getFilterName() {
        return getClass().getName();
    }

    int getFilterID();

    Predicate<vCard> getPredicate(String filterValue);
}
