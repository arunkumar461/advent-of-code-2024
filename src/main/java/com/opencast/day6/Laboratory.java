package com.opencast.day6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Laboratory {
    private final Field[][] map;
    private Guard guard;
    private final Set<Field> obstacles;
    private final int rowSize;
    private final int columnSize;

    public Laboratory(List<String> input) {
        obstacles = new HashSet<>();
        columnSize = input.size();
        rowSize = input.getFirst().length();
        map = new Field[columnSize][rowSize];

        for (int y = 0; y < columnSize; y++) {
            var row = input.get(y).toCharArray();

            for (int x = 0; x < rowSize; x++) {
                switch (row[x]) {
                    case '.' -> map[y][x] = new Field(x, y, Field.Type.EMPTY);
                    case '#' -> {
                        Field field = new Field(x, y, Field.Type.OBSTACLE);
                        map[y][x] = field;
                        obstacles.add(field);
                    }
                    case '^' -> {
                        map[y][x] = new Field(x, y, Field.Type.OCCUPIED);
                        guard = new Guard(x, y, Guard.FacingDirection.NORTH);
                    }
                    default -> throw new RuntimeException("Unexpected char");
                }
            }
        }

        if (guard == null) {
            throw new RuntimeException("Guard not found");
        }
    }

    public void setField(int x, int y, Field.Type type) {
        map[y][x] = new Field(x, y, type);
    }

    public Set<Field> getObstacles() {
        return obstacles;
    }

    public Guard getGuard() {
        return guard;
    }

    public void turnGuardRight() {
        guard.turnRight();
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public long getNumberOfOccupiedFields() {
        return Arrays.stream(map)
                .flatMap(Arrays::stream)
                .filter(field -> field.type().equals(Field.Type.OCCUPIED))
                .count();
    }

    public boolean isFieldObstacle(int x, int y) {
        return map[y][x].type().equals(Field.Type.OBSTACLE);
    }

    public void addObstacle(int x, int y) {
        obstacles.add(map[y][x]);
    }

    public void removeObstacle(int x, int y) {
        obstacles.remove(map[y][x]);
    }
}
