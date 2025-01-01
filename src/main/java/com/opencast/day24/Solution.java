package com.opencast.day24;

import com.opencast.InputReader;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.readInput("day24/input.txt");

        Map<String, String> initialValues = input.stream()
                .takeWhile(line -> line.contains(": "))
                .map(split(": "))
                .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1]));

        Map<String, String[]> gates = input.stream()
                .dropWhile(line -> line.contains(": "))
                .filter(line -> !line.isEmpty())
                .map(split(" "))
                .collect(Collectors.toMap(parts -> parts[4], parts -> new String[]{parts[1], parts[0], parts[2]}));

        final Function<String, Long>[] getValueRef = new Function[1];
        getValueRef[0] = cache(out -> {
            if (initialValues.containsKey(out)) {
                return Long.parseLong(initialValues.get(out));
            }
            String[] gate = gates.get(out);
            String op = gate[0], a = gate[1], b = gate[2];
            return switch (op) {
                case "AND" -> getValueRef[0].apply(a) & getValueRef[0].apply(b);
                case "OR" -> getValueRef[0].apply(a) | getValueRef[0].apply(b);
                case "XOR" -> getValueRef[0].apply(a) ^ getValueRef[0].apply(b);
                default -> throw new IllegalStateException("Unknown operation: " + op);
            };
        });

        Function<String, Long> getValue = getValueRef[0];

        List<String> outputs = gates.keySet()
                .stream()
                .filter(name -> name.startsWith("z"))
                .sorted()
                .toList();

        String binaryResult = outputs.stream()
                .map(getValue)
                .map(Long::toBinaryString)
                .reduce((a, b) -> b + a)
                .orElse("");

        System.out.println("solution #1: " + Long.parseLong(binaryResult, 2));

        System.out.println("\n-----------------------------------------------------------\n");

        String xFirst = initialValues.keySet()
                .stream()
                .filter(name -> name.startsWith("x"))
                .min(Comparator.naturalOrder())
                .orElseThrow();

        String zLast = outputs.getLast();

        List<String> errors = spotAdditionCircuitErrors(gates, xFirst, zLast)
                .stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("solution #2: " + String.join(",", errors));
    }

    private static Function<String, String[]> split(String regex) {
        return line -> line.split(regex);
    }

    private static <T, R> Function<T, R> cache(Function<T, R> func) {
        Map<T, R> cache = new HashMap<>();
        return key -> {
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            R result = func.apply(key);
            cache.put(key, result);
            return result;
        };
    }

    private static List<String> spotAdditionCircuitErrors(Map<String, String[]> gates, String xFirst, String zLast) {
        List<String> errors = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : gates.entrySet()) {
            String out = entry.getKey();
            String[] gate = entry.getValue();
            String op = gate[0], a = gate[1], b = gate[2];

            if (out.startsWith("z") && !op.equals(out.equals(zLast) ? "OR" : "XOR")) {
                errors.add(out);
            }
            switch (op) {
                case "XOR":
                    if (!out.startsWith("z") && !a.startsWith("x")) {
                        errors.add(out);
                    }
                case "AND":
                    if (!a.startsWith("x")) {
                        String[] A = gates.get(a);
                        String[] B = gates.get(b);
                        if (isCarryOverGate(A, xFirst) && !isXorInputGate(B, xFirst)
                                || isXorInputGate(A, xFirst) && !isCarryOverGate(B, xFirst)) {
                            errors.add(b);
                        }
                        if (isCarryOverGate(B, xFirst) && !isXorInputGate(A, xFirst)
                                || isXorInputGate(B, xFirst) && !isCarryOverGate(A, xFirst)) {
                            errors.add(a);
                        }
                    }
                    break;
                case "OR":
                    if (isAndGate(gates.get(a))) errors.add(a);
                    if (isAndGate(gates.get(b))) errors.add(b);
            }
        }
        return errors;
    }

    private static boolean isCarryOverGate(String[] gate, String xFirst) {
        if (gate == null) return false;
        return xFirst.equals(gate[1]) ? "AND".equals(gate[0]) : !gate[1].startsWith("x") && "OR".equals(gate[0]);
    }

    private static boolean isXorInputGate(String[] gate, String xFirst) {
        return gate != null && gate[1].startsWith("x") && "XOR".equals(gate[0]);
    }

    private static boolean isAndGate(String[] gate) {
        return gate == null || !"AND".equals(gate[0]);
    }
}
