package com.opencast.day4.part1;

public enum StandardDirection {
    VERTICAL(1, 0, 0, 0, 3, 0),
    VERTICAL_REVERSED(-1, 0, 3, 0, 0, 0),
    HORIZONTAL(0, 1, 0, 0, 0, 3),
    HORIZONTAL_REVERSED(0, -1, 0, 3, 0, 0),
    LEFT_DIAGONAL(-1, 1, 3, 0, 0, 3),
    LEFT_DIAGONAL_REVERSED(1, -1, 0, 3, 3, 0),
    RIGHT_DIAGONAL(1, 1, 0, 0, 3, 3),
    RIGHT_DIAGONAL_REVERSED(-1, -1, 3, 3, 0, 0);

    public final int nextX;
    public final int nextY;
    public final int fromStartX;
    public final int fromStartY;
    public final int fromEndX;
    public final int fromEndY;

    StandardDirection(int nextX, int nextY, int fromStartX, int fromStartY, int fromEndX, int fromEndY) {
        this.nextX = nextX;
        this.nextY = nextY;
        this.fromStartX = fromStartX;
        this.fromStartY = fromStartY;
        this.fromEndX = fromEndX;
        this.fromEndY = fromEndY;
    }
}
