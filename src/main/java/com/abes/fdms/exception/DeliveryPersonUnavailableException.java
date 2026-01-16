package com.abes.fdms.exception;

public class DeliveryPersonUnavailableException extends Exception {
    public DeliveryPersonUnavailableException() {
        super("No delivery person is currently available. Please try again later.");
    }
}
