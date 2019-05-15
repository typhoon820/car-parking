package com.labs.helper;

import com.labs.model.CarNumber;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegionComparatorTest {
    @Test
    public void comparingRegions() {
        RegionComparator comparator = new RegionComparator();
        CarNumber carNumber1 = new CarNumber("A111AA12");
        CarNumber carNumber2 = new CarNumber("A222AA20");
        assertEquals(-1, comparator.compare(carNumber1, carNumber2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void comparingRegionAndNull() {
        RegionComparator comparator = new RegionComparator();
        CarNumber carNumber1 = new CarNumber("A111AA12");
        comparator.compare(carNumber1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void comparingNullAndNull() {
        RegionComparator comparator = new RegionComparator();
        comparator.compare(null, null);
    }

}