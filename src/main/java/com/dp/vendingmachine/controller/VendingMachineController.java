package com.dp.vendingmachine.controller;

import com.dp.vendingmachine.dao.DaoException;
import com.dp.vendingmachine.dto.Change;
import com.dp.vendingmachine.dto.Item;
import com.dp.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.dp.vendingmachine.service.VendingMachineItemDoesNotExistException;
import com.dp.vendingmachine.service.VendingMachineOutOfStockException;
import com.dp.vendingmachine.service.VendingMachineServiceLayer;
import com.dp.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {
    VendingMachineView view;
    VendingMachineServiceLayer service;
    
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() {
        boolean cont = true;
        int menuSelection = 0;
        
            try {
                while (cont) {
                    menuSelection = getMenuSelection();

                    switch (menuSelection) {
                        case 1:
                            insertMoney();
                            break;
                        case 2: 
                            viewItems();
                            break;
                        case 3:
                            chooseItem();
                            break;
                        case 4: 
                            returnMoney();
                            break;
                        case 5: 
                            cont = false;
                            break;
                        default:
                            unknownCommand();
                    }
                }
                exitMessage();
            } catch (DaoException e) {
                view.displayErrorMessage(e.getMessage());
            }
    }
    
    private int getMenuSelection() {
        return view.displayMainMenu();
    }

    private void insertMoney() throws DaoException {
        BigDecimal insertedMoney = view.getMoneyAmount();
        service.addMoney(insertedMoney);
        view.displayMoneyInsertedSuccess();
    }

    private void viewItems() throws DaoException {
        List<Item> itemList = service.getAllItems();
        view.displayItemMenu(itemList);
    }
    
    private void chooseItem() throws DaoException {
        boolean hasErrors = false;
        do {
            String item = view.getItemChoice();
            try {
                service.vendItem(item);
                hasErrors = false;
                view.displayItemVendedSuccess();
            } catch (VendingMachineInsufficientFundsException | VendingMachineOutOfStockException | VendingMachineItemDoesNotExistException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
                break;
            }
        } while (hasErrors);  
    }

    private void returnMoney() throws DaoException {
        BigDecimal money = service.getMoney();
        Change changeToReturn = service.dispenseChange(money);
        view.displayChange(changeToReturn);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
