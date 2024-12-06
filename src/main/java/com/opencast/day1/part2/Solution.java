package com.opencast.day1.part2;

import java.util.List;

class Solution {
    private Solution() {}

    public static long getSimilarity(List<String> inputs) {
        var mappedInputs = inputs.stream()
                .map(input -> input.replaceAll(" +", ";"))
                .map(input -> input.split(";"))
                .toList();

        var secondList = mappedInputs.stream()
                .map(arr -> Integer.valueOf(arr[1]))
                .toList();

        return mappedInputs.stream()
                .map(arr -> Integer.valueOf(arr[0]))
                .map(id -> countSimilarity(id, secondList))
                .mapToLong(l -> l)
                .sum();
    }

    private static long countSimilarity(int testedId, List<Integer> referenceList) {
        return testedId * referenceList.stream()
                .filter(id -> id == testedId)
                .count();
    }
}
