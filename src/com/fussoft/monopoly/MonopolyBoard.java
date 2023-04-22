package com.fussoft.monopoly;

public interface MonopolyBoard {

	MonopolyBoardField getFieldAtIndex(int fieldIndex);

	MonopolyBoardField[] getAllFields();
	
	int getMaxFieldIndex();
	
	boolean checkForAllPropertiesSold();

	int getGoToJailIndex();

	int getJailIndex();
}
