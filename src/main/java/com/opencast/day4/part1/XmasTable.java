package com.opencast.day4.part1;

import java.util.Arrays;
import java.util.List;

public class XmasTable {
    private final char[][] table;
    private final int columnSize;
    private final int rowSize;

    public XmasTable(List<String> tableRepresentation) {
        columnSize = tableRepresentation.size();
        rowSize = tableRepresentation.getFirst().length();
        table = new char[columnSize][rowSize];


        for (int i = 0; i < tableRepresentation.size(); i++) {
            var line = tableRepresentation.get(i);

            table[i] = line.toCharArray();
        }
    }

    public long getNumberOfOccurrencesInEveryDirection() {
        return Arrays.stream(StandardDirection.values())
                .map(this::getNumberOfOccurrencesInSelectedDirection)
                .mapToLong(Long::longValue)
                .sum();
    }

    private long getNumberOfOccurrencesInSelectedDirection(StandardDirection standardDirection) {
        long result = 0L;

        for (int y = standardDirection.fromStartY; y < columnSize - standardDirection.fromEndY; y++) {
            for (int x = standardDirection.fromStartX; x < rowSize - standardDirection.fromEndX; x++) {
                if (table[y][x] == 'X' && containsXmasWord(y, x, standardDirection.nextX, standardDirection.nextY)) {
                    result++;
                }
            }
        }

        return result;
    }

    private boolean containsXmasWord(int yPos, int xPos, int nextX, int nextY) {
        char[] xmasChars = new char[]{'X', 'M', 'A', 'S'};

        for (int i = 1; i < 4; i++) {
            if (xmasChars[i] != table[yPos + (nextY * i)][xPos + (nextX * i)]) {
                return false;
            }
        }

        return true;
    }
}
