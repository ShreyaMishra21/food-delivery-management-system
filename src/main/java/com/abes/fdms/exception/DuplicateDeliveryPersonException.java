package com.abes.fdms.exception;

public class DuplicateDeliveryPersonException extends Exception {
    public DuplicateDeliveryPersonException(String id) {
        super("Delivery person with ID '" + id + "' already exists.");
    }
}
