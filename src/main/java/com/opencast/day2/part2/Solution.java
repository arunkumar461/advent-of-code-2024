package com.opencast.day2.part2;

import java.util.List;

class Solution {
    private Solution() {}

    public static long countNumberOfSafeReports(List<String> inputs) {
        return inputs.stream()
                .map(input -> input.split(" "))
                .filter(levels -> isReportSafe(levels, true))
                .count();
    }

    private static boolean isReportSafe(String[] levels, boolean allowProblemFix) {
        boolean isIncreasing = Integer.parseInt(levels[0]) < Integer.parseInt(levels[1]);
        for (int i = 1; i < levels.length; i++) {
            if (!isOperationSafe(i, isIncreasing, levels)) {
                return allowProblemFix && tryOperationWithDifferentIgnores(levels);
            }
        }

        return true;
    }

    private static boolean tryOperationWithDifferentIgnores(String[] levels) {
        for (int i = 0; i < levels.length; i ++) {
            if (isReportSafe(copyLevelsWithoutElementOnPosition(levels, i), false)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isOperationSafe(int position, boolean isIncreasing, String[] levels) {
        if (isIncreasing) {
            return Integer.parseInt(levels[position]) - Integer.parseInt(levels[position - 1]) >= 1 &&
            Integer.parseInt(levels[position]) - Integer.parseInt(levels[position - 1]) <= 3;
        }


        return Integer.parseInt(levels[position - 1]) - Integer.parseInt(levels[position]) >= 1 &&
                Integer.parseInt(levels[position - 1]) - Integer.parseInt(levels[position]) <= 3;
    }

    private static String[] copyLevelsWithoutElementOnPosition(String[] levels, int positionToIgnore) {
        var result = new String[levels.length - 1];

        if (positionToIgnore >= 0) System.arraycopy(levels, 0, result, 0, positionToIgnore);

        if (levels.length - (positionToIgnore + 1) >= 0)
            System.arraycopy(levels, positionToIgnore + 1, result, positionToIgnore + 1 - 1, levels.length - (positionToIgnore + 1));

        return result;
    }
}
