package com.fussoft.monopoly;

public class Player {
	
	private String name;
	private int balance;
	private int boardPosition;
	
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

	public int getBoardPosition() {
		return boardPosition;
	}

	public int moveSteps(final int steps, final int maxFieldIndex) {
		boardPosition+=steps;
		if (boardPosition > maxFieldIndex) {
			boardPosition -= maxFieldIndex;
		}
		return boardPosition;
	}
	
	public int getPayedRentForProperty(final AustraliaBoardField field) {
		balance += field.getCurrentRent();
		return balance;
	}

	public int payRentForProperty(final AustraliaBoardField field) {
		balance -= field.getCurrentRent();
		return balance;
	}
	
	public int payHouseOrHotelForProperty(final AustraliaBoardField field) {
		balance -= field.getPriceHouseAndHotel();
		return balance;
	}
	
}
