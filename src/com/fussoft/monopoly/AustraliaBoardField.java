package com.fussoft.monopoly;

import java.util.Arrays;

public class AustraliaBoardField implements MonopolyBoardField {

	private final String name;

	private final FIELD_TYPE fieldType;
	private final COLOR_CODE colorCode;

	private final int value;

	private final int sameColorCount;

	private final int priceHouseAndHotel;

	private final int rentSolo;
	private final int rentAllColor;

	private final int rentHouse1;
	private final int rentHouse2;
	private final int rentHouse3;
	private final int rentHouse4;
	private final int rentHotel;

	private int currentRent;
	private Player currentOwner;
	private int roundOfPurchase;

	public AustraliaBoardField(String name, FIELD_TYPE fieldType, COLOR_CODE colorCode, int value, int sameColorCount, int priceHouseAndHotel, int rentSolo,
							   int rentAllColor, int rentHouse1, int rentHouse2, int rentHouse3, int rentHouse4, int rentHotel) {
		this.name = name;
		this.fieldType = fieldType;
		this.colorCode = colorCode;
		this.value = value;
		this.sameColorCount = sameColorCount;
		this.priceHouseAndHotel = priceHouseAndHotel;
		this.rentSolo = rentSolo;
		this.rentAllColor = rentAllColor;
		this.rentHouse1 = rentHouse1;
		this.rentHouse2 = rentHouse2;
		this.rentHouse3 = rentHouse3;
		this.rentHouse4 = rentHouse4;
		this.rentHotel = rentHotel;
		currentRent = 0;
		currentOwner = null;
		roundOfPurchase = 0;
	}

	public String getName() {
		return name;
	}

	public FIELD_TYPE getFieldType() {
		return fieldType;
	}

	public int getValue() {
		return value;
	}

	@Override
	public COLOR_CODE getColorCode() {
		return colorCode;
	}

	public int getSameColorCount() {
		return sameColorCount;
	}

	@Override
	public Player getCurrentOwner() {
		return currentOwner;
	}

	public int getRoundOfPurchase() {
		return roundOfPurchase;
	}

	public int getCurrentRent(int diceValue, final MonopolyBoardField[] allFields) {
		if (fieldType == FIELD_TYPE.WORKS) {
			return getWorksFactor(allFields) * diceValue;
		}
		return currentRent;
	}

	public int getPriceHouseAndHotel() {
		return priceHouseAndHotel;
	}


	public boolean isPurchasable() {
		return fieldType == FIELD_TYPE.AIRPORT
				|| fieldType == FIELD_TYPE.LOCATION
				|| fieldType == FIELD_TYPE.WORKS;
	}

	public boolean isAvailableForPurchase() {
		return currentOwner == null;
	}

	public void setNewOwner(final Player newOwner, final int priceToPay, final MonopolyBoardField[] allFields, final int currentRound) {
		newOwner.payForProperty(priceToPay);
		roundOfPurchase = currentRound;
		switchOwner(newOwner, allFields);
	}

	public void switchOwner(final Player newOwner, final MonopolyBoardField[] allFields) {
		currentOwner = newOwner;
		recalculateCurrentRent(allFields);
	}

	public void recalculateCurrentRent(final MonopolyBoardField[] allFields) {
		if (fieldType == FIELD_TYPE.AIRPORT) {
			currentRent = getRentForOwnedAirports(allFields);
		} else if (fieldType == FIELD_TYPE.LOCATION) {
			if (currentRent == 0) {
				currentRent = rentSolo;
			}
			if (allColorsHaveSameOwner(allFields)) {
				setRentToAllColorRentForAllFieldsOfColor(allFields);
				System.out.println("\t>>>Player '" + currentOwner.getName() + "' now owns all '" + colorCode + "' fields (ex. rent=" + currentRent + ").");
			}
		}
	}

	public boolean canBuyHouse() {
//		System.out.println("CurrentRent: " + currentRent + ", rentSolo: " + rentSolo);
		return (currentRent > rentSolo) && (currentRent < rentHotel);
	}

	public void buyHouseOrHotel(final MonopolyBoardField[] allFields) {
		if (currentRent == rentAllColor) {
			currentRent = rentHouse1;
		} else if (currentRent == rentHouse1) {
			currentRent = rentHouse2;
		} else if (currentRent == rentHouse2) {
			currentRent = rentHouse3;
		} else if (currentRent == rentHouse3) {
			currentRent = rentHouse4;
		} else {
			currentRent = rentHotel;
		}
	}

	public void setCurrentRentToAllColor() {
		currentRent = rentAllColor;
	}

	private boolean allColorsHaveSameOwner(MonopolyBoardField[] fields) {
		return Arrays.stream(fields)
				.filter(field ->
						(field.getCurrentOwner() == currentOwner)
								&& field.getColorCode() == colorCode)
				.count() == sameColorCount;
	}

	private int getRentForOwnedAirports(MonopolyBoardField[] fields) {
		final long numberOfAirportsOwnedByThisOwner = Arrays.stream(fields)
				.filter(field ->
						(field.getCurrentOwner() == currentOwner)
								&& field.getFieldType() == FIELD_TYPE.AIRPORT)
				.count();
		final int rent;
		if (numberOfAirportsOwnedByThisOwner == 1) {
			rent = 25;
		} else if (numberOfAirportsOwnedByThisOwner == 2) {
			rent = 50;
		} else if (numberOfAirportsOwnedByThisOwner == 3) {
			rent = 100;
		} else if (numberOfAirportsOwnedByThisOwner == 4) {
			rent = 200;
		} else {
			rent = 0;
		}
		return rent;
	}

	private int getWorksFactor(MonopolyBoardField[] fields) {
		final long numberOfWorksOwnedByThisOwner = Arrays.stream(fields)
				.filter(field ->
						(field.getCurrentOwner() == currentOwner)
								&& field.getFieldType() == FIELD_TYPE.WORKS)
				.count();

		final int factor;
		if (numberOfWorksOwnedByThisOwner == 1) {
			factor = 4;
		} else if (numberOfWorksOwnedByThisOwner == 2) {
			factor = 10;
		} else {
			factor = 0;
		}
		return factor;
	}

	private void setRentToAllColorRentForAllFieldsOfColor(MonopolyBoardField[] allFields) {
		Arrays.stream(allFields)
				.filter(field -> field.getColorCode() == colorCode)
				.forEach(MonopolyBoardField::setCurrentRentToAllColor);
	}

}
