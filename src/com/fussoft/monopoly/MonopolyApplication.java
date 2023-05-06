package com.fussoft.monopoly;

import com.fussoft.monopoly.strategy.MonopolyGamingStrategy;

public class MonopolyApplication {

	static final MonopolyGamingStrategy gamingStrategy = new MonopolyGamingStrategy();

	public static void main(String[] args) {
		gamingStrategy.playGame(new AustraliaBoard(), 4);
	}

}
