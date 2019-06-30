package com.dp.vendingmachine.dao;

import com.dp.vendingmachine.dto.Item;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemDaoTest {
    
    private static final String SEED_FILE = "test-seed.txt";
    private static final String DATA_FILE = "test-items.txt";
    
    private ItemDao dao = new ItemDaoFileImpl(DATA_FILE);
    
    @BeforeAll
    public static void init() throws IOException {
        Path source = Paths.get(SEED_FILE);
        Path destination = Paths.get(DATA_FILE);
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }
    
    @Test
    public void testGetAllItems() throws DaoException {
        List<Item> items = dao.getAllItems();
        assertTrue(items != null && items.size() > 0);
    }

    @Test
    public void testFindByName() throws DaoException {
        Item one = dao.getItemByName("Name 1");
        assertTrue(one != null);
    }
    
    @Test
    public void testFindByNameAgain() throws DaoException {
        Item two = dao.getItemByName("Name 2");
        assertTrue(two != null);
    }

//    @Test
//    public void testUpdateItem() throws DaoException {
//        Item invChange = new Item();
//        invChange.setName("Snickers");
//        invChange.setInventory(20);
//        invChange.setCost(new BigDecimal("2.00"));
//        
//        assertTrue(dao.updateItem(invChange));
//        
//        Item updatedItem = dao.getItemByName("Snickers");
//        
//        assertEquals(invChange, updatedItem); 
//    }
    
    @Test
    public void testUpdateItemInventory() throws DaoException {
        Item testItem = dao.getItemByName("Name 1");
        int originalInventory = testItem.getInventory();
 
        dao.updateItemInventory(testItem);
        
        assertEquals(originalInventory - 1, testItem.getInventory());
    }
}
