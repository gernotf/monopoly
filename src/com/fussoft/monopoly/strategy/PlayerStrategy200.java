package com.fussoft.monopoly.strategy;

public class PlayerStrategy200 implements PlayerStrategy {

    private static final int MIN_HOUSE_PURCHASE_BALANCE = 200;
    @Override
    public int getMinBalanceForPurchasingAHouse() {
        return MIN_HOUSE_PURCHASE_BALANCE;
    }
}
