package com.fussoft.monopoly;

import java.util.Arrays;

import com.fussoft.monopoly.MonopolyBoardField.COLOR_CODE;
import com.fussoft.monopoly.MonopolyBoardField.FIELD_TYPE;

public class AustraliaBoard implements MonopolyBoard {

	static final AustraliaBoardField[] FIELDS = new AustraliaBoardField[40];

	private final int purchasableFields;

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
				10, //rentHouse1
				30, //rentHouse2
				90, //rentHouse3
				160, //rentHouse4
				250  //rentHotel
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
				20, //rentHouse1
				60, //rentHouse2
				180, //rentHouse3
				320, //rentHouse4
				450  //rentHotel
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
				6, //rentSolo
				12, //rentAllColor
				30, //rentHouse1
				90, //rentHouse2
				270, //rentHouse3
				400, //rentHouse4
				550  //rentHotel
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
				6, //rentSolo
				12, //rentAllColor
				30, //rentHouse1
				90, //rentHouse2
				270, //rentHouse3
				400, //rentHouse4
				550  //rentHotel
			);

		FIELDS[9] = new AustraliaBoardField(
				"Hobart",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.LIGHT_BLUE,
				120, //value
				3, //sameColorCount
				50, //priceHouseAndHotel
				8, //rentSolo
				16, //rentAllColor
				40, //rentHouse1
				100, //rentHouse2
				300, //rentHouse3
				450, //rentHouse4
				600  //rentHotel
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
				"Margaret River",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.PINK,
				140, //value
				3, //sameColorCount
				100, //priceHouseAndHotel
				10, //rentSolo
				20, //rentAllColor
				50, //rentHouse1
				150, //rentHouse2
				450, //rentHouse3
				625, //rentHouse4
				750  //rentHotel
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
				10, //rentSolo
				20, //rentAllColor
				50, //rentHouse1
				150, //rentHouse2
				450, //rentHouse3
				625, //rentHouse4
				750  //rentHotel
			);

		FIELDS[14] = new AustraliaBoardField(
				"Esperance",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.PINK,
				160, //value
				3, //sameColorCount
				100, //priceHouseAndHotel
				12, //rentSolo
				24, //rentAllColor
				60, //rentHouse1
				180, //rentHouse2
				500, //rentHouse3
				700, //rentHouse4
				900  //rentHotel
			);

		FIELDS[15] = new AustraliaBoardField(
				"Brisbane Airport",
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
				14, //rentSolo
				28, //rentAllColor
				70, //rentHouse1
				200, //rentHouse2
				550, //rentHouse3
				750, //rentHouse4
				950  //rentHotel
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
				14, //rentSolo
				28, //rentAllColor
				70, //rentHouse1
				200, //rentHouse2
				550, //rentHouse3
				750, //rentHouse4
				950  //rentHotel
			);

		FIELDS[19] = new AustraliaBoardField(
				"Great Ocean Road",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.ORANGE,
				200, //value
				3, //sameColorCount
				100, //priceHouseAndHotel
				16, //rentSolo
				32, //rentAllColor
				80, //rentHouse1
				220, //rentHouse2
				600, //rentHouse3
				800, //rentHouse4
				1000  //rentHotel
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
				18, //rentSolo
				36, //rentAllColor
				90, //rentHouse1
				250, //rentHouse2
				700, //rentHouse3
				875, //rentHouse4
				1050  //rentHotel
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
				18, //rentSolo
				36, //rentAllColor
				90, //rentHouse1
				250, //rentHouse2
				700, //rentHouse3
				875, //rentHouse4
				1050  //rentHotel
			);

		FIELDS[24] = new AustraliaBoardField(
				"Questacon",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.RED,
				240, //value
				3, //sameColorCount
				150, //priceHouseAndHotel
				20, //rentSolo
				40, //rentAllColor
				100, //rentHouse1
				300, //rentHouse2
				750, //rentHouse3
				925, //rentHouse4
				1100  //rentHotel
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
				22, //rentSolo
				44, //rentAllColor
				110, //rentHouse1
				330, //rentHouse2
				800, //rentHouse3
				975, //rentHouse4
				1150  //rentHotel
			);

		FIELDS[27] = new AustraliaBoardField(
				"Kangaroo Island",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.YELLOW,
				260, //value
				3, //sameColorCount
				150, //priceHouseAndHotel
				22, //rentSolo
				44, //rentAllColor
				110, //rentHouse1
				330, //rentHouse2
				800, //rentHouse3
				975, //rentHouse4
				1150  //rentHotel
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
				24, //rentSolo
				48, //rentAllColor
				120, //rentHouse1
				360, //rentHouse2
				850, //rentHouse3
				1025, //rentHouse4
				1200  //rentHotel
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
				26, //rentSolo
				52, //rentAllColor
				130, //rentHouse1
				390, //rentHouse2
				900, //rentHouse3
				1100, //rentHouse4
				1275  //rentHotel
			);

		FIELDS[32] = new AustraliaBoardField(
				"Gold Coast",
				FIELD_TYPE.LOCATION,
				COLOR_CODE.GREEN,
				300, //value
				3, //sameColorCount
				200, //priceHouseAndHotel
				26, //rentSolo
				52, //rentAllColor
				130, //rentHouse1
				390, //rentHouse2
				900, //rentHouse3
				1100, //rentHouse4
				1275  //rentHotel
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
				28, //rentSolo
				56, //rentAllColor
				150, //rentHouse1
				450, //rentHouse2
				1000, //rentHouse3
				1200, //rentHouse4
				1400  //rentHotel
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
				35, //rentSolo
				70, //rentAllColor
				175, //rentHouse1
				500, //rentHouse2
				1100, //rentHouse3
				1300, //rentHouse4
				1500  //rentHotel
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
				50, //rentSolo
				100, //rentAllColor
				200, //rentHouse1
				600, //rentHouse2
				1400, //rentHouse3
				1700, //rentHouse4
				2000  //rentHotel
			);

	}

	public AustraliaBoard() {
		purchasableFields = (int) Arrays.stream(FIELDS)
				.filter(field -> (field.getFieldType() == FIELD_TYPE.AIRPORT || field.getFieldType() == FIELD_TYPE.LOCATION || field.getFieldType() == FIELD_TYPE.WORKS))
				.count();

		System.out.println("Purchasable fields for this board: " + purchasableFields);
	}

	@Override
	public MonopolyBoardField[] getAllFields() {
		return FIELDS;
	}

	public MonopolyBoardField getFieldAtIndex(int fieldIndex) {
		return FIELDS[fieldIndex];
	}

	public int getMaxFieldIndex() {
		return FIELDS.length - 1;
	}

	@Override
	public boolean checkForAllPropertiesSold() {
		int soldFields = (int) Arrays.stream(FIELDS)
		.filter((field -> field.getCurrentOwner() != null && (field.getFieldType() == FIELD_TYPE.AIRPORT || field.getFieldType() == FIELD_TYPE.LOCATION || field.getFieldType() == FIELD_TYPE.WORKS)))
		.count();
		System.out.println("Currently, " + soldFields + " of " + purchasableFields + " are sold");
		return soldFields == purchasableFields;
	}

	@Override
	public int getGoToJailIndex() {
		return 30;
	}

	@Override
	public int getJailIndex() {
		return 10;
	}

}
