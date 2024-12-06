package com.opencast.day6.part1;


import com.opencast.day6.Field;
import com.opencast.day6.Guard;
import com.opencast.day6.Laboratory;

import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;

class Solution {
    private Solution() {
    }

    public static long predictNumberOfVisitedPositions(List<String> inputs) {
        var laboratory = new Laboratory(inputs);

        Field nextObstacle = getNextObstacle(laboratory.getGuard(), laboratory.getObstacles());
        while (nextObstacle != null) {
            visitAllFieldsToObstacle(laboratory, nextObstacle);
            laboratory.turnGuardRight();
            nextObstacle = getNextObstacle(laboratory.getGuard(), laboratory.getObstacles());
        }

        switch (laboratory.getGuard().getDirection()) {
            case NORTH ->
                    visitAllFieldsToObstacle(laboratory, new Field(laboratory.getGuard().getPosX(), -1, Field.Type.OBSTACLE));
            case EAST ->
                    visitAllFieldsToObstacle(laboratory, new Field(laboratory.getRowSize(), laboratory.getGuard().getPosY(), Field.Type.OBSTACLE));
            case SOUTH ->
                    visitAllFieldsToObstacle(laboratory, new Field(laboratory.getGuard().getPosX(), laboratory.getColumnSize(), Field.Type.OBSTACLE));
            case WEST ->
                    visitAllFieldsToObstacle(laboratory, new Field(-1, laboratory.getGuard().getPosY(), Field.Type.OBSTACLE));
        }

        return laboratory.getNumberOfOccupiedFields();
    }

    private static Field getNextObstacle(Guard guard, Set<Field> obstacles) {
        return obstacles.stream()
                .filter(obstacle -> isObstacleInLineOfView(obstacle, guard))
                .min((o1, o2) -> {
                    if (guard.getDirection().equals(Guard.FacingDirection.NORTH) || guard.getDirection().equals(Guard.FacingDirection.SOUTH)) {
                        return Integer.compare(abs(guard.getPosY() - o1.yPos()), abs(guard.getPosY() - o2.yPos()));
                    }

                    return Integer.compare(abs(guard.getPosX() - o1.xPos()), abs(guard.getPosX() - o2.xPos()));
                })
                .orElse(null);
    }

    private static boolean isObstacleInLineOfView(Field field, Guard guard) {
        return switch (guard.getDirection()) {
            case NORTH -> field.xPos() == guard.getPosX() && field.yPos() < guard.getPosY();
            case EAST -> field.yPos() == guard.getPosY() && field.xPos() > guard.getPosX();
            case SOUTH -> field.xPos() == guard.getPosX() && field.yPos() > guard.getPosY();
            case WEST -> field.yPos() == guard.getPosY() && field.xPos() < guard.getPosX();
        };
    }

    private static void visitAllFieldsToObstacle(Laboratory lab, Field nextObstacle) {
        var guard = lab.getGuard();
        int actualX = guard.getPosX() + guard.getDirection().xTensor;
        int actualY = guard.getPosY() + guard.getDirection().yTensor;

        while (actualX != nextObstacle.xPos() || actualY != nextObstacle.yPos()) {
            lab.setField(actualX, actualY, Field.Type.OCCUPIED);

            actualX += guard.getDirection().xTensor;
            actualY += guard.getDirection().yTensor;
        }

        guard.setPosX(actualX - guard.getDirection().xTensor);
        guard.setPosY(actualY - guard.getDirection().yTensor);
    }
}
