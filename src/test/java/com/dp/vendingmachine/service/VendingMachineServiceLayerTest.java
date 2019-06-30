package com.dp.vendingmachine.service;

import com.dp.vendingmachine.dao.ItemDao;
import com.dp.vendingmachine.dao.ItemDaoStubImpl;
import com.dp.vendingmachine.dto.Change;
import com.dp.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineServiceLayerTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllItems() throws Exception {
        ItemDao dao = new ItemDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao);
        
        assertEquals(1, service.getAllItems().size());
    }

    @Test
    public void testAddMoney() {
        ItemDao dao = new ItemDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao);
        
        BigDecimal testMoney = new BigDecimal("4.45");
        assertEquals(testMoney, service.addMoney(testMoney));
    }

    @Test
    public void testDispenseChange() {
        ItemDao dao = new ItemDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao);
        
        BigDecimal testMoney = new BigDecimal("3.25");
        Change change = service.dispenseChange(testMoney);
        BigDecimal quarters = change.getQuarters();
        assertEquals(new BigDecimal("13"), quarters);
    }

    @Test
    public void testVendItem() throws Exception { 
        ItemDao dao = new ItemDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao); 
        
        BigDecimal testMoney = new BigDecimal("10.00");
        service.addMoney(testMoney);
        Item testItem = service.getItem("Snickers");
        int testItemInventory = testItem.getInventory();
        service.vendItem("Snickers");
        assertEquals(testItemInventory - 1, testItem.getInventory());
    }
}
