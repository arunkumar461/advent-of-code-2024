package com.opencast.day12;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public record Region(char plantId, Set<Position> positions) {
    public long getFencesNumber() {
        return positions.stream()
                .mapToLong(p -> 4 - findNumberOfNeighbour(p))
                .sum();
    }

    private long findNumberOfNeighbour(Position position) {
        return positions.stream()
                .filter(p -> (abs(p.x() - position.x()) == 1 && abs(p.y() - position.y()) == 0)
                        || (abs(p.y() - position.y()) == 1 && abs(p.x() - position.x()) == 0))
                .count();
    }

    public long findNumberOfSides() {
        var groupedByY = positions.stream()
                .collect(Collectors.groupingBy(Position::y));
        var groupedByX = positions.stream()
                .collect(Collectors.groupingBy(Position::x));

        int yMin = groupedByY.keySet()
                .stream()
                .min(Integer::compare)
                .orElseThrow();

        int yMax = groupedByY.keySet()
                .stream()
                .max(Integer::compare)
                .orElseThrow();

        int xMin = groupedByX.keySet()
                .stream()
                .min(Integer::compare)
                .orElseThrow();

        int xMax = groupedByX.keySet()
                .stream()
                .max(Integer::compare)
                .orElseThrow();

        if (yMax == yMin && xMax == xMin) {
            return 4L;
        }

        int ySize = (yMax - yMin) + 3;
        int xSize = (xMax - xMin) + 3;
        char[][] normalisedRegion = new char[ySize][xSize];

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (x == 0 || y == 0 || x == xSize - 1 || y == ySize - 1) {
                    normalisedRegion[y][x] = '.';
                } else {
                    int xDiff = x;
                    normalisedRegion[y][x] = !groupedByY.containsKey(yMin + y - 1) ? '.' :
                            groupedByY.get(yMin + y - 1).stream()
                                    .anyMatch(p -> p.x() == (xMin + xDiff - 1)) ? 'A' : '.';
                }
            }
        }

        int normalisedY = normalisedRegion.length;
        int normalisedX = normalisedRegion[0].length;

        long yDown = normalisedY == 1 ? 1L : getSidesDownside(normalisedRegion, 1);
        long yUp =  normalisedY == 1 ? 1L : getSidesUpside(normalisedRegion, normalisedRegion.length - 2);
        long xRight = normalisedX == 1 ? 1L :  getSidesRightSide(normalisedRegion, 1);
        long xLeft = normalisedX == 1 ? 1L :  getSidesLeftside(normalisedRegion, normalisedRegion[0].length - 2);
        return yDown + yUp + xLeft + xRight;
    }

    private long getSidesDownside(char[][] normalisedRegion, int actualLine) {
        if (actualLine >= normalisedRegion.length) {
            return 0L;
        }

        char[] line = normalisedRegion[actualLine];
        char[] previousLine = normalisedRegion[actualLine - 1];

        long wallNumber = 0L;

        int lastWallPosition = -1;
        for (int i = 0; i < line.length; i++) {
            if (line[i] != previousLine[i]) {
                if (line[i] == 'A' &&  i - lastWallPosition > 1) {
                    wallNumber++;
                }

                if (line[i] == 'A') {
                    lastWallPosition = i;
                }
            }
        }

        return wallNumber + getSidesDownside(normalisedRegion, actualLine + 1);
    }

    private long getSidesUpside(char[][] normalisedRegion, int actualLine) {
        if (actualLine < 0) {
            return 0L;
        }

        char[] line = normalisedRegion[actualLine];
        char[] previousLine = normalisedRegion[actualLine + 1];

        long wallNumber = 0L;

        int lastWallPosition = -1;
        for (int i = 0; i < line.length; i++) {
            if (line[i] != previousLine[i]) {
                if (line[i] == 'A' &&  i - lastWallPosition > 1) {
                    wallNumber++;
                }

                if (line[i] == 'A') {
                    lastWallPosition = i;
                }
            }
        }

        return wallNumber + getSidesUpside(normalisedRegion, actualLine - 1);
    }

    private long getSidesLeftside(char[][] normalisedRegion, int actualLine) {
        if (actualLine < 0) {
            return 0L;
        }

        char[] line = getVerticalLine(normalisedRegion, actualLine);
        char[] previousLine = getVerticalLine(normalisedRegion, actualLine + 1);

        long wallNumber = 0L;

        int lastWallPosition = -1;
        for (int i = 0; i < line.length; i++) {
            if (line[i] != previousLine[i]) {
                if (line[i] == 'A' &&  i - lastWallPosition > 1) {
                    wallNumber++;
                }

                if (line[i] == 'A') {
                    lastWallPosition = i;
                }
            }
        }

        return wallNumber + getSidesLeftside(normalisedRegion, actualLine - 1);
    }

    private long getSidesRightSide(char[][] normalisedRegion, int actualLine) {
        if (actualLine >= normalisedRegion[0].length) {
            return 0L;
        }

        char[] line = getVerticalLine(normalisedRegion, actualLine);
        char[] previousLine = getVerticalLine(normalisedRegion, actualLine - 1);

        long wallNumber = 0L;

        int lastWallPosition = -1;
        for (int i = 0; i < line.length; i++) {
            if (line[i] != previousLine[i]) {
                if (line[i] == 'A' &&  i - lastWallPosition > 1) {
                    wallNumber++;
                }

                if (line[i] == 'A') {
                    lastWallPosition = i;
                }
            }
        }

        return wallNumber + getSidesRightSide(normalisedRegion, actualLine + 1);
    }

    private char[] getVerticalLine(char[][] normalisedRegion, int lineNumber) {
        int lineLength = normalisedRegion.length;
        char[] line = new char[lineLength];

        for (int i = 0; i < lineLength; i++) {
            line[i] = normalisedRegion[i][lineNumber];
        }

        return line;
    }
}
