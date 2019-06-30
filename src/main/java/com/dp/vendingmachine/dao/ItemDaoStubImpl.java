package com.dp.vendingmachine.dao;

import com.dp.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoStubImpl implements ItemDao {
    Item onlyItem;
    List<Item> itemList = new ArrayList<>();
    
    public ItemDaoStubImpl() {
        onlyItem = new Item();
        onlyItem.setName("Snickers");
        onlyItem.setInventory(3);
        onlyItem.setCost(new BigDecimal("2.00"));
        
        itemList.add(onlyItem);
    }
    
    @Override
    public List<Item> getAllItems() {
        return itemList;
    }

    @Override
    public Item getItemByName(String name) {
        if(name.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

//    @Override
//    public boolean updateItem(Item item) {
//        for(Item i  : itemList) {
//            if(i.getName().equals(item.getName())) {
//                itemList.set(itemList.indexOf(i), item);
//            }
//        }
//        return true;
//    }
    
    @Override
    public Item updateItemInventory(Item item) throws DaoException {
        for (Item i : itemList) {
            if(i.getName().equals(item.getName())) {
                
                int inv = i.getInventory();
                
                item.setInventory(inv - 1);
            }
        }
        return item;
    }
}
