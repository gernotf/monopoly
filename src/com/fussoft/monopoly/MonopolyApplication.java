package com.fussoft.monopoly;

import com.fussoft.monopoly.strategy.MonopolyGamePlay;

public class MonopolyApplication {

	static final MonopolyGamePlay gamingStrategy = new MonopolyGamePlay();

	public static void main(String[] args) {
		gamingStrategy.playGame(new AustraliaBoard(), 4);
	}

}
