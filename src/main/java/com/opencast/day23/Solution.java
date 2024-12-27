package com.opencast.day23;

import com.opencast.InputReader;

import java.io.IOException;
import java.util.*;

public class Solution {
    private static final Map<String, Set<String>> neighbors = new HashMap<>();

    public static void main(String[] args) throws IOException {

        List<String[]> input = readLines("day23/input.txt", "-");

        for (String[] pair : input) {
            String from = pair[0];
            String to = pair[1];
            neighbors.computeIfAbsent(from, k -> new HashSet<>()).add(to);
            neighbors.computeIfAbsent(to, k -> new HashSet<>()).add(from);
        }

        System.out.println("solution #1: " + findTrianglesWithT().size());

        System.out.println("\n-----------------------------------------------------------\n");


        Set<String> largestClique = findMaximalCliques();
        List<String> sortedClique = new ArrayList<>(largestClique);
        Collections.sort(sortedClique);
        System.out.println("solution #2: " + String.join(",", sortedClique));
    }


    public static Set<List<String>> findTrianglesWithT() {
        Set<List<String>> triangles = new HashSet<>();
        for (String a : neighbors.keySet()) {
            if (a.charAt(0) == 't') {
                for (String b : neighbors.get(a)) {
                    for (String c : neighbors.get(b)) {
                        if (neighbors.get(c).contains(a)) {
                            List<String> triangle = new ArrayList<>(Arrays.asList(a, b, c));
                            Collections.sort(triangle);
                            triangles.add(triangle);
                        }
                    }
                }
            }
        }
        return triangles;
    }

    public static Set<String> bronKerbosch(Set<String> R, Set<String> P, Set<String> X) {
        Set<String> result = new HashSet<>();
        if (P.isEmpty() && X.isEmpty()) {
            return R;
        }

        for (String v : new HashSet<>(P)) {
            Set<String> neighborsV = neighbors.get(v);
            Set<String> newP = new HashSet<>();
            Set<String> newX = new HashSet<>();
            for (String n : P) {
                if (neighborsV.contains(n)) {
                    newP.add(n);
                }
            }
            for (String n : X) {
                if (neighborsV.contains(n)) {
                    newX.add(n);
                }
            }
            Set<String> newR = new HashSet<>(R);
            newR.add(v);
            Set<String> clique = bronKerbosch(newR, newP, newX);
            if (!clique.isEmpty()) {
                result.addAll(clique);
            }
            P.remove(v);
            X.add(v);
        }
        return result;
    }

    public static Set<String> findMaximalCliques() {
        return bronKerbosch(new HashSet<>(), new HashSet<>(neighbors.keySet()), new HashSet<>());
    }

    public static List<String[]> readLines(String filePath, String delimiter) {
        return InputReader.readInput(filePath).stream().map(s -> s.split(delimiter)).toList();
    }
}
