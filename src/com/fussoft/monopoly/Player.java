package com.fussoft.monopoly;


import com.fussoft.monopoly.strategy.PlayerStrategy;
import com.fussoft.monopoly.strategy.PlayerStrategy200;

import java.util.*;
import java.util.stream.Collectors;

public class Player {

	private final String name;
	private int balance;
	private int boardPosition;
	private boolean isInJail;

	private int roundsInJail;

	private int passedStartField;

	private boolean isBankrupt;

	private PlayerStrategy playerStrategy;

	public Player (String name, int initialBalance) {
		this.name = name;
		this.balance = initialBalance;
		this.boardPosition = 0;
		this.isInJail = false;
		this.roundsInJail = 0;
		this.passedStartField = 0;
		this.isBankrupt = false;

		this.playerStrategy = new PlayerStrategy200();
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public int getBoardPosition() {
		return boardPosition;
	}

	public boolean isInJail() {
		return isInJail;
	}

	public int getRoundsInJail() {
		return roundsInJail;
	}

	public int getPassedStartField() {
		return passedStartField;
	}

	public void addRoundInJail() {
		this.roundsInJail++;
	}

	public int moveSteps(final int steps, final int maxFieldIndex, final int goToJailIndex, final int jailIndex) {
		// players are allowed to move, so they couldn't be in jail
		// but: check for go-to-jail and if so, move to jail
		isInJail = false;
		boardPosition+=steps;
		if (boardPosition > maxFieldIndex) {
			System.out.println("Player '" + this.name + "' crossed the START field.");
			boardPosition -= maxFieldIndex;
			balance += 200;
			passedStartField++;
		}
		if (boardPosition == goToJailIndex) {
			boardPosition = jailIndex;
			isInJail = true;
			roundsInJail++;
			System.out.println("Player '" + this.name + "' goes to jail.");
		}
		return boardPosition;
	}

	public void payForProperty(final int priceToPay) {
		balance -= priceToPay;
	}

	public void getPayedForProperty(final int moneyToGet) {
		balance += moneyToGet;
	}

	public void getPayedRentForProperty(final int paidRent) {
		balance += paidRent;
	}

	public void payRentForProperty(final int rentToPay) {
		balance -= rentToPay;
	}

	public void setIsBankrupt(final boolean isBankrupt) {
		this.isBankrupt = isBankrupt;
	}

	public boolean isBankrupt() {
		return isBankrupt;
	}

	public int payHouseOrHotelForProperty(final MonopolyBoardField field) {
		balance -= field.getPriceHouseAndHotel();
		return balance;
	}

	/**
	 *
	 * @param remainingPayment Debt to be paid be selling houses.
	 * @param allFields All the fields on the board.
	 * @return Remaining debt after selling houses.
	 */
	public int sellHouses(int remainingPayment, MonopolyBoardField[] allFields) {
		// sort the player's properties by
		final List<MonopolyBoardField> fieldsSortedByHousesAsc = Arrays.stream(allFields)
				.filter(field -> field.getCurrentOwner() == this)
				.filter(field -> field.getNumberOfHouses() > 0)
				.sorted(Comparator.comparingInt(MonopolyBoardField::getNumberOfHouses))
				.collect(Collectors.toList());

		int gainFromHouses = 0;
		if (!fieldsSortedByHousesAsc.isEmpty()) {
			System.out.println("Player '" + name + "' can sell houses (or hotel) to pay the debt, as they have " + fieldsSortedByHousesAsc.size() + " properties with houses.");
			int listPosition = 0;
			while (gainFromHouses < remainingPayment
					&& listPosition >= 0) {
				final int moneyFromSoldHouse = sellHouseFromColorCode(allFields, fieldsSortedByHousesAsc.get(listPosition).getColorCode());
				if (moneyFromSoldHouse == 0) {
					// don't get anymore money from houses of this color code, get (and check) the next one
					listPosition = getIndexOfNextColorCode(fieldsSortedByHousesAsc, listPosition);
				} else {
					gainFromHouses += moneyFromSoldHouse;
				}
			}
		}

		return gainFromHouses;
	}

	/**
	 *
	 * @param remainingPayment Debt to be paid be returning properties.
	 * @param allFields All the fields on the board.
	 * @return A list of returned properties and their sum'ed  value.
	 */
	public PropertiesRecord provideProperties(int remainingPayment, MonopolyBoardField[] allFields) {

		final List<MonopolyBoardField> fieldsSortedPropertyValueAsc = Arrays.stream(allFields)
				.filter(field -> field.getCurrentOwner() == this)
				.filter(field -> field.getNumberOfHouses() == 0)
				.sorted(Comparator.comparingInt(MonopolyBoardField::getValue))
				.collect(Collectors.toList());

		int moneyFromReturnedProperties = 0;
		final List<MonopolyBoardField> returnedProperties = new ArrayList<>();
		int listIndex = 0;
		while (moneyFromReturnedProperties < remainingPayment
				&& listIndex < fieldsSortedPropertyValueAsc.size()) {

			final MonopolyBoardField currentProperty = fieldsSortedPropertyValueAsc.get(listIndex);
			returnedProperties.add(currentProperty);
			moneyFromReturnedProperties += currentProperty.getValue();
			System.out.println("Player '" + name + "' returned property '" + currentProperty.getName() + "'. Remaining debt:" + (remainingPayment - moneyFromReturnedProperties));
			listIndex++;
		}

		return new PropertiesRecord(returnedProperties, moneyFromReturnedProperties);
	}


	public void tradePropertiesWithOtherPlayers(Player[] players, MonopolyBoardField[] allFields) {

		final List<MonopolyBoardField> interestingProperties = findInterestingMissingPropertiesOwnedBySomeoneElse(allFields);

		final List<MonopolyBoardField> deals = interestingProperties.stream()
				.filter(property -> property.getCurrentOwner().askForProperty(property, this, allFields))
				.collect(Collectors.toList());

		deals.forEach(property -> {
			if (balance > (property.getValue() - playerStrategy.getMinBalanceForPurchasingAPropertyOrHouse())) {
				payForProperty(property.getValue());
				property.getCurrentOwner().getPayedForProperty(property.getValue());
				property.switchOwner(this, allFields);
			}
		});

	}

	private List<MonopolyBoardField> findInterestingMissingPropertiesOwnedBySomeoneElse(MonopolyBoardField[] allFields) {

		final Map<MonopolyBoardField.COLOR_CODE, Integer> colorCodePLayerCountMap = new HashMap<>();
		final Map<MonopolyBoardField.COLOR_CODE, Integer> colorCodeTotalCountMap = new HashMap<>();

		// get all player fields and save the count of same colorcodes in a map
		final List<MonopolyBoardField> playersFields = Arrays.stream(allFields)
				.filter(field -> field.getCurrentOwner() == this)
				.peek(field -> {
					if (!colorCodePLayerCountMap.containsKey(field.getColorCode())) {
						colorCodePLayerCountMap.put(field.getColorCode(), 1);
						colorCodeTotalCountMap.put(field.getColorCode(), field.getSameColorCount());
					} else {
						colorCodePLayerCountMap.put(field.getColorCode(), colorCodePLayerCountMap.get(field.getColorCode()) + 1);
					}
				})
				.collect(Collectors.toList());

		// get all colorcodes of the player's fields, where the player owns all accept one field of that colorcode
		final List<MonopolyBoardField.COLOR_CODE> interestingColorCodes = colorCodePLayerCountMap.entrySet().stream()
				.filter(entrySet -> entrySet.getValue().intValue() == (colorCodeTotalCountMap.get(entrySet.getKey()).intValue() - 1))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());

		// get the all fields of the interesting colorcode, which are not owned by this player
		List<MonopolyBoardField> interestingProperties = Arrays.stream(allFields)
				.filter(field -> interestingColorCodes.contains(field.getColorCode()))
				.filter(field -> field.getCurrentOwner() != this)
				.collect(Collectors.toList());

		if (!interestingProperties.isEmpty()) {
			System.out.println("Player '" + name + "' is currently interested in the following properties: ");
			interestingProperties.forEach(field -> System.out.println("\tProperty: '" + field.getName() + "'"));
		} else {
			System.out.println("Player '" + name + "' is currently NOT interested in any property: ");

		}
		return interestingProperties;
	}

