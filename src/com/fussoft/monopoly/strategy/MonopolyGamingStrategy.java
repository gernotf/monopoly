package com.fussoft.monopoly.strategy;

import com.fussoft.monopoly.MonopolyBoard;
import com.fussoft.monopoly.Player;

import java.util.Random;

public abstract class MonopolyGamingStrategy {

	static final Random random = new Random();
	
	static final Player[] PLAYERS = new Player[4];

	abstract int getMinBalance();
	
	public void playGame(final MonopolyBoard board) {
		
	}
	
	static int roleTheDice() {
		return 2 + random.nextInt(11);
	}
}
