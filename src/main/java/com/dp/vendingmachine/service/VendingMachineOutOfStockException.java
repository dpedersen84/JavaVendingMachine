package com.dp.vendingmachine.service;

public class VendingMachineOutOfStockException extends Exception {
    public VendingMachineOutOfStockException(String message) {
        super(message);
    }

    public VendingMachineOutOfStockException(String message,
        Throwable cause) {
        super(message, cause);
    }
}
