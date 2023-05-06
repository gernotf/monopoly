package com.fussoft.monopoly.strategy;

public class PlayerStrategy200 implements PlayerStrategy {

    private static final int MIN_PURCHASE_BALANCE_FOR_PROPERTY = 200;

    private static final int MIN_PURCHASE_BALANCE_FOR_AUCTION = 200;

    private static final int MIN_PURCHASE_BALANCE_FOR_HOUSE = 200;

    @Override
    public int getMinBalanceForPurchasingAProperty() {
        return MIN_PURCHASE_BALANCE_FOR_PROPERTY;
    }

    public int getMinBalanceForAuctionAProperty() {
        return MIN_PURCHASE_BALANCE_FOR_AUCTION;
    }

    public int getMinBalanceForPurchasingAHouse() {
        return MIN_PURCHASE_BALANCE_FOR_HOUSE;
    }
}
