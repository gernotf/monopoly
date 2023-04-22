package com.fussoft.monopoly.strategy;

public class MonopolyGamingStrategy100 extends MonopolyGamingStrategy {

	private static final int MIN_BALANCE = 100;
	
	public MonopolyGamingStrategy100() {

	}

	@Override
	public int getMinBalance() {
		return MIN_BALANCE;
	}

}
