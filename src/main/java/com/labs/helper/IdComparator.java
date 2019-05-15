package com.labs.helper;

import com.labs.model.CarNumber;

import java.util.Comparator;

public class IdComparator implements Comparator<CarNumber> {

    @Override
    public int compare(CarNumber o1, CarNumber o2) {
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("One or more arguments of comparator is null");
        }
        return Long.compare(o1.getId(), o2.getId());
    }
}
