package com.opencast.day16.part1;

public enum Direction {
    NORTH ("EAST", 0, -1),
    SOUTH ("WEST", 0 , 1),
    WEST ("NORTH", -1, 0),
    EAST ("SOUTH", 1, 0);

    private final String hasOnRight;
    private final int xVector;
    private final int yVector;

    Direction(String hasOnRight, int xVector, int yVector) {
        this.hasOnRight = hasOnRight;
        this.xVector = xVector;
        this.yVector = yVector;
    }

    public Direction getHasOnRight() {
        return Direction.valueOf(hasOnRight);
    }

    public int getxVector() {
        return xVector;
    }

    public int getyVector() {
        return yVector;
    }
}
