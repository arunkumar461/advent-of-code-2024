package com.opencast.day15.part1;

import com.opencast.day15.MapAndRobot;
import com.opencast.day15.Obstacle;

import java.util.List;

public class Solution {

    public static long countFinalPackageGPS(List<String> mapDescription, List<String> moves) {
        var mapAndRobot = resolveMapAndRobotPosition(mapDescription);
        Obstacle[][] map = mapAndRobot.map();
        int robotX = mapAndRobot.robotX();
        int robotY = mapAndRobot.robotY();

        var connectedMoves = String.join("", moves);

        for (int i = 0; i < connectedMoves.length(); i++) {
            var result = moveIfCan(map, robotX, robotY, connectedMoves.charAt(i), true);
            robotX = result != null ? result.robotX() : robotX;
            robotY = result != null ? result.robotY() : robotY;
        }

        printMap(map);
        return countPackageGps(map);
    }

    private static MapAndRobot resolveMapAndRobotPosition(List<String> mapLines) {
        Obstacle[][] map = new Obstacle[mapLines.size()][mapLines.getFirst().length()];
        int robotX = -1;
        int robotY = -1;

        for (int y = 0; y < mapLines.size(); y++) {
            var lineChars = mapLines.get(y).toCharArray();
            for (int x = 0; x < lineChars.length; x++) {
                map[y][x] = switch (lineChars[x]) {
                    case 'O' -> Obstacle.PACKAGE;
                    case '#' -> Obstacle.WALL;
                    case '@' -> {
                        robotY = y;
                        robotX = x;
                        yield Obstacle.ROBOT;
                    }
                    default -> Obstacle.EMPTY;
                };
            }
        }

        return new MapAndRobot(robotX, robotY, map);
    }

    private static MapAndRobot moveIfCan(Obstacle[][] map, int x, int y, char command, boolean robotMove) {
        int yReplacement = 0;
        int xReplacement = 0;

        switch (command) {
            case '^' -> yReplacement = -1;
            case 'v' -> yReplacement = 1;
            case '>' -> xReplacement = 1;
            case '<' -> xReplacement = -1;
        }

        if (map[y + yReplacement][x + xReplacement].equals(Obstacle.WALL)) {
            return null;
        }

        if (map[y + yReplacement][x + xReplacement].equals(Obstacle.PACKAGE)) {
            var moveResult = moveIfCan(map, x + xReplacement, y + yReplacement, command, false);
            if (moveResult == null) {
                return robotMove ? new MapAndRobot(x, y, map) : null;
            }
        }

        map[y + yReplacement][x + xReplacement] = map[y][x];
        map[y][x] = Obstacle.EMPTY;

        return new MapAndRobot(x + xReplacement, y + yReplacement, map);
    }

    private static void printMap(Obstacle[][] map) {
        for (Obstacle[] line: map) {
            System.out.println("");
            for (Obstacle o: line) {
                char c = switch (o) {
                    case WALL -> '#';
                    case PACKAGE -> 'O';
                    case EMPTY -> '.';
                    case ROBOT -> '@';
                    case P_START -> '[';
                    case P_END -> ']';
                };

                System.out.print(c);
            }
        }
    }

    private static long countPackageGps(Obstacle[][] map) {
        long sum = 0L;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x].equals(Obstacle.PACKAGE)) {
                    sum += (100L * y) + x;
                }
            }
        }

        return sum;
    }
}
