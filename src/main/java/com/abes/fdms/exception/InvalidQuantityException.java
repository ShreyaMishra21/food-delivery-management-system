package com.abes.fdms.exception;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException() {
        super("Invalid quantity specified. Quantity must be greater than zero.");
    }
}
