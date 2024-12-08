package com.opencast.day8;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Region {
    private final int xSize;
    private final int ySize;
    private final Set<Antenna> antennas;

    public Region(List<String> regionMap) {
        ySize = regionMap.size();
        xSize = regionMap.getFirst().length();

        antennas = new HashSet<>();

        for (int y = 0; y < ySize; y++) {
            var chars = regionMap.get(y).toCharArray();
            for (int x = 0; x < xSize; x++) {
              if (chars[x] != '.') {
                  antennas.add(new Antenna(chars[x], x, y));
              }
            }
        }
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public Set<Antenna> getAntennas() {
        return antennas;
    }
}
