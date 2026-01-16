package com.abes.fdms.exception;

public class InvalidOrderException extends Exception {
    public InvalidOrderException() {
        super("The order is invalid. Please check the items and quantities.");
    }
}
