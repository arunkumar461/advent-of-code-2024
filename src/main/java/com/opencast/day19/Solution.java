package com.opencast.day19;

import com.opencast.InputReader;

import java.util.*;
import java.util.function.Function;

public class Solution {

    public static void main(String[] args) {
        // Read input file
        List<String> input = InputReader.readInput("day19/input.txt");
        Set<String> towels = new HashSet<>(Arrays.asList(input.getFirst().split(", ")));
        long maxSize = towels.stream().mapToLong(String::length).max().orElse(0);
        List<String> patterns = input.subList(1, input.size());

        // Initialize the memoized function
        final Function<String, Long>[] findPossibilities = new Function[1];
        findPossibilities[0] = memoize(pattern -> {
            if (pattern.isEmpty()) {
                return 1L;
            }

            long count = 0;
            for (int i = 1; i <= pattern.length() && i <= maxSize; i++) {
                String subPattern = pattern.substring(i);
                if (towels.contains(pattern.substring(0, i))) {
                    count += findPossibilities[0].apply(subPattern);
                }
            }
            return count;
        });

        long solution1 = patterns.stream().filter(pattern -> findPossibilities[0].apply(pattern) > 0).count();
        System.out.println("solution #1: " + solution1);

        System.out.println("\n-----------------------------------------------------------\n");

        long solution2 = patterns.stream().mapToLong(findPossibilities[0]::apply).sum();
        System.out.println("solution #2: " + solution2);
    }

    private static <T, R> Function<T, R> memoize(Function<T, R> function) {
        Map<T, R> cache = new HashMap<>();
        return input -> {
            if (cache.containsKey(input)) {
                return cache.get(input);
            }
            R result = function.apply(input);
            cache.put(input, result);
            return result;
        };
    }
}
