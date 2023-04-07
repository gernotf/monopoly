package com.fussoft.monopoly;

public class AustraliaBoard {
	
	static final AustraliaBoardField[] FIELDS = new AustraliaBoardField[40];
	
	static {
		FIELDS[0] = new AustraliaBoardField(
				"Darwin",
				0, //value
				0, //sameColorCount
				0, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);
	}

}
