package com.fussoft.monopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Application {

	static final Random random = new Random();
	
	static final Map<Integer, Integer> pipes1   = new HashMap<>();
	static final Map<Integer, Integer> pipes2   = new HashMap<>();
	static final Map<Integer, Integer> ladders1 = new HashMap<>();
	static final Map<Integer, Integer> ladders2 = new HashMap<>();
	
	
	
	static final Map<Integer, Integer> results = new HashMap<>();
	static final int maxRounds = 2000;
	static final int games = 100000;
	static final int displayRows = 200;
	
	static {

	}

	static List<Integer> shortestRound = new ArrayList<>();

	public static void main(String[] args) {
		for (int i = 0; i < games; i++) {
			int position = 1;
			int round = 1;
			final List<Integer> thisRound = new ArrayList<>();
			do {
				thisRound.add(position);
				final int diceValue = roleTheDice();
				if (position + diceValue > 100) {
					// have to land on the 100, not beyond
					continue;
				}
				position += diceValue;
				thisRound.add(position);
//				position = applyPipesAndLadders1(position);
				position = applyPipesAndLadders2(position);
				thisRound.add(position);
				round++;
			} while (position < 100 && round < maxRounds);
			
			final Integer roundsO = Integer.valueOf(round - 1);
			results.put(roundsO, Integer.valueOf(results.get(roundsO).intValue() + 1));
			if (shortestRound.size() == 0 || thisRound.size() < shortestRound.size()) {
				shortestRound = thisRound;
			}
		}
		printResults();
	}

	private static int applyPipesAndLadders1(int position) {
		final int newPosition;
		Integer positionObject = Integer.valueOf(position);
		if (pipes1.containsKey(positionObject)) {
			newPosition = pipes1.get(positionObject).intValue();
//			System.out.println("after pipe: " + newPosition);
		} else if (ladders1.containsKey(positionObject)) {
			newPosition = ladders1.get(positionObject).intValue();
//			System.out.println("after ladder: " + newPosition);
		} else {
			newPosition = position;
		}
		return newPosition;
	}

	private static int applyPipesAndLadders2(int position) {
		final int newPosition;
		Integer positionObject = Integer.valueOf(position);
		if (pipes2.containsKey(positionObject)) {
			newPosition = pipes2.get(positionObject).intValue();
//			System.out.println("after pipe: " + newPosition);
		} else if (ladders2.containsKey(positionObject)) {
			newPosition = ladders2.get(positionObject).intValue();
//			System.out.println("after ladder: " + newPosition);
		} else {
			newPosition = position;
		}
		return newPosition;
	}

	private static int roleTheDice() {
		return 2 + random.nextInt(11);
	}
	
	private static void printResults() {
		System.out.println("Results:");
		System.out.println("Shortest round (" + shortestRound.size() + " rounds):");
		for (int i = 0; i < shortestRound.size(); ) {
			System.out.println("position:" + shortestRound.get(i++) + " dice position:" + shortestRound.get(i++) + " p/l position:" + shortestRound.get(i++));
		}
		System.out.println();
		final int[] maxValue = new int[1];
		maxValue[0] = 0;
		results.values().forEach(v -> maxValue[0] = Math.max(v, maxValue[0]));
				
		final int maxRoundsPeak = results.values().stream().mapToInt(v -> v).max().orElse(1);
		System.out.println("maxRoundPeak: " + maxRoundsPeak);
		final int quotient = Math.max(maxRoundsPeak / displayRows, 1);
//		System.out.println("quotient: " + quotient);

		for (int i = 0; i<maxRounds; i++) {
			int value = results.get(Integer.valueOf(i));
			int displayBlocks = value / quotient;
			if (value > 0 && displayBlocks < 1) {
				displayBlocks = 1;
			}
			System.out.println(i + ": " + "#".repeat(displayBlocks) + ":" + value);
		}
	}
}
