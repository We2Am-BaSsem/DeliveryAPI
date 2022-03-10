package com.DeliveryAPI.DeliveryAPI.Exceptions;

public class DuplicateCityInsertionException extends Exception {
    public DuplicateCityInsertionException(String cityName) {
        super("Duplicate city name: ".concat(cityName));
    }
}