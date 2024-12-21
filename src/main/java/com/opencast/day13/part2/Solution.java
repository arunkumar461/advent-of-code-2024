package com.opencast.day13.part2;

import com.opencast.day13.Button;
import com.opencast.day13.Equation;
import com.opencast.day13.Pair;
import com.opencast.day13.Prize;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;

class Solution {
    private static final long PRIZE_INCREASE = 10000000000000L;
    private Solution() {
    }

    public static long getMinimumTokens(List<String> input) {
        var exuations = new HashSet<Equation>();
        Button aButton = null;
        Button bButton = null;
        Prize prize = null;

        for (int i = 0; i < input.size(); i++) {
            if (i % 4 == 0) {
                aButton = mapButton(input.get(i));
            }
            if (i % 4 == 1) {
                bButton = mapButton(input.get(i));
            }
            if (i % 4 == 2) {
                prize = mapPrize(input.get(i));
            }
            if (i % 4 == 3) {
                exuations.add(new Equation(aButton, bButton, prize));
            }
        }
        exuations.add(new Equation(aButton, bButton, prize));

        return exuations.stream()
                .map(Solution::getEquationSolution)
                .filter(Objects::nonNull)
                .mapToLong(p -> p.a() * 3 + p.b())
                .sum();
    }

    private static Button mapButton(String buttonDesc) {
        var buttonSplitted = buttonDesc.split(" ");

        int xMove = buttonSplitted[2].charAt(1) == '+' ? Integer.valueOf(buttonSplitted[2].substring(2, buttonSplitted[2].length() - 1)) : Integer.valueOf(buttonSplitted[2].substring(1, buttonSplitted[2].length() - 1));
        int yMove = buttonSplitted[3].charAt(1) == '+' ? Integer.valueOf(buttonSplitted[3].substring(2)) : Integer.valueOf(buttonSplitted[3].substring(1));

        int cost = buttonSplitted[1].charAt(0) == 'A' ? 3 : 1;

        return new Button(xMove, yMove, cost);
    }

    private static Prize mapPrize(String prizeDesc) {
        var prizeSplitted = prizeDesc.split(" ");
        long xPos = Long.parseLong(prizeSplitted[1].substring(2, prizeSplitted[1].length() - 1)) + PRIZE_INCREASE;
        long yPos = Long.parseLong(prizeSplitted[2].substring(2)) + PRIZE_INCREASE;

        return new Prize(xPos, yPos);
    }

    private static Pair getEquationSolution(Equation equation) {
        Button buttonA = equation.a();
        Button buttonB = equation.b();
        Prize prize = equation.p();

        long[] first = new long[3];
        first[0] = (long) buttonA.xMove() * buttonA.yMove();
        first[1] = (long) buttonB.xMove() * buttonA.yMove();
        first[2] = prize.xPos() * buttonA.yMove();

        long[] second = new long[3];
        second[0] = (long) buttonA.yMove() * buttonA.xMove();
        second[1] = (long) buttonB.yMove() * buttonA.xMove();
        second[2] = prize.yPos() * buttonA.xMove();

        if (abs((second[2] - first[2])) % abs((second[1] - first[1])) != 0 || abs((second[2] - first[2])) / abs((second[1] - first[1])) < 0) {
            return null;
        }

        long bPress = abs((second[2] - first[2])) / abs((second[1] - first[1]));

        if ((prize.xPos() - (bPress * buttonB.xMove())) % buttonA.xMove() != 0 || (prize.xPos() - (bPress * buttonB.xMove())) / buttonA.xMove() < 0) {
            return null;
        }
        long aPress = (prize.xPos() - (bPress * buttonB.xMove())) / buttonA.xMove();

        return new Pair(aPress, bPress);
    }
}
