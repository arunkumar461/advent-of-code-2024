package com.opencast.day14.part1;

import com.opencast.day14.Robot;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    private Solution() {
    }

    public static long getSecurityFactor(List<String> input, int secondsNumber, int areaWidth, int areaHeight) {
        int middleWidth = areaWidth / 2;
        int middleHeight = areaHeight / 2;
        var robotEndPositions = input.stream()
                .map(Solution::mapRobot)
                .map(r -> r.calculatePosition(areaWidth, areaHeight, secondsNumber))
                .filter(p -> p.x() != middleWidth && p.y() != middleHeight)
                .toList();

        var robotsPartitionedByHeight = robotEndPositions.stream()
                .collect(Collectors.partitioningBy(p -> p.y() > middleHeight));

        var topQuartersRobots = robotsPartitionedByHeight.get(false).stream()
                .collect(Collectors.partitioningBy(p -> p.x() > middleWidth));
        var bottomQuartersRobots = robotsPartitionedByHeight.get(true).stream()
                .collect(Collectors.partitioningBy(p -> p.x() > middleWidth));

        return Stream.of(
                        topQuartersRobots.values(),
                        bottomQuartersRobots.values()
                )
                .flatMap(Collection::stream)
                .mapToInt(Collection::size)
                .reduce(1, (a, b) -> a * b);
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
}
