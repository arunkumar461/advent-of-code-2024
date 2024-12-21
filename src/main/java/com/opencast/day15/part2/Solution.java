package com.opencast.day15.part2;

import com.opencast.day15.MapAndRobot;
import com.opencast.day15.Obstacle;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static long countFinalPackageGPS(List<String> mapDescription, List<String> moves) {
        var mapAndRobot = resolveMapAndRobotPosition(mapDescription);
        Obstacle[][] map = mapAndRobot.map();
        int robotX = mapAndRobot.robotX();
        int robotY = mapAndRobot.robotY();

        printMap(map);

        var connectedMoves = String.join("", moves);

        for (int i = 0; i < connectedMoves.length(); i++) {
            var result = moveIfCan(map, robotX, robotY, connectedMoves.charAt(i), Obstacle.ROBOT);
            robotX = result != null ? result.robotX() : robotX;
            robotY = result != null ? result.robotY() : robotY;
        }

        printMap(map);
        return countPackageGps(map);
    }

    private static MapAndRobot resolveMapAndRobotPosition(List<String> mapLines) {
        Obstacle[][] map = new Obstacle[mapLines.size()][mapLines.getFirst().length() * 2];
        int robotX = -1;
        int robotY = -1;

        for (int y = 0; y < mapLines.size(); y++) {
            var lineChars = mapLines.get(y).toCharArray();
            for (int x = 0; x < lineChars.length; x++) {
                switch (lineChars[x]) {
                    case 'O' -> {
                        map[y][2 * x] = Obstacle.P_START;
                        map[y][(2 * x) + 1] = Obstacle.P_END;
                    }
                    case '#' -> {
                        map[y][2 * x] = Obstacle.WALL;
                        map[y][(2 * x) + 1] = Obstacle.WALL;
                    }
                    case '@' -> {
                        robotY = y;
                        robotX = 2 * x;
                        map[y][2 * x] = Obstacle.ROBOT;
                        map[y][(2 * x) + 1] = Obstacle.EMPTY;
                    }
                    default -> {
                        map[y][2 * x] = Obstacle.EMPTY;
                        map[y][(2 * x) + 1] = Obstacle.EMPTY;
                    }
                }
            }
        }

        return new MapAndRobot(robotX, robotY, map);
    }

    private static MapAndRobot moveIfCan(Obstacle[][] map, int x, int y, char command, Obstacle type) {
        int yReplacement = 0;
        int xReplacement = 0;

        switch (command) {
            case '^' -> yReplacement = -1;
            case 'v' -> yReplacement = 1;
            case '>' -> xReplacement = 1;
            case '<' -> xReplacement = -1;
        }


        if (map[y + yReplacement][x + xReplacement].equals(Obstacle.WALL)) {
            return new MapAndRobot(x, y, map);
        }


        if (map[y + yReplacement][x + xReplacement].equals(Obstacle.EMPTY)) {
            map[y + yReplacement][x + xReplacement] = type;
            map[y][x] = Obstacle.EMPTY;
            return new MapAndRobot(x + xReplacement, y + yReplacement, map);
        }

        var packageGroup = detectPackageGroup(x, y, xReplacement, yReplacement, map);
        if (!isGroupBlocked(packageGroup, xReplacement, yReplacement, map)) {
            moveGroup(packageGroup, xReplacement, yReplacement, map);
            map[y + yReplacement][x + xReplacement] = type;
            map[y][x] = Obstacle.EMPTY;
            return new MapAndRobot(x + xReplacement, y + yReplacement, map);
        }

        return null;
    }

    private static PackageGroup detectPackageGroup(int robotX, int robotY, int xReplacement, int yReplacement, Obstacle[][] map) {
        if (yReplacement != 0) {
            return detectVerticalGroup(robotX, robotY + yReplacement, yReplacement, map);
        }

        int xStart = robotX + xReplacement;
        int xEnd = xStart;
        int yStart = robotY + yReplacement;
        int yEnd = yStart;
        var positions = new HashSet<Position>();

        int iteration = 0;
        while (map[yStart][xStart+ (iteration * xReplacement)].equals(Obstacle.P_START) || map[yStart][xStart + (iteration * xReplacement)].equals(Obstacle.P_END)) {
            iteration++;
            xEnd += xReplacement;
            positions.add(new Position(robotX + (iteration * xReplacement), robotY, map[robotY][robotX + (iteration * xReplacement)]));
        }

        return new PackageGroup(positions);
    }

    private static PackageGroup detectVerticalGroup(int actualX, int actualY, int yReplacement, Obstacle[][] map) {
        var type = map[actualY][actualX];
        int pairX = type.equals(Obstacle.P_START) ? actualX + 1 : actualX - 1;
        if (
               type.equals(Obstacle.EMPTY) || type.equals(Obstacle.WALL)
        ) {
            return new PackageGroup(Collections.emptySet());
        }

        var group = detectVerticalGroup(actualX, actualY + yReplacement, yReplacement, map);
        var pairGroup = detectVerticalGroup(pairX, actualY + yReplacement, yReplacement, map);

        var thisElement = new Position(actualX, actualY, map[actualY][actualX]);
        var pairElement = thisElement.type().equals(Obstacle.P_START) ? new Position(actualX + 1, actualY, Obstacle.P_END) : new Position(actualX - 1, actualY, Obstacle.P_START);
        var positions = new HashSet<Position>();
        positions.addAll(group.positions());
        positions.addAll(pairGroup.positions());
        positions.add(thisElement);
        positions.add(pairElement);

        var filtered = positions.stream()
                .filter(p -> p.type().equals(Obstacle.P_START) || p.type().equals(Obstacle.P_END))
                .collect(Collectors.toSet());

        return new PackageGroup(filtered);
    }

    private static boolean isGroupBlocked(PackageGroup group, int xReplacement, int yReplacement, Obstacle[][] map) {
        for (Position p: group.positions()) {
            if (map[p.y() + yReplacement][p.x() + xReplacement].equals(Obstacle.WALL)) {
                return true;
            }
        }

        return false;
    }

    private static Obstacle[][] moveGroup(PackageGroup group, int xReplacement, int yReplacement, Obstacle[][] map) {
        for (Position p: group.positions()) {
            map[p.y()][p.x()] = Obstacle.EMPTY;
        }

        for (Position p: group.positions()) {
            map[p.y() + yReplacement][p.x() + xReplacement] = p.type();
        }

        return map;
    }

    private static void printMap(Obstacle[][] map) {
        for (Obstacle[] line : map) {
            System.out.println();
            for (Obstacle o : line) {
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
                if (map[y][x].equals(Obstacle.P_START)) {
                    sum += (100L * y) + x;
                }
            }
        }

        return sum;
    }
}
