package com.abes.fdms.exception;

public class DuplicateItemException extends Exception {
    public DuplicateItemException(String name) {
        super("Item '" + name + "' already exists in the menu.");
    }
}
