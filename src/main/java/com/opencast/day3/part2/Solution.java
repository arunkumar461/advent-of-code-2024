package com.opencast.day3.part2;


import com.opencast.day3.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    private static final Pattern INSTRUCTION_MATCHER = Pattern.compile("mul\\(\\d{1,3}\\,\\d{1,3}\\)|do\\(\\)|don\\'t\\(\\)");
    private static final String DO_OPERATION = "do()";
    private static final String DONT_OPERATION = "don't()";
    private static boolean OPERATION_ENABLED = true;

    private Solution() {}

    public static long getSumOfMultiplications(List<String> inputs) {
        return inputs.stream()
                .flatMap(input -> getListOfOperations(input).stream())
                .map(o -> o.firstNumber() * o.secondNumber())
                .mapToLong(Integer::longValue)
                .sum();
    }

    private static List<Operation> getListOfOperations(String input) {
        Matcher matcher = INSTRUCTION_MATCHER.matcher(input);
        var operations = new ArrayList<Operation>();

        while (matcher.find()) {
            operations.add(mapToOperation(matcher.group()));
        }

        return operations.stream()
                .filter(Objects::nonNull)
                .toList();
    }

    private static Operation mapToOperation(String stringOperation) {
        if (stringOperation.equals(DO_OPERATION)) {
            OPERATION_ENABLED = true;
            return null;
        }

        if (stringOperation.equals(DONT_OPERATION)) {
            OPERATION_ENABLED = false;
            return null;
        }

        if (OPERATION_ENABLED) {
            var numbers = stringOperation.substring(4, stringOperation.length() - 1).split(",");

            return new Operation(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
        }

        return null;
    }
}
