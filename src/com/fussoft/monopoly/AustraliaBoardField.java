package com.fussoft.monopoly;

import java.util.Arrays;
import java.util.List;

public class AustraliaBoardField implements MonopolyBoardField {

	private static final int AIRPORT_BASE_RENT = 25;

	private String name;

	private FIELD_TYPE fieldType;
	private COLOR_CODE colorCode;
	
	private int value;
	
	private int sameColorCount;

	private int priceHouseAndHotel;
	
	private int rentSolo;
	private int rentAllColor;
	
	private int rentHouse1;
	private int rentHouse2;
	private int rentHouse3;
	private int rentHouse4;
	private int rentHotel;
	
	private int currentRent;
	private Player currentOwner;
	
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
		currentRent= rentSolo;
		currentOwner = null;
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

	@Override
	public Player getCurrentOwner() {
		return currentOwner;
	}
	
	public int getCurrentRent(int diceValue, final MonopolyBoardField[] allFields) {
		if (fieldType == FIELD_TYPE.WORKS) {
			return getWorksFactor(Arrays.asList(allFields)) * diceValue;
		}
		return currentRent;
	}

	public int getPriceHouseAndHotel() {
		return priceHouseAndHotel;
	}

	
	public boolean isPurchasable() {
		return 	   fieldType == FIELD_TYPE.AIRPORT
				|| fieldType == FIELD_TYPE.LOCATION
				|| fieldType == FIELD_TYPE.WORKS;
	}
	
	public boolean isAvailableForPurchase() {
		return currentOwner == null;
	}
	
	public void setNewOwner(final Player newOwner, final MonopolyBoardField[] allFields) {
		currentOwner = newOwner;
		currentOwner.payForProperty(this);
		if (fieldType == FIELD_TYPE.AIRPORT) {
			currentRent = getRentForOwnedAirports(Arrays.asList(allFields));
		} else if (fieldType == FIELD_TYPE.LOCATION) {
			if (currentRent == 0) {
				currentRent = rentSolo;
			} else if (allColorsHaveSameOwner(Arrays.asList(allFields))) {
				System.out.println("\t>>>Player '" + newOwner.getName() + "' now owns all '" + colorCode + "' fields.");
				currentRent = rentAllColor;
			}
		}
	}
	
	public boolean canBuyHouse(final MonopolyBoardField[] allFields) {
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
	
	private boolean allColorsHaveSameOwner(List<MonopolyBoardField> list) {
		return list.stream()
			.filter(field -> 
				(   field.getCurrentOwner() == currentOwner)
				 && field.getColorCode() == colorCode)
			.count() == sameColorCount;
	}

	private int getRentForOwnedAirports(List<MonopolyBoardField> list) {
		final long numberOfAirportsOwnedByThisOwner = list.stream()
			.filter(field -> 
				(   field.getCurrentOwner() == currentOwner)
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
	
	private int getWorksFactor(List<MonopolyBoardField> list) {
		final long numberOfWorksOwnedByThisOwner = list.stream()
				.filter(field -> 
					(   field.getCurrentOwner() == currentOwner)
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

	@Override
	public boolean canBuyHouse(AustraliaBoardField[] allFields) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void buyHouseOrHotel(AustraliaBoardField[] allFields) {
		// TODO Auto-generated method stub
		
	}
}
