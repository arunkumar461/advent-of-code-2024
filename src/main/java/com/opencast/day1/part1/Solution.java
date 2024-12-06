package com.opencast.day1.part1;

import java.util.List;

import static java.lang.Math.abs;

class Solution {
    private Solution() {}

    public static int getDistancesSummary(List<String> inputs) {
        var mappedInputs = inputs.stream()
                .map(input -> input.replaceAll(" +", ";"))
                .map(input -> input.split(";"))
                .toList();

        var firstList = mappedInputs.stream()
                .map(arr -> Integer.valueOf(arr[0]))
                .sorted()
                .toList();

        var secondList = mappedInputs.stream()
                .map(arr -> Integer.valueOf(arr[1]))
                .sorted()
                .toList();

        int result = 0;

        for (int i = 0; i < firstList.size(); i ++) {
            result += abs(firstList.get(i) - secondList.get(i));
        }

        return result;
    }
}
