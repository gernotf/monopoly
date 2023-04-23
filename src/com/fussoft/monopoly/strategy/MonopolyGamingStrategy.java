package com.fussoft.monopoly.strategy;

import com.fussoft.monopoly.MonopolyBoard;
import com.fussoft.monopoly.MonopolyBoardField;
import com.fussoft.monopoly.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

				int diceValue1 = roleTheDice();
				int diceValue2 = roleTheDice();

				if (players[playerNumber].isInJail()) {
					int[] doubleOrNot = threeTimesChanceToRollADouble(diceValue1, diceValue2);
					if (doubleOrNot[0] == doubleOrNot[1]) {
						System.out.println("Player '" + players[playerNumber].getName() + "' comes out of jail.");
						diceValue1 = doubleOrNot[0];
						diceValue2 = doubleOrNot[1];
					} else {
						// not coming out of jail
						players[playerNumber].addRoundInJail();
						System.out.println("Player '" + players[playerNumber].getName() + "' stays in jail.");
						continue;
					}
				}

				int diceValue = diceValue1 + diceValue2;

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
		printGameResults(board, players);
	}

	protected void initPlayers(Player[] players) {
		for (int playerNumber = 0; playerNumber < players.length; playerNumber++) {
			players[playerNumber] = new Player("Player-" + playerNumber, 1500);
		}
	}

	static int roleTheDice() {
		return 1 + random.nextInt(6);
	}

	private int[] threeTimesChanceToRollADouble(int diceValue1, int diceValue2) {
		int newDiceValue1 = diceValue1;
		int newDiceValue2 = diceValue2;
		int tryCount = 1;
		while (newDiceValue1 != newDiceValue2 && tryCount++ < 3) {
			newDiceValue1 = roleTheDice();
			newDiceValue2 = roleTheDice();
		}
		return new int[] {newDiceValue1, newDiceValue2};

	}

	private void printGameResults(final MonopolyBoard board, final Player[] players) {
		Arrays.stream(board.getAllFields())
		.filter(MonopolyBoardField::isPurchasable)
		.forEach(field -> {
			System.out.println("Field: '" + field.getName() + "' is owned by '" + field.getCurrentOwner().getName() + "'.");
		});
		Arrays.stream(players)
		.forEach(player -> {
			System.out.println("Player: '" + player.getName() + "' owns " + getFieldsOwnedByPlayer(player, board).size() + " fields, has " + player.getBalance() + " money left and spent " + player.getRoundsInJail() + " rounds in jail.");
		});

	}


	private List<MonopolyBoardField> getFieldsOwnedByPlayer(Player player, MonopolyBoard board) {
		return Arrays.stream(board.getAllFields())
				.filter(field -> field.getCurrentOwner() == player)
				.collect(Collectors.toList());
	}

}
