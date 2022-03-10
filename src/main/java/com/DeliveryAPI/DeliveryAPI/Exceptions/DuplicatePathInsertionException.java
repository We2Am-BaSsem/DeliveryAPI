package com.DeliveryAPI.DeliveryAPI.Exceptions;

public class DuplicatePathInsertionException extends Exception {
    public DuplicatePathInsertionException(String path) {
        super("Duplicate path: ".concat(path));
    }
}
