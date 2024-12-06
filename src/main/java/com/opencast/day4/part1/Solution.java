package com.opencast.day4.part1;

import java.util.List;

class Solution {

    private Solution() {}

    public static long getNumberOfXmassOccurrences(List<String> inputs) {
        return new XmasTable(inputs).getNumberOfOccurrencesInEveryDirection();
    }
}
