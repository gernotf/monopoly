package com.fussoft.monopoly;

import com.fussoft.monopoly.strategy.MonopolyGamingStrategy;
import com.fussoft.monopoly.strategy.MonopolyGamingStrategy100;

public class MonopolyApplication {
	
	static final MonopolyGamingStrategy gamingStrategy = new MonopolyGamingStrategy100();
	
	public static void main(String[] args) {
		gamingStrategy.playGame(new AustraliaBoard());
	}

}
