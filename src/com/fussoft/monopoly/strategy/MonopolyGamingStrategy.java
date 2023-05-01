package com.fussoft.monopoly.strategy;

import com.fussoft.monopoly.MonopolyBoard;
import com.fussoft.monopoly.MonopolyBoardField;
import com.fussoft.monopoly.Player;
import com.fussoft.monopoly.PropertiesRecord;

import java.util.*;
import java.util.stream.Collectors;

public abstract class MonopolyGamingStrategy {

	static final Random random = new Random();

	static final int MAX_ROUNDS = 100;

	abstract int getMinBalance();


	public void playGame(final MonopolyBoard board, final int playerCount) {
		final Player[] players = new Player[playerCount];
		initPlayers(players);
		int round = 1;
		boolean allPropertiesSold = false;

		while (!allPropertiesSold && (round < MAX_ROUNDS)) {

			System.out.println("\n++++Starting round " + round);

			for (int playerNumber = 0; playerNumber < players.length; playerNumber++) {

				final Player player = players[playerNumber];

				int diceValue1 = roleTheDice();
				int diceValue2 = roleTheDice();

				if (player.isBankrupt()) {
					continue;
				}

				if (player.isInJail()) {
					int[] doubleOrNot = threeTimesChanceToRollADouble(diceValue1, diceValue2);
					if (doubleOrNot[0] == doubleOrNot[1]) {
						System.out.println("Player '" + player.getName() + "' comes out of jail.");
						diceValue1 = doubleOrNot[0];
						diceValue2 = doubleOrNot[1];
					} else {
						// not coming out of jail
						player.addRoundInJail();
						System.out.println("Player '" + player.getName() + "' stays in jail.");
						continue;
					}
				}

				int diceValue = diceValue1 + diceValue2;

				int newPosition = player.moveSteps(diceValue, board.getMaxFieldIndex(), board.getGoToJailIndex(), board.getJailIndex());

				final MonopolyBoardField boardField = board.getFieldAtIndex(newPosition);

				if (!boardField.isPurchasable()) {
					// nothing to do on unpurchasable fields (for now)
					continue;
				}

				if (boardField.getCurrentOwner() == player) {
					// player returned to their own property - no action (for now)
					continue;
				}
				if (boardField.isAvailableForPurchase()) {
					if (player.getBalance() - boardField.getValue() >= getMinBalance()) {
						boardField.setNewOwner(player, boardField.getValue(), board.getAllFields(), round);
						System.out.println("Field '" + boardField.getName() + "'(" + boardField.getValue() + ") was purchased by '" + player.getName() + "', remaining balance: " + player.getBalance());
					} else {
						System.out.println("Field '" + boardField.getName() + "'(" + boardField.getValue() + ") was NOT purchased by '" + player.getName() + "' with balance of " + player.getBalance());
						// auction the field
						final AuctionResult auctionResult = getPlayerWithHighestBidOnProperty(boardField, board.getAllFields(), players);
						if (auctionResult != null) {
							boardField.setNewOwner(auctionResult.chosenPlayer, auctionResult.priceToPay, board.getAllFields(), round);
							System.out.println("\tField '" + boardField.getName() + "'(" + boardField.getValue() + ") was auctioned by '" + auctionResult.chosenPlayer.getName() + "' for " + auctionResult.priceToPay + ", remaining balance: " + auctionResult.chosenPlayer.getBalance());
						}
					}
				} else {
					player.setIsBankrupt(payForProperty(board, player, diceValue, boardField));
				}

				player.checkAndBuyHouses(boardField, board.getAllFields());

				if (diceValue1 == diceValue2) {
					// current player rolled a double and has another turn
					System.out.println("Player '" + player.getName() + "' rolled a double and has another turn.");
					playerNumber--;
				}
			}
			//allPropertiesSold = board.checkForAllPropertiesSold();
			round++;
		}
		printGameResults(board, players);
	}

