package com.dp.vendingmachine.dao;

import com.dp.vendingmachine.dto.Item;
import java.util.List;

public interface ItemDao {
    /**
     * Gets all items
     * @return String array with all items
     * @throws com.dp.vendingmachine.dao.DaoException
     */
    List<Item> getAllItems() throws DaoException;
    
    /**
     * Gets a single item
     * @param name
     * @return Returns a single item
     * @throws com.dp.vendingmachine.dao.DaoException
     */
    Item getItemByName(String name) throws DaoException;
    
    /**
     * Updates an item
     * @param item
     * @throws com.dp.vendingmachine.dao.DaoException
     */
//    boolean updateItem(Item item) throws DaoException;
    
    /**
     * Takes the item, updates inventory
     * @param item
     * @return updated Item
     * @throws DaoException 
     */
    Item updateItemInventory(Item item) throws DaoException;
}