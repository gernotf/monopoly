package com.fussoft.monopoly;

public class Player {
	
	private String name;
	private int balance;
	private int boardPosition;
	private boolean isInJail;
	
	public Player (String name, int initialBalance) {
		this.name = name;
		this.balance = initialBalance;
		this.boardPosition = 0;
		this.isInJail = false;
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
	
	public boolean isInJail() {
		return isInJail;
	}

	public int moveSteps(final int steps, final int maxFieldIndex, final int goToJailIndex, final int jailIndex) {
		// player is allowed to move, so their couldn't be in jail
		isInJail = false;
		boardPosition+=steps;
		if (boardPosition > maxFieldIndex) {
			System.out.println("Player '" + this.name + "' crossed the START field.");
			boardPosition -= maxFieldIndex;
			balance += 200;
		}
		if (boardPosition == goToJailIndex) {
			boardPosition = jailIndex;
			isInJail = true;
			System.out.println("Player '" + this.name + "' goes to jail.");
		}
		return boardPosition;
	}
	
	public int payForProperty(final MonopolyBoardField field) {
		balance -= field.getValue();
		return balance;
	}
	
	public int getPayedRentForProperty(final MonopolyBoardField field, int diceValue, final MonopolyBoardField[] allFields) {
		balance += field.getCurrentRent(diceValue, allFields);
		return balance;
	}

	public int payRentForProperty(final MonopolyBoardField field, int diceValue, final MonopolyBoardField[] allFields) {
		balance -= field.getCurrentRent(diceValue, allFields);
		return balance;
	}
	
	public int payHouseOrHotelForProperty(final AustraliaBoardField field) {
		balance -= field.getPriceHouseAndHotel();
		return balance;
	}
	
}
