package com.opencast.day20;

import com.opencast.InputReader;

import java.io.IOException;
import java.util.*;

public class Solution {
    static int sX = 0, sY = 0, eX = 0, eY = 0;
    static char[][] input;

    public static void main(String[] args) throws IOException {
        input = readCharArrays("day20/input.txt");

        forEach2D(input, 'S', (x, y) -> {
            sX = x;
            sY = y;
        });
        forEach2D(input, 'E', (x, y) -> {
            eX = x;
            eY = y;
        });

        Map<String, Position> trackPositions = new HashMap<>();
        trackPositions.put(key(sX, sY), new Position(sX, sY, 0));

        int x = sX, y = sY, lastX = 0, lastY = 0, dist = 0;
        while (x != eX || y != eY) {
            for (int[] neighbor : neighbors(x, y, 1)) {
                int nextX = neighbor[0], nextY = neighbor[1];
                if (input[nextY][nextX] != '#' && (nextX != lastX || nextY != lastY)) {
                    lastX = x;
                    lastY = y;
                    x = nextX;
                    y = nextY;
                    trackPositions.put(key(x, y), new Position(x, y, ++dist));
                    break;
                }
            }
        }

        int threshold = input.length < 100 ? 50 : 100;
        System.out.println("Solution #1: " + countCheats(trackPositions, 2, threshold));

        System.out.println("\n-----------------------------------------------------------\n");

        System.out.println("Solution #2: " + countCheats(trackPositions, 20, threshold));
    }

    static List<int[]> neighbors(int x, int y, int radius) {
        List<int[]> result = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = Math.abs(i) - radius; j + Math.abs(i) <= radius; j++) {
                if (i != 0 || j != 0) {
                    result.add(new int[]{x + i, y + j, Math.abs(i) + Math.abs(j)});
                }
            }
        }
        return result;
    }

    static int countCheats(Map<String, Position> trackPositions, int radius, int threshold) {
        int cheatCount = 0;
        for (Position pos : trackPositions.values()) {
            for (int[] neighbor : neighbors(pos.x, pos.y, radius)) {
                int nextX = neighbor[0], nextY = neighbor[1], steps = neighbor[2];
                Position nextPos = trackPositions.get(key(nextX, nextY));
                if (nextPos != null && nextPos.dist - pos.dist - steps >= threshold) {
                    cheatCount++;
                }
            }
        }
        return cheatCount;
    }

    static char[][] readCharArrays(String filename) throws IOException {
        List<String> lines = InputReader.readInput(filename);
        char[][] array = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            array[i] = lines.get(i).toCharArray();
        }
        return array;
    }

    static void forEach2D(char[][] array, char val, ForEach2DCallback callback) {
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                if (array[y][x] == val) {
                    callback.apply(x, y);
                }
            }
        }
    }

    static String key(Object... items) {
        return String.join(":", Arrays.stream(items)
                .map(String::valueOf)
                .toArray(String[]::new));
    }

    interface ForEach2DCallback {
        void apply(int x, int y);
    }

    static class Position {
        int x, y, dist;

        Position(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
