package com.opencast.day8.part1;


import com.opencast.day8.Antenna;
import com.opencast.day8.AntennaPair;
import com.opencast.day8.Antinode;
import com.opencast.day8.Region;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class Solution {
    public static long getNumberOfAntinodes(List<String> input) {
        var region = new Region(input);

        return region.getAntennas().stream()
                .collect(Collectors.groupingBy(Antenna::frequency))
                .values().stream()
                .flatMap(group -> getAntinodes(group, region.getxSize(), region.getySize()).stream())
                .distinct()
                .count();
    }

    private static List<Antinode> getAntinodes(List<Antenna> antennas, int xSize, int ySize) {
        var antennaPairs = antennas.stream()
                .flatMap(antenna -> antennas.stream()
                        .filter(a -> !a.equals(antenna))
                        .map(a -> new AntennaPair(antenna, a, abs(a.xPos() - antenna.xPos()), abs(a.yPos() - antenna.yPos())))
                )
                .distinct()
                .toList();

        return antennaPairs.stream()
                .flatMap(ap -> getAntinodesForPair(ap, xSize, ySize).stream())
                .toList();
    }

    private static List<Antinode> getAntinodesForPair(AntennaPair pair, int xSize, int ySize) {
        var firstAntinode = new Antinode(
                pair.firsAntenna().xPos() + getPosition(pair.firsAntenna().xPos(), pair.secondAntenna().xPos()),
                pair.firsAntenna().yPos() + getPosition(pair.firsAntenna().yPos(), pair.secondAntenna().yPos()));

        var secondAntinode =new Antinode(
                pair.secondAntenna().xPos() + getPosition(pair.secondAntenna().xPos(), pair.firsAntenna().xPos()),
                pair.secondAntenna().yPos() + getPosition(pair.secondAntenna().yPos(), pair.firsAntenna().yPos()));

        return Stream.of(firstAntinode, secondAntinode)
                .filter(a -> isInBounds(a, xSize, ySize))
                .toList();
    }

    private static int getPosition(int antennaPosition, int relatedAntennaPosition) {
        if (antennaPosition == relatedAntennaPosition) {
            return antennaPosition;
        }

        int relativePosition = abs(antennaPosition - relatedAntennaPosition);
        if (antennaPosition > relatedAntennaPosition) {
            return relativePosition;
        }

        return relativePosition * (-1);
    }

    private static boolean isInBounds(Antinode antinode, int xSize, int ySize) {
        if (antinode.xPos() < 0 || antinode.yPos() < 0) {
            return false;
        }

        return antinode.xPos() < xSize && antinode.yPos() < ySize;
    }
}
