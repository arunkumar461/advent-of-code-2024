package com.opencast.day7.part1;


import com.opencast.day7.Equation;
import com.opencast.day7.OperationTypes;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static long getSumOfCalibrationResults(List<String> input) {
        var equations = input.stream()
                .map(line -> line.split(":"))
                .map(arr -> new Equation(
                        Long.valueOf(arr[0]),
                        mapTerms(arr[1])
                        ))
                .toList();

        return equations.stream()
                .filter(e -> isEquationCorrect(e, 0, 0, OperationTypes.SUM))
                .mapToLong(Equation::result)
                .sum();
    }

    private static List<Long> mapTerms(String terms) {
        var termArr = terms.trim().split(" ");
        var result = new ArrayList<Long>();

        for (String term: termArr) {
            result.add(Long.valueOf(term));
        }

        return result;
    }

    private static boolean isEquationCorrect(Equation equation, int termPosition, long actualResult, OperationTypes operationType) {
        if (termPosition >= equation.terms().size() - 1) {
            return operationType.equals(OperationTypes.SUM) ?
                    actualResult + equation.terms().get(termPosition) == equation.result() :
                    actualResult * equation.terms().get(termPosition) == equation.result();
        }

        actualResult = operationType.equals(OperationTypes.SUM) ?
                actualResult + equation.terms().get(termPosition) :
                actualResult * equation.terms().get(termPosition);

        return isEquationCorrect(equation, termPosition + 1, actualResult, OperationTypes.SUM)
                || isEquationCorrect(equation, termPosition + 1, actualResult, OperationTypes.MULTIPLY);
    }
}
