package com.opencast.day6.part2;


import com.opencast.day6.Field;
import com.opencast.day6.Guard;
import com.opencast.day6.Laboratory;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;

class Solution {
    private Solution() {
    }

    public static long countPossibleLoopPositions(List<String> inputs) {
        var laboratory = new Laboratory(inputs);
        long possiblePositionCount = 0L;
        int guardStartX = laboratory.getGuard().getPosX();
        int guardStartY = laboratory.getGuard().getPosY();

        for (int y = 0; y < laboratory.getColumnSize(); y++) {
            for (int x = 0; x < laboratory.getRowSize(); x++) {
                if (laboratory.isFieldObstacle(x, y)) {
                    continue;
                }

                if (isLooped(x, y, laboratory)) {
                    possiblePositionCount++;
                }

                laboratory.getGuard().setPosX(guardStartX);
                laboratory.getGuard().setPosY(guardStartY);
                laboratory.getGuard().setDirection(Guard.FacingDirection.NORTH);
            }

        }

        return possiblePositionCount;
    }

    private static boolean isLooped(int xPos, int yPos, Laboratory laboratory) {
        laboratory.setField(xPos, yPos, Field.Type.OBSTACLE);
        laboratory.addObstacle(xPos, yPos);
        var obsctacles = new HashMap<Field, Integer>();

        Field nextObstacle = getNextObstacle(laboratory.getGuard(), laboratory.getObstacles());
        obsctacles.put(nextObstacle, 1);
        while (nextObstacle != null) {
            visitAllFieldsToObstacle(laboratory, nextObstacle);
            laboratory.turnGuardRight();
            nextObstacle = getNextObstacle(laboratory.getGuard(), laboratory.getObstacles());

            if (obsctacles.containsKey(nextObstacle) ) {
                obsctacles.put(nextObstacle, obsctacles.get(nextObstacle) + 1);
            } else {
                obsctacles.put(nextObstacle, 1);
            }

            if (obsctacles.values().stream().anyMatch(i -> i > 3)) {
                laboratory.removeObstacle(xPos, yPos);
                laboratory.setField(xPos, yPos, Field.Type.EMPTY);
                return true;
            }
        }

        laboratory.removeObstacle(xPos, yPos);
        laboratory.setField(xPos, yPos, Field.Type.EMPTY);
        return false;
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
            actualX += guard.getDirection().xTensor;
            actualY += guard.getDirection().yTensor;
        }

        guard.setPosX(actualX - guard.getDirection().xTensor);
        guard.setPosY(actualY - guard.getDirection().yTensor);
    }
}