	private boolean payForProperty(MonopolyBoard board, Player player, int diceValue, MonopolyBoardField boardField) {
		boolean isPayerBankrupt = false;
		final int currentRent = boardField.getCurrentRent(diceValue, board.getAllFields());
		if (currentRent <= player.getBalance() - getMinBalance()) {
			player.payRentForProperty(currentRent);
			boardField.getCurrentOwner().getPayedRentForProperty(currentRent);
			System.out.println("Player '" + player.getName() + "'(" + player.getBalance() + ") payed to owner '" + boardField.getCurrentOwner().getName() + "'(" + boardField.getCurrentOwner().getBalance() + ") for property '" + boardField.getName() + "'(" + currentRent + ")");
		} else {
			final int remainingPaymentHouses = currentRent - (player.getBalance() - getMinBalance());
			final int gainFromSoldHouses = player.sellHouses(remainingPaymentHouses, board.getAllFields());
			if (gainFromSoldHouses >= remainingPaymentHouses) {
				player.payRentForProperty(currentRent);
				boardField.getCurrentOwner().getPayedRentForProperty(currentRent);
				System.out.println("Player '" + player.getName() + "'(" + player.getBalance() + ") sold houses and payed to owner '" + boardField.getCurrentOwner().getName() + "'(" + boardField.getCurrentOwner().getBalance() + ") for property '" + boardField.getName() + "'(" + currentRent + ")");
			} else {
				final int remainingPaymentProperties = remainingPaymentHouses - gainFromSoldHouses;
				final PropertiesRecord propertiesRecord = player.provideProperties(remainingPaymentHouses, board.getAllFields());
				if (propertiesRecord.getSumPrice() >= remainingPaymentProperties) {
					// paying player may pay more with the sum of the value of the provided properties - the payee doesn't pay back the change!
					player.payRentForProperty(currentRent);
					boardField.getCurrentOwner().getPayedRentForProperty(currentRent);
					propertiesRecord.getProperties()
							.forEach(property -> property.switchOwner(boardField.getCurrentOwner(), board.getAllFields()));
					System.out.println("Player '" + player.getName() + "'(" + player.getBalance() + ") sold houses, handed properties and payed to owner '" + boardField.getCurrentOwner().getName() + "'(" + boardField.getCurrentOwner().getBalance() + ") for property '" + boardField.getName() + "'(" + currentRent + ")");
				} else {
					// player has sold all houses and properties and is still unable to pay the rent - the player is bankrupt
					isPayerBankrupt = true;
					System.out.println("Player '" + player.getName() + "'(" + player.getBalance() + ") is bankrupt and unable to pay the current rent of " + currentRent + ". Sold houses for " + gainFromSoldHouses + ", and handed over " + propertiesRecord.getProperties().size() + " properties of value " + propertiesRecord.getSumPrice() + ".");
				}

			}
		}
		return isPayerBankrupt;
	}

	protected void initPlayers(Player[] players) {
		for (int playerNumber = 0; playerNumber < players.length; playerNumber++) {
			players[playerNumber] = new Player("Player-" + playerNumber, 1500);
		}
	}

	static int roleTheDice() {
		return 1 + random.nextInt(6);
	}

	private int[] threeTimesChanceToRollADouble(int firstChanceDiceValue1, int firstChanceDiceValue2) {
		int newDiceValue1 = firstChanceDiceValue1;
		int newDiceValue2 = firstChanceDiceValue2;
		int tryCount = 1;
		while (newDiceValue1 != newDiceValue2 && tryCount++ < 3) {
			newDiceValue1 = roleTheDice();
			newDiceValue2 = roleTheDice();
		}
		return new int[] {newDiceValue1, newDiceValue2};

	}

	private AuctionResult getPlayerWithHighestBidOnProperty(final MonopolyBoardField boardField, MonopolyBoardField[] allFields, final Player[] players) {
		AuctionResult auctionResult = findPlayerWithSimilarColor(boardField, allFields);
		if (auctionResult == null) {
			auctionResult = findPlayerWithLeastProperties(boardField, allFields);
			if (auctionResult == null) {
				auctionResult = findPlayerWithLowestBalance(boardField, players);
			}
		}
		return auctionResult;
	}

