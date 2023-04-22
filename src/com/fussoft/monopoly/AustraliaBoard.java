package com.fussoft.monopoly;

import com.fussoft.monopoly.MonopolyBoardField.COLOR_CODE;
import com.fussoft.monopoly.MonopolyBoardField.FIELD_TYPE;

public class AustraliaBoard implements MonopolyBoard {
	
	static final AustraliaBoardField[] FIELDS = new AustraliaBoardField[40];
	
	static {
		FIELDS[0] = new AustraliaBoardField(
				"Start",
				FIELD_TYPE.START,
				null,
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

		FIELDS[1] = new AustraliaBoardField(
				"Darwin",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.BROWN,
				60, //value
				2, //sameColorCount
				50, //priceHouseAndHotel
				2, //rentSolo
				4, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[2] = new AustraliaBoardField(
				"Community Chest",
				FIELD_TYPE.COMMUNITY_CHEST,
				null,
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

		FIELDS[3] = new AustraliaBoardField(
				"Alice Springs",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.BROWN,
				60, //value
				2, //sameColorCount
				50, //priceHouseAndHotel
				4, //rentSolo
				8, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[4] = new AustraliaBoardField(
				"Income Tax",
				FIELD_TYPE.PAY_TAX,
				null,
				200, //value
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

		FIELDS[5] = new AustraliaBoardField(
				"Perth Airport",
				FIELD_TYPE.AIRPORT,
				null,
				200, //value
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

		FIELDS[6] = new AustraliaBoardField(
				"Stanley",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.LIGHT_BLUE,
				100, //value
				3, //sameColorCount
				50, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[7] = new AustraliaBoardField(
				"Chance",
				FIELD_TYPE.CHANCE,
				null,
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

		FIELDS[8] = new AustraliaBoardField(
				"Freycinet National Park",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.LIGHT_BLUE,
				100, //value
				3, //sameColorCount
				50, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[9] = new AustraliaBoardField(
				"Hobart",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.LIGHT_BLUE,
				120, //value
				3, //sameColorCount
				50, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[10] = new AustraliaBoardField(
				"Jail Just Visiting",
				FIELD_TYPE.VISITING_JAIL,
				null,
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

		FIELDS[11] = new AustraliaBoardField(
				"Margret River",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.PINK,
				140, //value
				3, //sameColorCount
				100, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[12] = new AustraliaBoardField(
				"Electric Company",
				FIELD_TYPE.WORKS,
				null,
				150, //value
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

		FIELDS[13] = new AustraliaBoardField(
				"Broome",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.PINK,
				140, //value
				3, //sameColorCount
				100, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[14] = new AustraliaBoardField(
				"Esperance",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.PINK,
				160, //value
				3, //sameColorCount
				100, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[15] = new AustraliaBoardField(
				"Brisban Airport",
				FIELD_TYPE.AIRPORT,
				null,
				200, //value
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

		FIELDS[16] = new AustraliaBoardField(
				"Phillip Island",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.ORANGE,
				180, //value
				3, //sameColorCount
				100, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[17] = new AustraliaBoardField(
				"Community Chest",
				FIELD_TYPE.COMMUNITY_CHEST,
				null,
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

		FIELDS[18] = new AustraliaBoardField(
				"Melbourne",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.ORANGE,
				180, //value
				3, //sameColorCount
				100, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[19] = new AustraliaBoardField(
				"Great Ocean Road",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.ORANGE,
				200, //value
				3, //sameColorCount
				100, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[20] = new AustraliaBoardField(
				"Free Parking",
				FIELD_TYPE.FREE_PARKING,
				null,
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

		FIELDS[21] = new AustraliaBoardField(
				"Canberra",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.RED,
				220, //value
				3, //sameColorCount
				150, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[22] = new AustraliaBoardField(
				"Chance",
				FIELD_TYPE.CHANCE,
				null,
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

		FIELDS[23] = new AustraliaBoardField(
				"Australian War Memorial",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.RED,
				220, //value
				3, //sameColorCount
				150, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[24] = new AustraliaBoardField(
				"Questacon",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.RED,
				240, //value
				3, //sameColorCount
				150, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[25] = new AustraliaBoardField(
				"Melbourne Airport",
				FIELD_TYPE.AIRPORT,
				null,
				200, //value
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

		FIELDS[26] = new AustraliaBoardField(
				"Port Lincoln",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.YELLOW,
				260, //value
				3, //sameColorCount
				150, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[27] = new AustraliaBoardField(
				"Kangaroo Island",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.YELLOW,
				260, //value
				3, //sameColorCount
				150, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[28] = new AustraliaBoardField(
				"Water Works",
				FIELD_TYPE.WORKS,
				null,
				150, //value
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

		FIELDS[29] = new AustraliaBoardField(
				"Barossa Valley",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.YELLOW,
				280, //value
				3, //sameColorCount
				150, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[30] = new AustraliaBoardField(
				"Go to Jail",
				FIELD_TYPE.GO_TO_JAIL,
				null,
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

		FIELDS[31] = new AustraliaBoardField(
				"Tropical North QLD",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.GREEN,
				300, //value
				3, //sameColorCount
				200, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[32] = new AustraliaBoardField(
				"Gold Coast",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.GREEN,
				300, //value
				3, //sameColorCount
				200, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[33] = new AustraliaBoardField(
				"Community Chest",
				FIELD_TYPE.COMMUNITY_CHEST,
				null,
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
		
		FIELDS[34] = new AustraliaBoardField(
				"Whitsundays",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.GREEN,
				320, //value
				3, //sameColorCount
				200, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[35] = new AustraliaBoardField(
				"Syndey Airport",
				FIELD_TYPE.AIRPORT,
				null,
				200, //value
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

		FIELDS[36] = new AustraliaBoardField(
				"Chance",
				FIELD_TYPE.CHANCE,
				null,
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
		
		FIELDS[37] = new AustraliaBoardField(
				"Orange",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.DARK_BLUE,
				350, //value
				2, //sameColorCount
				200, //priceHouseAndHotel
				0, //rentSolo
				0, //rentAllColor
				0, //rentHouse1
				0, //rentHouse2
				0, //rentHouse3
				0, //rentHouse4
				0  //rentHotel
			);

		FIELDS[38] = new AustraliaBoardField(
				"Super Tax",
				FIELD_TYPE.PAY_TAX,
				null,
				100, //value
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
		
		FIELDS[39] = new AustraliaBoardField(
				"Sydney Harbor",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.DARK_BLUE,
				400, //value
				2, //sameColorCount
				200, //priceHouseAndHotel
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
