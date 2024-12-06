package com.opencast.day2.part1;

import java.util.List;

class Solution {
    private Solution() {}

    public static long countNumberOfSafeReports(List<String> inputs) {
        return inputs.stream()
                .map(input -> input.split(" "))
                .filter(Solution::isReportSafe)
                .count();
    }

    private static boolean isReportSafe(String[] levels) {
        boolean isIncreasing = Integer.parseInt(levels[0]) < Integer.parseInt(levels[1]);
        for (int i = 1; i < levels.length; i++) {
            if (!isOperationSafe(i, isIncreasing, levels)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isOperationSafe(int position, boolean isIncreasing, String[] levels) {
        if (isIncreasing) {
            return Integer.parseInt(levels[position]) - Integer.parseInt(levels[position - 1]) >= 1 &&
                    Integer.parseInt(levels[position]) - Integer.parseInt(levels[position - 1]) <= 3;
        }


        return Integer.parseInt(levels[position - 1]) - Integer.parseInt(levels[position]) >= 1 &&
                Integer.parseInt(levels[position - 1]) - Integer.parseInt(levels[position]) <= 3;
    }
}
