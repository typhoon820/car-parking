package com.labs.service;

import com.labs.model.CarNumber;

import java.util.Collection;
import java.util.stream.Collectors;

public class CarPlateService {

    public Collection<CarNumber> getCoolNumbers(Collection<CarNumber> numbers) {
        return numbers.stream().filter(this::isCool).collect(Collectors.toList());
    }

    private boolean isCool(CarNumber carNumber) {
        return carNumber.getNumber().chars().distinct().toArray().length == 1 ||
                carNumber.getLetters().chars().distinct().toArray().length == 1;
    }
}
