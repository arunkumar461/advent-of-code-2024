package com.opencast.day25;

import com.opencast.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Solution {

    public static void main(String[] args) {
        List<String> input = InputReader.readInput("day25/input.txt");

        List<int[]> locks = new ArrayList<>();
        List<int[]> keys = new ArrayList<>();

        while (!input.isEmpty()) {
            List<String> next = shiftUntil(input, String::isEmpty, line -> line);

            int[] object = new int[5];
            if (next.getFirst().equals("#####")) {
                locks.add(object);
            } else {
                keys.add(object);
            }

            next.removeLast();
            forEach2D(next, '#', (x, y) -> object[x]++);
        }

        long matches = locks.stream()
                .flatMap(lock -> keys.stream()
                        .filter(key -> {
                            for (int i = 0; i < lock.length; i++) {
                                if (lock[i] + key[i] > 5) {
                                    return false;
                                }
                            }
                            return true;
                        }))
                .count();

        System.out.println("solution #1: " + matches);
    }

    public static List<String> shiftUntil(List<String> input, Predicate<String> condition, Function<String, String> mapValue) {
        List<String> result = new ArrayList<>();

        while (!input.isEmpty()) {
            String elem = input.removeFirst();
            if (condition.test(elem)) {
                break;
            }
            result.add(mapValue.apply(elem));
        }

        return result;
    }

    public static void forEach2D(List<String> arr, char val, BiConsumer<Integer, Integer> callback) {
        for (int i = 0; i < arr.size(); i++) {
            String line = arr.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == val) {
                    callback.accept(j, i);
                }
            }
        }
    }
}
