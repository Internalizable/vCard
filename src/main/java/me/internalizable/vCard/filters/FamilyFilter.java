package me.internalizable.vCard.filters;

import me.internalizable.vCard.filters.utils.IFilter;
import me.internalizable.vCard.utils.vCard;

import java.util.function.Predicate;

public class FamilyFilter implements IFilter {

    @Override
    public int getFilterID() {
        return 2;
    }

    @Override
    public Predicate<vCard> getPredicate(String filterValue) {
        return vCard -> vCard.getFamilyName().equalsIgnoreCase(filterValue);
    }
}
