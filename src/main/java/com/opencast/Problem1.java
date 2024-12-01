package com.opencast;

import java.util.Arrays;
import java.util.HashMap;

public class Problem1 {

    public int sumOfDistances(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int sum = 0;

        for (int i = 0; i < a.length; i++) {
            int distance = a[i] - b[i];
            sum = sum + Math.abs(distance);
        }

        return sum;
    }

    public int similarityScore(int[] a, int[] b) {

        int similarityScore = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j : b) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        for (int j : a) {
            similarityScore = similarityScore + j * map.getOrDefault(j, 0);
        }

        return similarityScore;
    }

}