	private boolean askForProperty(MonopolyBoardField property, Player askingPlayer, MonopolyBoardField[] allFields) {

		final long numberOfColorCodesOfTheAskingPlayer = Arrays.stream(allFields)
				.filter(field -> field.canBuyHouse())
				.filter(field -> field.getCurrentOwner() == askingPlayer)
				.map(MonopolyBoardField::getColorCode)
				.distinct()
				.count();

		return (numberOfColorCodesOfTheAskingPlayer < 3);
	}

	public void checkAndBuyHouses(MonopolyBoardField boardField, MonopolyBoardField[] allFields) {
		while(checkAndBuyNextHouse(allFields)) {
			System.out.println("Player '" + name + "'(" + balance + ") tries to buy a house.");
		}
	}

	private boolean checkAndBuyNextHouse(MonopolyBoardField[] allFields) {
		final int moneyToSpentOnBuying = balance - getPlayerMinBalance();
		if (moneyToSpentOnBuying < MonopolyBoard.MIN_PRICE_FOR_A_HOUSE) {
			System.out.println("Player '" + name + "'(" + balance + ") does not have enough money to buy a house.");
			return false;
		}

		final MonopolyBoardField.COLOR_CODE colorCodeWithMostHousesAndMostExpensiveHousePrice = Arrays.stream(allFields)
				.filter(field -> field.getCurrentOwner() == this)
				.filter(MonopolyBoardField::canBuyHouse)
				.filter(field -> field.getPriceHouseAndHotel() <= moneyToSpentOnBuying)
				.sorted(Comparator.comparingInt(MonopolyBoardField::getNumberOfHouses).reversed())
				.max(Comparator.comparingInt(MonopolyBoardField::getPriceHouseAndHotel))
				.map(MonopolyBoardField::getColorCode)
				.orElse(null);

		if (colorCodeWithMostHousesAndMostExpensiveHousePrice == null) {
			System.out.println("Player '" + name + "'(" + balance + ") does not have a property to buy a house for.");
			return false;
		}

		System.out.println("Player '" + name + "'(" + balance + ") will buy a house (or hotel) for property with color '" + colorCodeWithMostHousesAndMostExpensiveHousePrice.name() + "'.");
		final MonopolyBoardField fieldToBuyHouseFor = Arrays.stream(allFields)
				.filter(field -> field.getColorCode() == colorCodeWithMostHousesAndMostExpensiveHousePrice)
				.min(Comparator.comparingInt(MonopolyBoardField::getNumberOfHouses))
				.orElse(null);

		if (fieldToBuyHouseFor == null) {
			System.out.println("Player '" + name + "'(" + balance + ") - wanted to pick from a color - THIS SHOULD NOT HAPPEN.");
			return false;
		}

		System.out.println("Player '" + name + "'(" + balance + ") buys a house (or hotel) for property '" + fieldToBuyHouseFor.getName() + "'(price:" + fieldToBuyHouseFor.getPriceHouseAndHotel() + ").");
		payHouseOrHotelForProperty(fieldToBuyHouseFor);
		fieldToBuyHouseFor.buyHouseOrHotel();

		return true;
	}

