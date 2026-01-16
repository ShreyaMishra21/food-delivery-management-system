package com.abes.fdms.exception;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        super("The requested item was not found in the menu.");
    }
}
