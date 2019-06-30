package com.dp.vendingmachine.service;

public class VendingMachineItemDoesNotExistException extends Exception {
    public VendingMachineItemDoesNotExistException(String message) {
        super(message);
    }

    public VendingMachineItemDoesNotExistException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
