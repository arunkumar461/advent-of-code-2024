package com.opencast.day17;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day17 {

    private static List<Long> program;
    private static final List<Long> output = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        long A = 64584136;
        long B = 0;
        long C = 0;
        program = Stream.of(2, 4, 1, 2, 7, 5, 1, 3, 4, 3, 5, 5, 0, 3, 3, 0).map(Integer::longValue).toList();

        // Part #1
        System.out.println("Solution #1: " + runProgram(A, B, C));

        System.out.println("\n-----------------------------------------------------------\n");

        // Part #2
        String expectedOutput = program.stream().map(String::valueOf).collect(Collectors.joining(","));
        matchProgramOutputRecursive(BigInteger.ZERO, 0, expectedOutput, A, B, C);
    }

    private static String runProgram(long a, long b, long c) {
        BigInteger A = BigInteger.valueOf(a);
        BigInteger B = BigInteger.valueOf(b);
        BigInteger C = BigInteger.valueOf(c);

        int instr = 0;
        output.clear();

        while (instr < program.size()) {
            int cmd = program.get(instr++).intValue();
            int op = program.get(instr++).intValue();

            BigInteger combo = op < 4 ? BigInteger.valueOf(op) : List.of(A, B, C).get(op - 4);

            switch (cmd) {
                case 0 -> A = A.shiftRight(combo.intValue());
                case 1 -> B = B.xor(BigInteger.valueOf(op));
                case 2 -> B = combo.and(BigInteger.valueOf(7));
                case 3 -> {
                    if (!A.equals(BigInteger.ZERO)) instr = op;
                }
                case 4 -> B = B.xor(C);
                case 5 -> output.add(combo.and(BigInteger.valueOf(7)).longValue());
                case 6 -> B = A.shiftRight(combo.intValue());
                case 7 -> C = A.shiftRight(combo.intValue());
            }
        }

        return output.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    private static boolean matchProgramOutputRecursive(BigInteger A, int i, String expectedOutput, long a, long b, long c) {
        if (i == program.size()) {
            System.out.println("Run Program Output1: " + expectedOutput);
            System.out.println("Run Program Output2: " + expectedOutput.equals(runProgram(a, b, c)));
            if (expectedOutput.equals(runProgram(a, b, c))) {
                System.out.println("Solution #2: " + A);
                return true;
            } else {
                return false;
            }
        }

        A = A.multiply(BigInteger.valueOf(8));

        for (int byteValue = 0; byteValue < 8; byteValue++) {
            BigInteger newA = A.add(BigInteger.valueOf(byteValue));
            if (expectedOutput.endsWith(runProgram(newA.longValue(), b, c)) &&
                    matchProgramOutputRecursive(newA, i + 1, expectedOutput, a, b, c)) {
                return true;
            }
        }
        return false;
    }
}
