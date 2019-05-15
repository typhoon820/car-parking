package com.labs.helper;

import com.labs.model.CarNumber;

import java.util.Comparator;

public class NumberComparator implements Comparator<CarNumber> {

    public int compare(CarNumber o1, CarNumber o2) {
        if (o1 == null || o2 == null){
            throw new IllegalArgumentException("One or more arguments of comparator is null");
        }
        return Integer.compare(Integer.parseInt(o1.getNumber()), Integer.parseInt(o2.getNumber()));
    }
}
