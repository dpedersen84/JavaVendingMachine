package com.dp.vendingmachine.service;

import com.dp.vendingmachine.dao.DaoException;
import com.dp.vendingmachine.dao.ItemDao;
import com.dp.vendingmachine.dto.Change;
import com.dp.vendingmachine.dto.Item;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    ItemDao dao;
    BigDecimal totalMoney = new BigDecimal("0");
    
    public VendingMachineServiceLayerImpl(ItemDao dao) {
        this.dao = dao;
    }
    
    @Override
    public List<Item> getAllItems() throws DaoException {
        return dao.getAllItems();
    }
    
    @Override
    public Item getItem(String name) throws DaoException {
        return dao.getItemByName(name);
    }

    @Override
    public BigDecimal addMoney(BigDecimal money) {
        totalMoney = totalMoney.add(money);
        totalMoney.setScale(2);
        return totalMoney;
    }
    
    @Override
    public BigDecimal getMoney() {
        return totalMoney;
    }

    @Override
    public Change dispenseChange(BigDecimal changeToDispense) {
        Change newChange;
        
        if(changeToDispense.equals(ZERO)) {
            newChange = null;
        } else {
            newChange = new Change(changeToDispense);
        }
        totalMoney = totalMoney.subtract(totalMoney);
        return newChange;
    }

    @Override
    public void vendItem(String name) throws 
            DaoException, 
            VendingMachineInsufficientFundsException, 
            VendingMachineOutOfStockException, 
            VendingMachineItemDoesNotExistException {
        
        Item chosenItem = dao.getItemByName(name);
        
        if(chosenItem != null) {
            if(chosenItem.getInventory() > 0) {
                BigDecimal itemCost = chosenItem.getCost();
            
                if(totalMoney.compareTo(itemCost) >= 0) {
                    totalMoney = totalMoney.subtract(itemCost);
                
                    dao.updateItemInventory(chosenItem);
                } else {
                    throw new VendingMachineInsufficientFundsException("Not enough money.");
                }
            } else {
                throw new VendingMachineOutOfStockException("No more of that item.");
            }
        } else {
            throw new VendingMachineItemDoesNotExistException("Item does not exist.");
        }
    }
}
