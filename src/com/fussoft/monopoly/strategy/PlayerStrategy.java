package com.fussoft.monopoly.strategy;

public interface PlayerStrategy {

    int getMinBalanceForPurchasingAProperty();
    int getMinBalanceForAuctionAProperty();
    int getMinBalanceForPurchasingAHouse();
}
