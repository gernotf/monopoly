package com.fussoft.monopoly.strategy;

import com.fussoft.monopoly.MonopolyBoard;
import com.fussoft.monopoly.MonopolyBoardField;
import com.fussoft.monopoly.Player;

import java.util.Arrays;
import java.util.Random;

public abstract class MonopolyGamingStrategy {

	static final Random random = new Random();
	
	static final int MAX_ROUNDS = 100;
	abstract int getMinBalance();
	
	
	public void playGame(final MonopolyBoard board, final int playerCount) {
		final Player[] players = new Player[playerCount];
		initPlayers(players);		
		int round = 0;
		boolean allPropertiesSold = false;
		while (!allPropertiesSold && (round < MAX_ROUNDS)) {
			System.out.println("\n++++Starting round " + round);
			for (int playerNumber = 0; playerNumber < players.length; playerNumber++) {
				
				int diceValue = roleTheDice();
				if (players[playerNumber].isInJail()) {
					diceValue = threeTimesChanceToRollADouble(diceValue);
					if (diceValue % 2 != 0) {
						// not coming out of jail
						System.out.println("Player '" + players[playerNumber].getName() + "' stays in jail");
						continue;
					} else {
						System.out.println("Player '" + players[playerNumber].getName() + "' comes out of jail");
					}
				}
				
				int newPosition = players[playerNumber].moveSteps(diceValue, board.getMaxFieldIndex(), board.getGoToJailIndex(), board.getJailIndex());
				
				final MonopolyBoardField boardField = board.getFieldAtIndex(newPosition);
				
				if (!boardField.isPurchasable()) {
					// nothing to do on unpurchasable fields (for now)
					continue;
				}
				
				if (boardField.getCurrentOwner() == players[playerNumber]) {
					// player returned to their own property - no action (for now)
					continue;
				}
				if (boardField.isAvailableForPurchase()) {
					if (players[playerNumber].getBalance() - boardField.getValue() >= getMinBalance()) {
						boardField.setNewOwner(players[playerNumber], board.getAllFields());
						System.out.println("Field '" + boardField.getName() + "'(" + boardField.getValue() + ") was purchased by '" + players[playerNumber].getName() + "', remaining balance: " + players[playerNumber].getBalance());
					} else {
						System.out.println("Field '" + boardField.getName() + "'(" + boardField.getValue() + ") was NOT purchased by '" + players[playerNumber].getName() + "' with balance of " + players[playerNumber].getBalance());
					}
				} else {
					players[playerNumber].payRentForProperty(boardField, diceValue, board.getAllFields());
					boardField.getCurrentOwner().getPayedRentForProperty(boardField, diceValue, board.getAllFields());
					System.out.println("Player '" + players[playerNumber].getName() + "'(" + players[playerNumber].getBalance() + ") payed to owner '" + boardField.getCurrentOwner().getName() + "'(" + boardField.getCurrentOwner().getBalance() + ") for property '" + boardField.getName() + "'(" + boardField.getCurrentRent(diceValue, board.getAllFields()) + ")");
				}
				allPropertiesSold = board.checkForAllPropertiesSold();
				if (allPropertiesSold) {
					break;
				}
			}
			
			round++;
		}
		Arrays.asList(board.getAllFields()).stream()
		.filter(field -> field.isPurchasable())
		.forEach(field -> {
			System.out.println("Field: '" + field.getName() + "' is owned by '" + field.getCurrentOwner().getName() + "'.");
		});
	}
	
	private int threeTimesChanceToRollADouble(int diceValue) {
		int newDiceValue = diceValue;
		int tryCount = 1;
		while (newDiceValue % 2 != 0 && tryCount++ < 3) {
			newDiceValue = roleTheDice();
		}
		return newDiceValue;
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