	private AuctionResult findPlayerWithSimilarColor(final MonopolyBoardField boardField, MonopolyBoardField[] allFields) {

		final int priceStrategy = boardField.getValue() * 2;

		final List<Player> colorCodePlayers =  Arrays.stream(allFields)
				.filter(field ->
						((boardField.getFieldType() == MonopolyBoardField.FIELD_TYPE.AIRPORT && field.getFieldType() == MonopolyBoardField.FIELD_TYPE.AIRPORT)
								|| (boardField.getFieldType() == MonopolyBoardField.FIELD_TYPE.LOCATION && field.getColorCode() == boardField.getColorCode()))
								&& field.getCurrentOwner() != null)
				.map(MonopolyBoardField::getCurrentOwner)
				.collect(Collectors.toList());

		AuctionResult auctionResult = null;
		if (!colorCodePlayers.isEmpty()) {
			if (boardField.getFieldType() == MonopolyBoardField.FIELD_TYPE.AIRPORT) {
				// just select the first player in line with the most airports
				if (colorCodePlayers.size() == 1
						|| (colorCodePlayers.size() == 2 && (colorCodePlayers.get(0) == colorCodePlayers.get(1)))
						|| (colorCodePlayers.size() == 3 && (((colorCodePlayers.get(1) == colorCodePlayers.get(2)))
						|| ((colorCodePlayers.get(0) == colorCodePlayers.get(1)) && (colorCodePlayers.get(0) == colorCodePlayers.get(2)))))
				) {
					Player chosenPlayer = colorCodePlayers.get(0);
					if (chosenPlayer.getBalance() - priceStrategy >= getMinBalance()) {
						auctionResult = new AuctionResult(chosenPlayer, priceStrategy);
						System.out.println("Chosen player for Airport (4, 1/2/3): '" + chosenPlayer.getName() + "'.");
					}
				} else if (colorCodePlayers.size() == 3
						&& (colorCodePlayers.get(0) == colorCodePlayers.get(2) || colorCodePlayers.get(1) == colorCodePlayers.get(2))
				) {
					Player chosenPlayer = colorCodePlayers.get(2);
					if (chosenPlayer.getBalance() - priceStrategy >= getMinBalance()) {
						auctionResult = new AuctionResult(chosenPlayer, priceStrategy);
						System.out.println("Chosen player for Airport (4, 1/3 or 2/3): '" + chosenPlayer.getName() + "'.");
					}
				}
			}
			if (boardField.getSameColorCount() == 2) {
				// there can only be one player with a property of that color - it's the chosen one
				Player chosenPlayer = colorCodePlayers.get(0);
				if (chosenPlayer.getBalance() - priceStrategy >= getMinBalance()) {
					auctionResult = new AuctionResult(chosenPlayer, priceStrategy);
					System.out.println("Chosen player by similar color (2): '" + chosenPlayer.getName() + "'.");
				}
			} else {
				// in here, there are 3 fields of the same colorCode
				if (colorCodePlayers.size() == 1) {
					// only one player owns a property of that color - it's the chosen one
					Player chosenPlayer = colorCodePlayers.get(0);
					if (chosenPlayer.getBalance() - priceStrategy >= getMinBalance()) {
						auctionResult = new AuctionResult(chosenPlayer, priceStrategy);
						System.out.println("Chosen player by similar color (3, 1): '" + chosenPlayer.getName() + "'.");
					}
				} else {
					if (colorCodePlayers.get(0) == colorCodePlayers.get(1)) {
						// same owner - it's the chosen one
						Player chosenPlayer = colorCodePlayers.get(0);
						if (chosenPlayer.getBalance() - priceStrategy >= getMinBalance()) {
							auctionResult = new AuctionResult(chosenPlayer, priceStrategy);
							System.out.println("Chosen player by similar color (3, 2): '" + chosenPlayer.getName() + "'.");
						}
					}
				}
			}
		}
		return auctionResult;
	}

