package com.opencast.day10.part2;

import com.opencast.day10.LocationMap;
import com.opencast.day10.Position;

import java.util.Collections;
import java.util.List;

class Solution {
    private Solution() {}

    public static int getTotalRating(List<String> input) {
        var locationMap = new LocationMap(input);

        return locationMap.getTrailHeads().stream()
                .mapToInt(e -> getRating(Collections.emptyList(), e.xPos(), e.yPos(), -1, locationMap))
                .sum();
    }

    private static int getRating(List<Position> previousEndPositions, int actualX, int actualY, int previousHeight, LocationMap locationMap) {
        int actualHeight = locationMap.getHeightAtPosition(actualX, actualY);

        if (actualHeight < -1 || actualHeight - previousHeight != 1) {
            return 0;
        }

        if (actualHeight >= 9 && actualHeight - previousHeight == 1) {
            return 1;
        }

        return getRating(previousEndPositions, actualX - 1, actualY, actualHeight, locationMap) +
                getRating(previousEndPositions,actualX + 1, actualY, actualHeight, locationMap) +
                getRating(previousEndPositions, actualX, actualY - 1, actualHeight, locationMap) +
                getRating(previousEndPositions, actualX, actualY + 1, actualHeight, locationMap);
    }
}
