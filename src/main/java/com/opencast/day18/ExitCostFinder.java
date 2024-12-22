package com.opencast.day18;

import com.opencast.InputReader;

import java.io.IOException;
import java.util.*;

public class ExitCostFinder {

    static final int WIDTH = 71;
    static final int HEIGHT = 71;

    public static void main(String[] args) throws IOException {
        List<String> lines = InputReader.readInput("day18/input.txt");
        List<int[]> input = parseInput(lines);

        // Part #1
        System.out.println("Solution #1: " + findExitCost(input.subList(0, 1024)));

        // Part #2
        int blockingIndex = binarySearch(1024, input.size(), i -> findExitCost(input.subList(0, i)) == null) - 1;
        System.out.println("Solution #2: " + Arrays.toString(input.get(blockingIndex)));
    }

    private static List<int[]> parseInput(List<String> lines) {
        List<int[]> parsedInput = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            parsedInput.add(Arrays.stream(parts).mapToInt(Integer::parseInt).toArray());
        }
        return parsedInput;
    }

    private static Integer findExitCost(List<int[]> bytesCount) {
        Set<String> corrupted = new HashSet<>();
        for (int[] coords : bytesCount) {
            corrupted.add(key(coords));
        }

        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{0, 0});

        Map<String, Integer> costs = new HashMap<>();
        costs.put(key(0, 0), 0);

        while (!toVisit.isEmpty()) {
            int[] current = toVisit.poll();
            int x = current[0], y = current[1];
            int cost = costs.get(key(x, y));

            int[][] neighbors = {
                    {x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1}
            };

            for (int[] neighbor : neighbors) {
                int nx = neighbor[0], ny = neighbor[1];
                String neighborKey = key(nx, ny);
                if (nx >= 0 && ny >= 0 && nx < WIDTH && ny < HEIGHT
                        && !corrupted.contains(neighborKey)
                        && (!costs.containsKey(neighborKey) || cost + 1 < costs.get(neighborKey))) {
                    costs.put(neighborKey, cost + 1);
                    toVisit.add(neighbor);
                }
            }
        }
        return costs.get(key(WIDTH - 1, HEIGHT - 1));
    }

    private static String key(int... items) {
        return Arrays.stream(items).mapToObj(String::valueOf).reduce((a, b) -> a + ":" + b).orElse("");
    }

    private static int binarySearch(int lowerBound, int upperBound, java.util.function.Predicate<Integer> condition) {
        while (lowerBound < upperBound) {
            int mid = (lowerBound + upperBound) / 2;
            if (condition.test(mid)) {
                upperBound = mid;
            } else {
                lowerBound = mid + 1;
            }
        }
        return lowerBound;
    }
}
