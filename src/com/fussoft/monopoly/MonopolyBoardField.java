package com.fussoft.monopoly;

public interface MonopolyBoardField {

	String getName();

	FIELD_TYPE getFieldType();
	
	COLOR_CODE getColorCode();

	int getValue();
	
 	int getCurrentRent();
	
	Player getCurrentOwner();

	int getPriceHouseAndHotel();

	boolean isAvailableForPurchase();
	
	void setNewOwner(final Player newOwner, final MonopolyBoardField[] allFields);
	
	boolean canBuyHouse(final AustraliaBoardField[] allFields);
	
	void buyHouseOrHotel(final AustraliaBoardField[] allFields);
	

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
