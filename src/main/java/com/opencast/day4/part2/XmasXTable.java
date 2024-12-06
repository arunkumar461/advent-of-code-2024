package com.opencast.day4.part2;

import java.util.List;

public class XmasXTable {
    private final char[][] table;
    private final int columnSize;
    private final int rowSize;

    public XmasXTable(List<String> tableRepresentation) {
        columnSize = tableRepresentation.size();
        rowSize = tableRepresentation.getFirst().length();
        table = new char[columnSize][rowSize];


        for (int i = 0; i < tableRepresentation.size(); i++) {
            var line = tableRepresentation.get(i);

            table[i] = line.toCharArray();
        }
    }

    public long getNumberOfOccurrencesInEveryDirection() {
        long result = 0L;

        for (int y = 1; y < columnSize - 1; y++) {
            for (int x = 1; x < rowSize - 1; x++) {
                if (table[y][x] == 'A' && containsXmasWord(y, x)) {
                    result++;
                }
            }
        }

        return result;
    }

    private boolean containsXmasWord(int yPos, int xPos) {
        /*
        S.M
        .A.
        M.S
         */
        if (table[yPos - 1][xPos - 1] == table[yPos + 1][xPos + 1]) {
            return false;
        }

        int masNumber = 0;

        if (table[yPos - 1][xPos - 1] == 'M' && table[yPos + 1][xPos + 1] == 'S') {
            masNumber++;
        }

        if (table[yPos + 1][xPos - 1] == 'M' && table[yPos - 1][xPos + 1] == 'S') {
            masNumber++;
        }

        if (table[yPos - 1][xPos + 1] == 'M' && table[yPos + 1][xPos - 1] == 'S') {
            masNumber++;
        }

        if (table[yPos + 1][xPos + 1] == 'M' && table[yPos - 1][xPos - 1] == 'S') {
            masNumber++;
        }

        return masNumber == 2;
    }
}
