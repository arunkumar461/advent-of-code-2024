package com.opencast.day14;

public record Robot(int initX, int initY, int velocityX, int velocityY) {

    public Position calculatePosition(int areaXSize, int areaYSize, int secondsNumber) {
        int xReplacement = ((velocityX * secondsNumber) % areaXSize);
        int yReplacement = ((velocityY * secondsNumber) % areaYSize);

        int xPos = getNormalisedPosition(0, areaXSize, initX + xReplacement);
        int yPos = getNormalisedPosition(0, areaYSize, initY + yReplacement);

        return new Position(xPos, yPos);
    }

    private int getNormalisedPosition(int min, int max, int value) {
        if (value > max - 1) {
            return min + (value - max);
        }

        if (value < min) {
            return max - (min - value);
        }

        return value;
    }
}
