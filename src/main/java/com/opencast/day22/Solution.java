package com.opencast.day22;

import com.opencast.InputReader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        List<Long> input = readLines("day22/input.txt");

        // Part #1
        List<Long> secret2000s = input.stream()
                .map(secret -> pseudoRandom(secret).skip(1999).findFirst().orElseThrow())
                .collect(Collectors.toList());

        System.out.println("solution #1: " + sum(secret2000s));

        System.out.println("\n-----------------------------------------------------------\n");

        // Part #2
        Map<String, Long> bestPrices = new HashMap<>();

        for (long secret : input) {
            Set<String> visited = new HashSet<>();
            long price = secret % 10;
            long[] window = new long[4];

            Iterator<Long> iterator = pseudoRandom(secret).limit(2000).iterator();
            while (iterator.hasNext()) {
                long newPrice = (iterator.next() % 10);
                System.arraycopy(window, 1, window, 0, 3);
                window[3] = newPrice - price;

                if (window[0] != 0) {
                    String priceKey = key(window);
                    if (!visited.contains(priceKey)) {
                        visited.add(priceKey);
                        bestPrices.put(priceKey, bestPrices.getOrDefault(priceKey, 0L) + newPrice);
                    }
                }

                price = newPrice;
            }
        }

        System.out.println("solution #2: " + max(bestPrices.values()));
    }

    private static Stream<Long> pseudoRandom(long secret) {
        return Stream.iterate(secret, sec -> {
            long s1 = sec * 64;
            sec = sec ^ s1;
            sec = sec % 16777216;

            long s2 = sec / 32;
            sec = sec ^ s2;
            sec = sec % 16777216;

            long s3 = sec * 2048;
            sec = sec ^ s3;
            sec = sec % 16777216;

            return sec;
        });
    }

    private static List<Long> readLines(String filePath) {
        return InputReader.readInput(filePath).stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    private static long sum(List<Long> list) {
        return list.stream().mapToLong(Long::longValue).sum();
    }

    private static long max(Collection<Long> values) {
        return values.stream().mapToLong(Long::valueOf).max().orElse(0);
    }

    private static String key(long[] items) {
        return Arrays.stream(items).mapToObj(String::valueOf).collect(Collectors.joining(":"));
    }
}
