package com.opencast.day11;

import java.util.Arrays;
import java.util.HashMap;

class Solution {
    private Solution() {
    }

    private static final HashMap<Blink, Long> cache = new HashMap<>();

    public static long getNumberOfStonesAfterBlinks(String input, int numberOfBlinks) {
        return  Arrays.stream(input.split(" ")).map(Long::parseLong)
                .mapToLong(s -> getNumberOfChildren(s, 0, numberOfBlinks))
                .sum();
    }

    private static long getNumberOfChildren(long stoneId, int actualBlink, int totalBlinks) {
        var blink = new Blink(stoneId, totalBlinks - actualBlink);
        if (cache.containsKey(blink)) {
            return cache.get(blink);
        }

        if (actualBlink >= totalBlinks) {
            cache.put(blink, 1L);
            return 1;
        }

        if (stoneId == 0L) {
            long toReturn = getNumberOfChildren(1L, actualBlink + 1, totalBlinks);
            cache.put(blink, toReturn);
            return toReturn;
        }

        int stoneLength = stoneLength(stoneId);

        if (stoneLength % 2 == 0) {
            int divider = getDivider(stoneLength / 2);
            long toReturn = getNumberOfChildren(stoneId / divider, actualBlink + 1, totalBlinks)
                    + getNumberOfChildren(stoneId % divider, actualBlink + 1, totalBlinks);
            cache.put(blink, toReturn);
            return toReturn;
        }

        long toReturn = getNumberOfChildren(stoneId * 2024L, actualBlink + 1, totalBlinks);
        cache.put(blink, toReturn);
        return toReturn;
    }

    private static int stoneLength(long numberToTest) {
        int numberOfDigits = 1;
        long comparator = 1;

        while (numberToTest >= 10L * comparator) {
            numberOfDigits++;
            comparator = comparator * 10;
        }

        return numberOfDigits;
    }

    private static int getDivider(int power) {
        int divider = 1;

        for (int i = 0; i < power; i++) {
            divider = divider * 10;
        }

        return divider;
    }


}
