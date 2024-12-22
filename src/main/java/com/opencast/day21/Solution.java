package com.opencast.day21;

import com.opencast.InputReader;

import java.util.*;

public class Solution {

    private static final Map<String, List<String>> moves = new HashMap<>();
    private static final Map<String, Long> cache = new HashMap<>();

    static {
        Map<Character, long[]> numericKeypad = mapButtons("789", "456", "123", " 0A");
        Map<Character, long[]> arrowKeypad = mapButtons(" ^A", "<v>");
        iterate(numericKeypad);
        iterate(arrowKeypad);
    }

    public static void main(String[] args) {
        List<String> input = InputReader.readInput("day21/input.txt");

        System.out.println("solution #1: " + input.stream()
                .mapToLong(line -> getComplexity(line, 2))
                .sum());

        System.out.println("\n-----------------------------------------------------------\n");

        System.out.println("solution #2: " + input.stream()
                .mapToLong(line -> getComplexity(line, 25))
                .sum());
    }

    public static long part1(List<String> input) {
        return input.stream()
                .mapToLong(line -> getComplexity(line, 2))
                .sum();
    }

    public static long part2(List<String> input) {
        return input.stream()
                .mapToLong(line -> getComplexity(line, 25))
                .sum();
    }

    private static void iterate(Map<Character, long[]> numericKeypad) {
        for (Map.Entry<Character, long[]> entry1 : numericKeypad.entrySet()) {
            for (Map.Entry<Character, long[]> entry2 : numericKeypad.entrySet()) {
                moves.put(entry1.getKey() + "" + entry2.getKey(), findMoves(entry1.getValue(), entry2.getValue(), numericKeypad.get(' ')));
            }
        }
    }

    private static Map<Character, long[]> mapButtons(String... rows) {
        Map<Character, long[]> keypad = new HashMap<>();
        for (long y = 0; y < rows.length; y++) {
            String row = rows[(int) y];
            for (long x = 0; x < row.length(); x++) {
                keypad.put(row.charAt((int) x), new long[]{x, y});
            }
        }
        return keypad;
    }

    private static List<String> findMoves(long[] from, long[] to, long[] avoid) {
        List<String> result = new ArrayList<>();
        if (Arrays.equals(from, avoid) || Arrays.equals(to, avoid)) {
            return result;
        }
        if (Arrays.equals(from, to)) {
            result.add("A");
            return result;
        }
        if (from[0] < to[0]) {
            for (String move : findMoves(new long[]{from[0] + 1, from[1]}, to, avoid)) {
                result.add(">" + move);
            }
        }
        if (from[0] > to[0]) {
            for (String move : findMoves(new long[]{from[0] - 1, from[1]}, to, avoid)) {
                result.add("<" + move);
            }
        }
        if (from[1] < to[1]) {
            for (String move : findMoves(new long[]{from[0], from[1] + 1}, to, avoid)) {
                result.add("v" + move);
            }
        }
        if (from[1] > to[1]) {
            for (String move : findMoves(new long[]{from[0], from[1] - 1}, to, avoid)) {
                result.add("^" + move);
            }
        }
        return result;
    }


    private static long countMinimumSteps(String buttons, long directionalConsoles) {
        String key = buttons + ":" + directionalConsoles;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (directionalConsoles == 0) {
            return buttons.length();
        }
        long steps = 0;
        char a = 'A';
        for (char b : buttons.toCharArray()) {
            steps += moves.get(a + "" + b).stream()
                    .mapToLong(move -> countMinimumSteps(move, directionalConsoles - 1))
                    .min()
                    .orElse(0);
            a = b;
        }
        cache.put(key, steps);
        return steps;
    }

    private static long getComplexity(String buttons, long robots) {
        long number = Long.parseLong(buttons.substring(0, buttons.length() - 1));
        long steps = countMinimumSteps(buttons, robots + 1);
        System.out.println(buttons + " -(" + robots + ")-> " + number + " * " + steps + " = " + (number * steps) + ".");
        return number * steps;
    }
}
