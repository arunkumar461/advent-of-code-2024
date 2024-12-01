package com.opencast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Problem1Test {

    @Test
    void totalDistanceBetweenArrayNumbers1() {
        Problem1 problem1 = new Problem1();
        int i = problem1.sumOfDistances(new int[]{3, 4, 2, 1, 3, 3}, new int[]{4, 3, 5, 3, 9, 3});
        Assertions.assertEquals(11, i);

    }

    @Test
    void totalDistanceBetweenArrayNumbers2() {
        Problem1 problem1 = new Problem1();
        List<int[]> numbers = inputs();
        int sum = problem1.sumOfDistances(numbers.getFirst(), numbers.getLast());
        Assertions.assertEquals(1941353, sum);

    }

    @Test
    void similarityScoreForArrays() {
        Problem1 problem1 = new Problem1();
        int i = problem1.similarityScore(new int[]{3, 4, 2, 1, 3, 3}, new int[]{4, 3, 5, 3, 9, 3});
        Assertions.assertEquals(31, i);
    }

    @Test
    void similarityScoreForArrays2() {
        Problem1 problem1 = new Problem1();
        List<int[]> numbers = inputs();
        int i = problem1.similarityScore(numbers.getFirst(), numbers.getLast());
        Assertions.assertEquals(22539317, i);

    }

    public List<int[]> inputs() {
        ArrayList<Integer> integers1 = new ArrayList<>();
        ArrayList<Integer> integers2 = new ArrayList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("input1.txt");

        if (inputStream == null) {
            throw new RuntimeException("File not found!");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");
                if (numbers.length == 2) {
                    int num1 = Integer.parseInt(numbers[0]);
                    int num2 = Integer.parseInt(numbers[1]);
                    integers1.add(num1);
                    integers2.add(num2);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] array1 = integers1.stream().mapToInt(Integer::intValue).toArray();

        int[] array2 = integers2.stream().mapToInt(Integer::intValue).toArray();

        return List.of(array1, array2);
    }
}
