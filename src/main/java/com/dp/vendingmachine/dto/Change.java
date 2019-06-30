package com.dp.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Change {
    private BigDecimal quarters;
    private BigDecimal dimes;
    private BigDecimal nickels;
    private BigDecimal pennies;

    private BigDecimal quarterAmount = new BigDecimal(".25");
    private BigDecimal dimeAmount = new BigDecimal(".10");
    private BigDecimal nickelAmount = new BigDecimal(".05");
    private BigDecimal pennieAmount = new BigDecimal(".01");
        
    public Change(BigDecimal change) {
        quarters = change.divide(quarterAmount, 0, RoundingMode.FLOOR);
        
        BigDecimal tmp = change.subtract(quarterAmount.multiply(quarters));
        
        dimes = tmp.divide(dimeAmount, 0, RoundingMode.FLOOR);
        
        BigDecimal tmp2 = tmp.subtract(dimeAmount.multiply(dimes));
        
        nickels = tmp2.divide(nickelAmount, 0, RoundingMode.FLOOR);
        
        BigDecimal tmp3 = tmp2.subtract(nickelAmount.multiply(nickels));
        
        pennies = tmp3.divide(pennieAmount, 0, RoundingMode.FLOOR);
        
        BigDecimal tmp4 = tmp3.subtract(pennieAmount.multiply(pennies));
    }
    
    public BigDecimal getQuarters() {
        return quarters;
    }

    public BigDecimal getDimes() {
        return dimes;
    }


    public BigDecimal getNickels() {
        return nickels;
    }

    public BigDecimal getPennies() {
        return pennies;
    }
}
