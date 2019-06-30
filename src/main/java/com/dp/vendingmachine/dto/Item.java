package com.dp.vendingmachine.dto;

import java.math.BigDecimal;

public class Item {
    private String name;
    private int inventory;
    private BigDecimal cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventory() {
        return inventory;
    }
    
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
