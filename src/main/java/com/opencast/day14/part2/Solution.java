package com.opencast.day14.part2;

import com.opencast.day14.Position;
import com.opencast.day14.Robot;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private Solution() {
    }

    public static int getTreeSecond(List<String> input, int areaWidth, int areaHeight) {
        char[][] area = new char[areaHeight][areaWidth];
        clearTable(area);
        int second = 0;
        while(!isATree(area)) {
            clearTable(area);
            var positions = getRobotPositions(input, second, areaWidth, areaHeight);
            addPositionsToTable(area, positions);
            second++;
        }

        return second - 1;
    }

    private static List<Position> getRobotPositions(List<String> input, int secondsNumber, int areaWidth, int areaHeight) {
        return input.stream()
                .map(Solution::mapRobot)
                .map(r -> r.calculatePosition(areaWidth, areaHeight, secondsNumber))
                .toList();
    }

    private static Robot mapRobot(String robotDescription) {
        var elements = robotDescription.split(" ");
        var position = elements[0].substring(2).split(",");
        var velocity = elements[1].substring(2).split(",");

        return new Robot(
                Integer.parseInt(position[0]),
                Integer.parseInt(position[1]),
                Integer.parseInt(velocity[0]),
                Integer.parseInt(velocity[1])
        );
    }

    private static void clearTable(char[][] table) {
        for (char[] chars : table) {
            Arrays.fill(chars, ' ');
        }
    }

    private static void addPositionsToTable(char[][] table, List<Position> positions) {
        for (Position p: positions) {
            table[p.y()][p.x()] = '#';
        }
    }

    private static boolean isATree(char[][] table) {
        int[] robots = new int[101];
        int lines = 0;
        Arrays.fill(robots, 0);
        for (char[] line: table) {
            for (int i = 0; i < 101; i++) {
                if (line[i] == '#')
                    robots[i] = robots[i] + 1;
            }
        }

        for (int i: robots) {
            if (i > 32) {
                lines ++;
            }
        }

        return lines == 2;
    }
}
