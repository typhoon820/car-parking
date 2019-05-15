package com.labs.helper;

import com.labs.model.CarNumber;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberComparatorTest {

    @Test
    public void comparingNumbers() {
        NumberComparator comparator = new NumberComparator();
        CarNumber carNumber1 = new CarNumber("A111AA12");
        CarNumber carNumber2 = new CarNumber("A222AA12");
        assertEquals(-1, comparator.compare(carNumber1, carNumber2));
    }
    @Test(expected = IllegalArgumentException.class)
    public void comparingNumberAndNull() {
        NumberComparator comparator = new NumberComparator();
        CarNumber carNumber1 = new CarNumber("A111AA12");
        comparator.compare(carNumber1, null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void comparingNullAndNull() {
        NumberComparator comparator = new NumberComparator();
        comparator.compare(null, null);
    }
}