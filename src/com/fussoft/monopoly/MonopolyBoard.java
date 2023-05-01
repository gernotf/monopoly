package com.fussoft.monopoly;

public interface MonopolyBoard {

    int MIN_PRICE_FOR_A_HOUSE = 50;

    MonopolyBoardField getFieldAtIndex(int fieldIndex);

	MonopolyBoardField[] getAllFields();

	int getMaxFieldIndex();

	boolean checkForAllPropertiesSold();

	int getGoToJailIndex();

	int getJailIndex();
}
