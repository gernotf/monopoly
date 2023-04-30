package com.fussoft.monopoly.strategy;

public class PlayerStrategy200 implements PlayerStrategy {

    private static int PURCHASE_MIN_BALANCE = 200;
    @Override
    public int getPurchaseMinBalance() {
        return PURCHASE_MIN_BALANCE;
    }
}
