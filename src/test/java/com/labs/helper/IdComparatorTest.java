package com.labs.helper;

import com.labs.model.CarNumber;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdComparatorTest {

    @Test
    public void comparingIds() {
        IdComparator comparator = new IdComparator();
        CarNumber carNumber1 = new CarNumber("A111AA12");
        CarNumber carNumber2 = new CarNumber("A222AA12");
        assertEquals(-1, comparator.compare(carNumber1, carNumber2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void comparingIdAndNull() {
        IdComparator comparator = new IdComparator();
        CarNumber carNumber1 = new CarNumber("A111AA12");
        comparator.compare(carNumber1, null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void comparingNullAndNull() {
        IdComparator comparator = new IdComparator();
        comparator.compare(null, null);
    }
}