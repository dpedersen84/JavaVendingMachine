package com.dp.vendingmachine.service;

import com.dp.vendingmachine.dao.DaoException;
import com.dp.vendingmachine.dto.Change;
import com.dp.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {
    List<Item> getAllItems() throws DaoException;
    Item getItem(String name) throws DaoException;
    BigDecimal addMoney(BigDecimal money);
    BigDecimal getMoney();
    Change dispenseChange(BigDecimal money);
    void vendItem(String name) throws 
            DaoException, 
            VendingMachineInsufficientFundsException, 
            VendingMachineOutOfStockException, 
            VendingMachineItemDoesNotExistException;
}