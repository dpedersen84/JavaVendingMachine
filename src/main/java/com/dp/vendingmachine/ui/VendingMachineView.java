package com.dp.vendingmachine.ui;

import com.dp.vendingmachine.dto.Change;
import com.dp.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {
    private final UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public int displayMainMenu() {
        io.print("1. Insert Money");
        io.print("2. View Items");
        io.print("3. Choose Item");
        io.print("4. Return Money");
        io.print("5. Exit");
        io.print("");
        return io.readInt("What would you like to do? [1-5]", 1, 5);
    }
    
    public void displayItemMenu(List<Item> itemList) {
        if(itemList.size() > 0 || itemList.isEmpty() != true) {
           io.print("==============================");
           io.print("");
            for(Item i : itemList) {
                io.print(i.getName() + " | " + i.getCost() + " | " + i.getInventory() + " available");
                io.print("");
            }
            io.print("==============================");
            io.readString("Press enter to continue.");
        }
    }

    public String getItemChoice() {
        return io.readString("Which item would you like?");
    }
    public BigDecimal getMoneyAmount() {
        return io.readBigDecimal("How much money would you like to add?");
    }
    
    public void displayChange(Change change) {
        if (change != null) {
            io.print("Quarters: " + change.getQuarters());
            io.print("Dimes: " + change.getDimes());
            io.print("Nickels: " + change.getNickels());
            io.print("Pennies: " + change.getPennies());
            io.print("");
        } else {
            io.print("No change.");
        }
        io.readString("Press enter to continue.");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print(errorMsg);
    }
    
    public void displayExitBanner() {
        io.print("Goodbye Friend!");
    }

    public void displayMoneyInsertedSuccess() {
        io.print("Money Added!");
    }
    
    public void displayItemVendedSuccess() {
        io.print("Item Vended!");
    }
    
}
