package com.fussoft.monopoly;

public class Player {
	
	private String name;
	private int balance;
	private int boardPosition;
	
	public Player (String name, int initialBalance) {
		this.name = name;
		this.balance = initialBalance;
		this.boardPosition = 0;
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
			System.out.println("Player '" + this.name + "' crossed the START field.");
			boardPosition -= maxFieldIndex;
			balance += 200;
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
