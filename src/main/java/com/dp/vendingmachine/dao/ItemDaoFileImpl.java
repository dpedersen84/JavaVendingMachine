package com.dp.vendingmachine.dao;

import com.dp.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ItemDaoFileImpl implements ItemDao {
    private final List<Item> itemList = new ArrayList<>();
    private final Map<String, Item> itemMap = new HashMap<>();
    
    public static final String DELIMITER = "::";
    
    String path = "items.txt";
    
    public ItemDaoFileImpl(String path) {
        this.path = path;
    }
    
    @Override
    public List<Item> getAllItems() throws DaoException {
        if(itemMap.isEmpty()) {
            loadDatabase();
        }
        return itemMap.values()
                .stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Item getItemByName(String name) throws DaoException{
        if(itemMap.isEmpty()) {
            loadDatabase();
        }
        return itemMap.get(name);
    }
    
    @Override
    public Item updateItemInventory(Item item) throws DaoException {
        if(itemList.isEmpty()) {
            loadDatabase();
        }
        
        for (Item i : itemList) {
            if(i.getName().equals(item.getName())) {
                
                int inv = item.getInventory();
                
                item.setInventory(inv - 1);
            }
        }
        writeDatabase();
        return item;
    }
    
    private Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        Item itemFromFile = new Item();
        itemFromFile.setName(itemTokens[0]);
        itemFromFile.setInventory(Integer.parseInt(itemTokens[1]));
        itemFromFile.setCost(new BigDecimal(itemTokens[2]));

        return itemFromFile;
    }
    
    private void loadDatabase() throws DaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(path)));
        } catch (FileNotFoundException e) {
            throw new DaoException("Could not load items.");
        }

        String currentLine;
        Item currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);

            itemList.add(currentItem);
            itemMap.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }
    
    private String marshallItem(Item item) {
        String itemAsText = item.getName() + DELIMITER;
        itemAsText += item.getInventory() + DELIMITER;
        itemAsText += item.getCost();
        
        return itemAsText;
    }
    
    private void writeDatabase() throws DaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(path));
        } catch (IOException e) {
            throw new DaoException("Could not save item.", e);
        }
        
        String itemAsText;
        
        List<Item> itemList = this.getAllItems();
        
        for(Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
}