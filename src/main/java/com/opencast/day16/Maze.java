package com.opencast.day16;

import java.util.List;

public record Maze(int startX, int startY, int endX, int endY, MazeTile[][] maze) {
    public static Maze mapMaze(List<String> input) {
        int ySize = input.size();
        int xSize = input.getFirst().length();
        MazeTile[][] maze = new MazeTile[ySize][xSize];
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;

        for (int y = 0; y < ySize; y++) {
            char[] line = input.get(y).toCharArray();

            for (int x = 0; x < xSize; x++) {
                maze[y][x] = switch (line[x]) {
                    case '#' -> MazeTile.WALL;
                    case 'S' -> {
                        startX = x;
                        startY = y;
                        yield MazeTile.START;
                    }
                    case 'E' -> {
                        endX = x;
                        endY = y;
                        yield MazeTile.END;
                    }
                    default -> MazeTile.EMPTY;
                };
            }
        }

        return new Maze(startX, startY, endX, endY, maze);
    }
}
