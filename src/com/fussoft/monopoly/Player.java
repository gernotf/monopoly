package com.fussoft.monopoly;


import java.util.ArrayList;

public class Player {

	private final String name;
	private int balance;
	private int boardPosition;
	private boolean isInJail;

	private int roundsInJail;

	private int passedStartField;

	public Player (String name, int initialBalance) {
		this.name = name;
		this.balance = initialBalance;
		this.boardPosition = 0;
		this.isInJail = false;
		this.roundsInJail = 0;
		this.passedStartField = 0;
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

	public int getRoundsInJail() {
		return roundsInJail;
	}

	public int getPassedStartField() {
		return passedStartField;
	}

	public void addRoundInJail() {
		this.roundsInJail++;
	}

	public int moveSteps(final int steps, final int maxFieldIndex, final int goToJailIndex, final int jailIndex) {
		// player is allowed to move, so their couldn't be in jail
		isInJail = false;
		boardPosition+=steps;
		if (boardPosition > maxFieldIndex) {
			System.out.println("Player '" + this.name + "' crossed the START field.");
			boardPosition -= maxFieldIndex;
			balance += 200;
			passedStartField++;
		}
		if (boardPosition == goToJailIndex) {
			boardPosition = jailIndex;
			isInJail = true;
			roundsInJail++;
			System.out.println("Player '" + this.name + "' goes to jail.");
		}
		return boardPosition;
	}

	public void payForProperty(final int priceToPay) {
		balance -= priceToPay;
	}

	public void getPayedRentForProperty(final int paidRent) {
		balance += paidRent;
	}

	public void payRentForProperty(final int rentToPay) {
		balance -= rentToPay;
	}

	public int payHouseOrHotelForProperty(final AustraliaBoardField field) {
		balance -= field.getPriceHouseAndHotel();
		return balance;
	}

	public int sellHouses(int remainingPayment, MonopolyBoardField[] allFields) {


		return 0;
	}

	public PropertiesRecord provideProperties(int remainingPaymentHouses, MonopolyBoardField[] allFields) {
		PropertiesRecord propertiesRecord = new PropertiesRecord(new ArrayList<>(), 0);


		return propertiesRecord;
	}
}
