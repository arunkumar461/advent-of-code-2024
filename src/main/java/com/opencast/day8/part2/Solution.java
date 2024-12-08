package com.opencast.day8.part2;


import com.opencast.day8.Antenna;
import com.opencast.day8.AntennaPair;
import com.opencast.day8.Antinode;
import com.opencast.day8.Region;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        var antinodes = new ArrayList<Antinode>();
        var firstAntinode = new Antinode(pair.firsAntenna().xPos(), pair.firsAntenna().yPos());
        antinodes.add(firstAntinode);
        int xShift = getShift(pair.firsAntenna().xPos(), pair.secondAntenna().xPos());
        int yShift = getShift(pair.firsAntenna().yPos(), pair.secondAntenna().yPos());

        int i = 1;
        while (isInBounds(firstAntinode.xPos() + i * xShift, firstAntinode.yPos() + i * yShift, xSize, ySize)) {
            antinodes.add(new Antinode(firstAntinode.xPos() + i * xShift, firstAntinode.yPos() + i * yShift));
            i++;
        }

        i = -1;
        while (isInBounds(firstAntinode.xPos() + i * xShift, firstAntinode.yPos() + i * yShift, xSize, ySize)) {
            antinodes.add(new Antinode(firstAntinode.xPos() + i * xShift, firstAntinode.yPos() + i * yShift));
            i--;
        }

        return antinodes;
    }

    private static int getShift(int antennaPosition, int relatedAntennaPosition) {
        if (antennaPosition == relatedAntennaPosition) {
            return antennaPosition;
        }

        int relativePosition = abs(antennaPosition - relatedAntennaPosition);
        if (antennaPosition > relatedAntennaPosition) {
            return relativePosition;
        }

        return relativePosition * (-1);
    }

    private static boolean isInBounds(int xPosition, int yPosition,  int xSize, int ySize) {
        if (xPosition < 0 || yPosition < 0) {
            return false;
        }

        return xPosition < xSize && yPosition < ySize;
    }
}
