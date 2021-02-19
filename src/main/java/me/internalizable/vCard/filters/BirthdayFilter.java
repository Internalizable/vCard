package me.internalizable.vCard.filters;

import me.internalizable.vCard.filters.utils.IFilter;
import me.internalizable.vCard.utils.vCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

public class BirthdayFilter implements IFilter {

    @Override
    public int getFilterID() {
        return 3;
    }

    @Override
    public Predicate<vCard> getPredicate(String filterValue) {
        return vCard -> {
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(filterValue);

                if(date.compareTo(vCard.getBirthday()) == 0)
                    return true;

            } catch (ParseException e) {
                e.printStackTrace();
            }

            return false;
        };
    }
}
