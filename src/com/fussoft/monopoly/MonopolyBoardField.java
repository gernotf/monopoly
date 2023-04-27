package com.fussoft.monopoly;


public interface MonopolyBoardField {

	String getName();

	FIELD_TYPE getFieldType();

	COLOR_CODE getColorCode();

	int getSameColorCount();

	int getValue();

 	int getCurrentRent(int diceValue, final MonopolyBoardField[] allFields);

	Player getCurrentOwner();

	int getPriceHouseAndHotel();

	int getRoundOfPurchase();

	boolean isPurchasable();

	boolean isAvailableForPurchase();

	void setNewOwner(final Player newOwner, final int price, final MonopolyBoardField[] allFields, final int currentRound);

	void switchOwner(final Player newOwner, final MonopolyBoardField[] allFields);

	void recalculateCurrentRent(final MonopolyBoardField[] allFields);

	void setCurrentRentToAllColor();

	boolean canBuyHouse();

	void buyHouseOrHotel();

	void sellHouseOrHotel();

	int getNumberOfHouses();

	enum FIELD_TYPE {
		START,
		LOCATION,
		AIRPORT,
		WORKS,
		PAY_TAX,
		CHANCE,
		COMMUNITY_CHEST,
		FREE_PARKING,
		VISITING_JAIL,
		GO_TO_JAIL
	}

	enum COLOR_CODE {
		BROWN,
		LIGHT_BLUE,
		PINK,
		ORANGE,
		RED,
		YELLOW,
		GREEN,
		DARK_BLUE
	}

}
