package com.labs.helper;

import com.labs.model.CarNumber;
import org.junit.Test;

import static org.junit.Assert.*;

public class LetterComparatorTest {

    @Test
    public void comparingLetters() {
        LetterComparator comparator = new LetterComparator();
        CarNumber carNumber1 = new CarNumber("B111AB12");
        CarNumber carNumber2 = new CarNumber("B222BB12");
        assertEquals(-1, comparator.compare(carNumber1, carNumber2));
    }
    @Test(expected = IllegalArgumentException.class)
    public void comparingLetterAndNull() {
        LetterComparator comparator = new LetterComparator();
        CarNumber carNumber1 = new CarNumber("A111AA12");
        comparator.compare(carNumber1, null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void comparingNullAndNull() {
        LetterComparator comparator = new LetterComparator();
        comparator.compare(null, null);
    }
}