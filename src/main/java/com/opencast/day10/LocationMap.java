package com.opencast.day10;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocationMap {
    private final int[][] map;
    private final Set<Position> trailHeads;

    public LocationMap(List<String> mapDescription) {
        int sizeY = mapDescription.size();
        int sizeX = mapDescription.getFirst().length();
        map = new int[sizeY][sizeX];
        trailHeads = new HashSet<>();

        for (int y = 0; y < sizeY; y++) {
            var line = mapDescription.get(y).toCharArray();
            for (int x = 0; x < sizeX; x++) {
                map[y][x] = line[x] - 48;
                if (line[x] == '0') {
                    trailHeads.add(new Position(x, y));
                }
            }
        }
    }

    public Set<Position> getTrailHeads() {
        return trailHeads;
    }

    public int getHeightAtPosition(int xPos, int yPos) {
        try {
            return map[yPos][xPos];
        } catch (IndexOutOfBoundsException ex) {
            return -2;
        }
    }
}
