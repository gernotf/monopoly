package com.fussoft.monopoly;

public class Player {
	
	private String name;
	private int balance;
	
	public Player (String name, int initialBalance) {
		this.name = name;
		this.balance = initialBalance;
	}
	
	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public int getPayedRent(final int amount) {
		balance += amount;
		return balance;
	}

	public int payRent(final int amount) {
		balance -= amount;
		return balance;
	}
	
	public int payHouseOrHotel(final int amount) {
		balance -= amount;
		return balance;
	}
	
}
