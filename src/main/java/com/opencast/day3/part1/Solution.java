package com.opencast.day3.part1;

import com.opencast.day3.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    private static final Pattern MULTIPLIER_MATCHER = Pattern.compile("mul\\(\\d{1,3}\\,\\d{1,3}\\)");

    private Solution() {}

    public static long getSumOfMultiplications(List<String> inputs) {
        return inputs.stream()
                .flatMap(input -> getListOfOperations(input).stream())
                .map(o -> o.firstNumber() * o.secondNumber())
                .mapToLong(Integer::longValue)
                .sum();
    }

    private static List<Operation> getListOfOperations(String input) {
        Matcher matcher = MULTIPLIER_MATCHER.matcher(input);
        var operations = new ArrayList<Operation>();

        while (matcher.find()) {
            operations.add(mapToOperation(matcher.group()));
        }

        return operations;
    }

    private static Operation mapToOperation(String stringOperation) {
        var numbers = stringOperation.substring(4, stringOperation.length() - 1).split(",");

        return new Operation(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
    }
}
