package com.opencast.day10.part1;

import com.opencast.day10.LocationMap;
import com.opencast.day10.Position;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

class Solution {
    private Solution() {}

    public static int numberOfReachableTrailHeads(List<String> input) {
        var locationMap = new LocationMap(input);

        return locationMap.getTrailHeads().stream()
                .mapToInt(e -> numberOfTrails(Collections.emptyList(), e.xPos(), e.yPos(), -1, locationMap).size())
                .sum();
    }

    private static List<Position> numberOfTrails(List<Position> previousEndPositions, int actualX, int actualY, int previousHeight, LocationMap locationMap) {
        int actualHeight = locationMap.getHeightAtPosition(actualX, actualY);
        var newEndPositions = new LinkedList<Position>(previousEndPositions);

        if (actualHeight < -1 || actualHeight - previousHeight != 1) {
            return previousEndPositions;
        }

        if (actualHeight >= 9 && actualHeight - previousHeight == 1) {
            newEndPositions.add(new Position(actualX, actualY));
            return newEndPositions;
        }

        return Stream.of(
                numberOfTrails(previousEndPositions, actualX - 1, actualY, actualHeight, locationMap),
                numberOfTrails(previousEndPositions,actualX + 1, actualY, actualHeight, locationMap),
                numberOfTrails(previousEndPositions, actualX, actualY - 1, actualHeight, locationMap),
                numberOfTrails(previousEndPositions, actualX, actualY + 1, actualHeight, locationMap)
        )
                .flatMap(Collection::stream)
                .distinct()
                .toList();
    }
}
