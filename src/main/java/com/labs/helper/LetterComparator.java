package com.labs.helper;

import com.labs.model.CarNumber;

import java.util.Comparator;

public class LetterComparator implements Comparator<CarNumber> {


    @Override
    public int compare(CarNumber o1, CarNumber o2) {
        if (o1 == null || o2 == null){
            throw new IllegalArgumentException("One or more arguments of comparator is null");
        }
        return o1.getLetters().compareTo(o2.getLetters());
    }
}
