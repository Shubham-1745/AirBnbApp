package com.dee.AirBnbApp.strategy;

import com.dee.AirBnbApp.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}
