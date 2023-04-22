package com.fussoft.monopoly.strategy;

import com.fussoft.monopoly.MonopolyBoard;
import com.fussoft.monopoly.MonopolyBoardField;
import com.fussoft.monopoly.Player;

import java.util.Random;

public abstract class MonopolyGamingStrategy {

	static final Random random = new Random();
	
	abstract int getMinBalance();
	
	public void playGame(final MonopolyBoard board, final int playerCount) {
		final Player[] players = new Player[playerCount];
		initPlayers(players);
		for (int playerNumber = 0; playerNumber < players.length; playerNumber++) {
			int diceValue = roleTheDice();
			
			int newPosition = players[playerNumber].moveSteps(diceValue, board.getMaxFieldIndex());
			
			final MonopolyBoardField boardField = board.getFieldAtIndex(newPosition);
			
			if (boardField.isAvailableForPurchase()) {
				if (players[playerNumber].getBalance() - boardField.getValue() >= getMinBalance()) {
					boardField.setNewOwner(players[playerNumber], board.getAllFields());
					System.out.println("Field " + boardField.getName() + " was purchased by " + players[playerNumber]);
				} else {
					System.out.println("Field " + boardField.getName() + " was NOT purchased by " + players[playerNumber] + " with balance " + players[playerNumber]);
				}
			} else {
				
			}
		}
	}
	
	protected void initPlayers(Player[] players) {
		for (int playerNumber = 0; playerNumber < players.length; playerNumber++) {
			players[playerNumber] = new Player("Player-" + playerNumber, 1500);
		}
	}

	static int roleTheDice() {
		return 2 + random.nextInt(11);
	}
}
