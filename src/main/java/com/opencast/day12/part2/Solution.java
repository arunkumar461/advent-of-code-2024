package com.opencast.day12.part2;


import com.opencast.day12.Position;
import com.opencast.day12.Region;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private Solution() {
    }

    public static long getCostOfRegionSurrounding(List<String> input) {

        Position[][] map = mapPlantsPositions(input);

        return splitIntoRegions(map).stream()
                .mapToLong(r -> r.findNumberOfSides() * r.positions().size())
                .sum();
    }

    private static Position[][] mapPlantsPositions(List<String> input) {
        Position[][] map = new Position[input.size()][input.getFirst().length()];
        for (int y = 0; y < input.size(); y++) {
            var line = input.get(y).toCharArray();
            for (int x = 0; x < line.length; x++) {
                map[y][x] = new Position(line[x], x, y, false);
            }
        }

        return map;
    }

    private static Set<Region> splitIntoRegions(Position[][] map) {
        int xLen = map[0].length;
        int yLen = map.length;

        var result = new HashSet<Region>();

        for (int y = 0; y < yLen; y++) {
            for (int x = 0; x < xLen; x++) {
                if (!map[y][x].occupied()) {
                    result.add(new Region(
                            map[y][x].plantId(),
                            expandRegion(map[y][x], x, y, xLen, yLen, map)
                    ));
                }
            }
        }
        return result;
    }

    private static Set<Position> expandRegion(Position lastPosition, int x, int y, int xLen, int yLen, Position[][] map) {
        if (x < 0 || y < 0 || x >= xLen || y >= yLen) {
            return Set.of(lastPosition);
        }

        if (map[y][x].plantId() != lastPosition.plantId() || map[y][x].occupied()) {
            return Set.of(lastPosition);
        }

        var actualPosition = new Position(map[y][x].plantId(), x, y, true);
        map[y][x] = actualPosition;
        var northExpansion = expandRegion(actualPosition, x, y - 1, xLen, yLen, map);
        var southExpansion = expandRegion(actualPosition, x, y + 1, xLen, yLen, map);
        var eastExpansion = expandRegion(actualPosition, x + 1, y, xLen, yLen, map);
        var westExpansion = expandRegion(actualPosition, x - 1, y, xLen, yLen, map);

        var resultSet = new HashSet<>(Set.of(actualPosition));
        resultSet.addAll(northExpansion);
        resultSet.addAll(southExpansion);
        resultSet.addAll(eastExpansion);
        resultSet.addAll(westExpansion);
        return resultSet;
    }
}
