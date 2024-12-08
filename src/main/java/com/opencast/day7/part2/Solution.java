package com.opencast.day7.part2;


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
            return switch (operationType) {
                case SUM -> actualResult + equation.terms().get(termPosition) == equation.result();
                case MULTIPLY -> actualResult * equation.terms().get(termPosition) == equation.result();
                case CONCATENATION -> concatenateNumbers(actualResult, equation.terms().get(termPosition)) == equation.result();
            };
        }

        actualResult = switch (operationType) {
            case SUM -> actualResult + equation.terms().get(termPosition);
            case MULTIPLY -> actualResult * equation.terms().get(termPosition);
            case CONCATENATION -> concatenateNumbers(actualResult, equation.terms().get(termPosition));
        };

        return isEquationCorrect(equation, termPosition + 1, actualResult, OperationTypes.SUM)
                || isEquationCorrect(equation, termPosition + 1, actualResult, OperationTypes.MULTIPLY)
                || isEquationCorrect(equation, termPosition + 1, actualResult, OperationTypes.CONCATENATION);
    }

    private static long concatenateNumbers(Long num1, Long num2) {
        String concat = num1.toString() + num2.toString();
        return Long.parseLong(concat);
    }
}
