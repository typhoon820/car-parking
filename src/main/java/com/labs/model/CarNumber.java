package com.labs.model;

import com.labs.exception.InvalidInputException;
import com.labs.helper.IdSequenceGenerator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarNumber {
    private static final Pattern p = Pattern.compile("^([a-zA-Z])([0-9]{3})([a-zA-Z]{2})([0-9]{2,3})");

    private long id;
    private String number;
    private String letters;
    private String region;

    public CarNumber() {
    }

    public CarNumber(String carNumber) {

        Matcher matcher = p.matcher(carNumber);
        if (matcher.find() && carNumber.length() > 7 && carNumber.length() < 10) {
            this.number = matcher.group(2);
            this.region = matcher.group(4);
            this.letters = matcher.group(1) + matcher.group(3);
            this.id = IdSequenceGenerator.getInstance().getId();
        } else {
            throw new InvalidInputException("Invalid data to create car plate!");
        }
    }

    public long getId() {
        return id;
    }

    public String getCarPlate() {
        return compileNumber();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return compileNumber().toUpperCase();
    }

    private String compileNumber() {
        return letters.charAt(0) + number + letters.substring(1, letters.length()) + region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarNumber carNumber = (CarNumber) o;

        if (!number.equals(carNumber.number)) return false;
        if (!letters.equals(carNumber.letters)) return false;
        return region.equals(carNumber.region);
    }

    @Override
    public int hashCode() {
        int result = number.hashCode();
        result = 31 * result + letters.hashCode();
        result = 31 * result + region.hashCode();
        return result;
    }
}
