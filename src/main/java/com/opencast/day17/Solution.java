package com.opencast.day17;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    private static List<BigInteger> program;

    public static void main(String[] args) throws IOException {

        BigInteger A = BigInteger.valueOf(64584136);
        BigInteger B = BigInteger.valueOf(0);
        BigInteger C = BigInteger.valueOf(0);
        program = Stream.of(2, 4, 1, 2, 7, 5, 1, 3, 4, 3, 5, 5, 0, 3, 3, 0).map(BigInteger::valueOf).toList();

        System.out.println("solution #1: " + runProgram(A, B, C));

        System.out.println("\n-----------------------------------------------------------\n");

        matchProgramOutputRecursive(BigInteger.ZERO, 0, A, B, C);
    }

    private static String runProgram(BigInteger a, BigInteger b, BigInteger c) {
        BigInteger A = a;
        BigInteger B = b;
        BigInteger C = c;
        int instr = 0;
        List<BigInteger> output = new ArrayList<>();

        while (instr < program.size()) {
            BigInteger cmd = program.get(instr++);
            BigInteger op = program.get(instr++);
            switch (cmd.intValue()) {
                case 0: // adv
                    A = A.shiftRight(op.intValue());
                    break;
                case 1: // bxl
                    B = B.xor(op);
                    break;
                case 2: // bst
                    B = op.and(BigInteger.valueOf(7));
                    break;
                case 3: // jnz
                    if (!A.equals(BigInteger.ZERO)) {
                        instr = op.intValue();
                    }
                    break;
                case 4: // bxc
                    B = B.xor(C);
                    break;
                case 5: // out
                    output.add(op.and(BigInteger.valueOf(7)));
                    break;
                case 6:
                    B = A.shiftRight(op.intValue());
                    break;
                case 7:
                    C = A.shiftRight(op.intValue());
                    break;
            }
        }
        return output.stream().map(BigInteger::toString).collect(Collectors.joining(","));
    }

    private static boolean matchProgramOutputRecursive(BigInteger A, int i, BigInteger initialA, BigInteger B, BigInteger C) {
        if (i == program.size()) {
            if (runProgram(A, B, C).equals(runProgram(initialA, B, C))) {
                System.out.println("solution #2: " + A);
                return true;
            } else {
                return false;
            }
        }
        A = A.multiply(BigInteger.valueOf(8));
        for (int byteValue = 0; byteValue < 8; byteValue++) {
            BigInteger nextA = A.add(BigInteger.valueOf(byteValue));
            if (runProgram(nextA, B, C).endsWith(runProgram(initialA, B, C)) &&
                    matchProgramOutputRecursive(nextA, i + 1, initialA, B, C)) {
                return true;
            }
        }
        return false;
    }
}
