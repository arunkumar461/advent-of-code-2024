package com.opencast.day6;

public class Guard {
    private int posX;
    private int posY;
    private FacingDirection direction;

    public Guard(int posX, int posY, FacingDirection facingDirection) {
        this.posX = posX;
        this.posY = posY;
        this.direction = facingDirection;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public FacingDirection getDirection() {
        return direction;
    }

    public void setDirection(FacingDirection direction) {
        this.direction = direction;
    }

    public void turnRight() {
        direction = switch (direction) {
            case NORTH -> FacingDirection.EAST;
            case EAST -> FacingDirection.SOUTH;
            case SOUTH -> FacingDirection.WEST;
            case WEST -> FacingDirection.NORTH;
        };
    }

    public enum FacingDirection {
        NORTH(0, -1),
        EAST(1, 0),
        SOUTH(0, 1),
        WEST(-1, 0);

        public final int xTensor;
        public final int yTensor;

        FacingDirection(int xTensor, int yTensor) {
            this.xTensor = xTensor;
            this.yTensor = yTensor;
        }

    }
}