	public int getPlayerMinBalance() {
		return playerStrategy.getMinBalanceForPurchasingAPropertyOrHouse();
	}

	private int sellHouseFromColorCode(MonopolyBoardField[] allFields, MonopolyBoardField.COLOR_CODE colorCode) {
		final MonopolyBoardField colorCodeFieldWithMostHouse = Arrays.stream(allFields)
				.filter(field -> field.getColorCode() == colorCode)
				.filter(field -> field.getNumberOfHouses() > 0)
				.max(Comparator.comparingInt(MonopolyBoardField::getNumberOfHouses)).orElse(null);

		int gainFromHouse = 0;
		if (colorCodeFieldWithMostHouse != null) {
			// sell the house
			colorCodeFieldWithMostHouse.sellHouseOrHotel();
			// ...and get the money (which is half the price that you have to pay when purchasing it!)
			gainFromHouse = colorCodeFieldWithMostHouse.getPriceHouseAndHotel() / 2;
			this.balance += gainFromHouse;
			System.out.println("Player '" + name + "' sold a house (or hotel) from property '" + colorCodeFieldWithMostHouse.getName() + "' and got " + gainFromHouse + " back.");
		}
		return gainFromHouse;
	}

	private int getIndexOfNextColorCode(List<MonopolyBoardField> fieldsSortedByHousesAsc, int currentListPosition) {
		final MonopolyBoardField.COLOR_CODE currentColorCode = fieldsSortedByHousesAsc.get(currentListPosition).getColorCode();

		int nextColorCodeIndex = currentListPosition + 1;
		while (nextColorCodeIndex < fieldsSortedByHousesAsc.size()
				&& fieldsSortedByHousesAsc.get(nextColorCodeIndex).getColorCode() == currentColorCode) {
			nextColorCodeIndex++;
		}
		if (nextColorCodeIndex >= fieldsSortedByHousesAsc.size()) {
			nextColorCodeIndex = -1;
		} else {
			System.out.println("Select next colorCode with houses. Old code:'" + currentColorCode + "', new code:'" + fieldsSortedByHousesAsc.get(nextColorCodeIndex).getColorCode() + "'");
		}
		return nextColorCodeIndex;
	}
}
