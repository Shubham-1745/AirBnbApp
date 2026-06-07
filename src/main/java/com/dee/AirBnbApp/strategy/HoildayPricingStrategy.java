package com.dee.AirBnbApp.strategy;

import com.dee.AirBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class HoildayPricingStrategy implements PricingStrategy{
    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);

        boolean isTodayHoilday = true; // call an API or check with local data

        if(isTodayHoilday){
            return price.multiply(BigDecimal.valueOf(1.25));
        }
        return price;
    }
}
