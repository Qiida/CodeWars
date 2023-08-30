package codo;

public class GoodVsEvil {

    final private static int[] goodWorthTable = {1, 2, 3, 3, 4, 10};
    final private static int[] evilWorthTable = {1, 2, 2, 2, 3, 5, 10};

    public static String battle(String goodAmounts, String evilAmounts) {
        int goodWorth = computeWorth(goodWorthTable, goodAmounts.split(" "));
        int evilWorth = computeWorth(evilWorthTable, evilAmounts.split(" "));

        if (goodWorth > evilWorth) {
            return "Battle Result: Good triumphs over Evil";
        } else if (evilWorth > goodWorth) {
            return "Battle Result: Evil eradicates all trace of Good";
        } else {
            return "Battle Result: No victor on this battle field";
        }
    }

    private static int computeWorth(int[] worthTable, String[] amountTable) {
        int accumulatedWorth = 0;
        for (int i=0; i<worthTable.length; i++) {
            int worth = worthTable[i];
            int amount = Integer.parseInt(amountTable[i]);
            accumulatedWorth += worth * amount;
        }
        return accumulatedWorth;
    }
}