	private AuctionResult findPlayerWithLeastProperties(MonopolyBoardField boardField, MonopolyBoardField[] allFields) {
		final int priceStrategy = boardField.getValue() / 2;

		final Map<Player, Integer> playerPropertiesCount = new HashMap<>();
		Arrays.stream(allFields)
				.filter(field -> field.getCurrentOwner() != null)
				.peek(field -> {
					if (!playerPropertiesCount.containsKey(field.getCurrentOwner())) {
						playerPropertiesCount.put(field.getCurrentOwner(), 0);
					}
					playerPropertiesCount.put(field.getCurrentOwner(),
							playerPropertiesCount.get(field.getCurrentOwner()) + 1);
				});

		final Player chosenPlayer = playerPropertiesCount.entrySet()
				.stream()
				.filter(entrySet -> entrySet.getKey().getBalance() - priceStrategy >= getMinBalance())
				.min(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.orElse(null);

		AuctionResult auctionResult = null;
		if (chosenPlayer != null) {
			System.out.println("Chosen player by least properties: '" + chosenPlayer.getName() + "', with this amount of properties: " + playerPropertiesCount.get(chosenPlayer) + ".");
			auctionResult = new AuctionResult(chosenPlayer, priceStrategy);
		}
		return auctionResult;
	}

	private AuctionResult findPlayerWithLowestBalance(MonopolyBoardField boardField, Player[] players) {

		final int priceStrategy = boardField.getValue() / 2;

		final Player playerWithLeastBalanceAbovePriceStrategy =  Arrays.stream(players)
				.filter(player -> player.getBalance() - priceStrategy >= getMinBalance())
				.min(Comparator.comparingInt(Player::getBalance))
				.orElse(null);


		AuctionResult auctionResult = null;
		if (playerWithLeastBalanceAbovePriceStrategy != null) {
			System.out.println("Chosen player by lowest balance: '" + playerWithLeastBalanceAbovePriceStrategy.getName() + "', with balance: " + playerWithLeastBalanceAbovePriceStrategy.getBalance() + ".");
			auctionResult = new AuctionResult(playerWithLeastBalanceAbovePriceStrategy, priceStrategy);
		}
		return auctionResult;
	}

	private void printGameResults(final MonopolyBoard board, final Player[] players) {
		System.out.println();
		Arrays.stream(board.getAllFields())
				.filter(MonopolyBoardField::isPurchasable)
				.forEach(field -> System.out.println("Field: '" + field.getName() + "' was acquired by '" + field.getCurrentOwner().getName() + "' in round " + field.getRoundOfPurchase() + "."));

		System.out.println();
		Arrays.stream(players)
				.forEach(player -> System.out.println("Player: '" + player.getName() + "' owns " + getFieldsOwnedByPlayer(player, board).size() + " fields, has " + player.getBalance() + " money left, spent " + player.getRoundsInJail() + " rounds in jail, and crossed " + player.getPassedStartField() + " times the Start field."));

		System.out.println();
		final List<MonopolyBoardField.COLOR_CODE> foundColorCodes = new ArrayList<>();
		Arrays.stream(board.getAllFields())
				.filter(field -> field.canBuyHouse() && !foundColorCodes.contains(field.getColorCode()))
				.peek(field -> foundColorCodes.add(field.getColorCode()))
				.forEach(field -> System.out.println("Fields of color: '" + field.getColorCode() + "' are all owned by '" + field.getCurrentOwner().getName() + "'."));
	}

	private List<MonopolyBoardField> getFieldsOwnedByPlayer(Player player, MonopolyBoard board) {
		return Arrays.stream(board.getAllFields())
				.filter(field -> field.getCurrentOwner() == player)
				.collect(Collectors.toList());
	}

	class AuctionResult {

		Player chosenPlayer;
		int priceToPay;

		AuctionResult(final Player chosenPlayer, int priceToPay) {
			this.chosenPlayer = chosenPlayer;
			this.priceToPay = priceToPay;
		}
	}
}
