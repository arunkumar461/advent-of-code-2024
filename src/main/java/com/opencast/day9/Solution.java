package com.opencast.day9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {

    public static Long defragPart1(String input) {
        List<String> index = getStringList(input);
        List<String> pt1 = part1Defrag(new ArrayList<>(index));
        return checksum(pt1);
    }

    public static Long defragPart2(String input) {

        List<String> index = getStringList(input);
        List<String> pt2 = part2Defrag(new ArrayList<>(index));
        return checksum(pt2);
    }

    public static List<String> getStringList(String input) {
        List<Long> numbers = new ArrayList<>();
        for (char c : input.toCharArray()) {
            numbers.add((long) Character.getNumericValue(c));
        }

        List<String> index = new ArrayList<>();

        for (long i = 0; i < numbers.size(); i++) {
            List<String> fragments = new ArrayList<>();
            if (i % 2 == 1) {
                for (long j = 0; j < numbers.get((int) i); j++) {
                    fragments.add(".");
                }
            } else {
                for (long j = 0; j < numbers.get((int) i); j++) {
                    fragments.add(String.valueOf(i / 2));
                }
            }
            index.addAll(fragments);
        }
        return index;
    }

    private static long checksum(List<String> index) {
        long checksum = 0;
        for (long i = 0; i < index.size(); i++) {
            if (index.get((int) i).equals(".")) continue;
            checksum += Long.parseLong(index.get((int) i)) * i;
        }
        return checksum;
    }

    private static List<String> part1Defrag(List<String> drive) {
        List<String> index = new ArrayList<>(drive);
        boolean noSwitch = false;

        for (long i = 0; i < index.size(); i++) {
            if (noSwitch) break;
            if (index.get((int) i).equals(".")) {
                for (long j = index.size() - 1; j > i; j--) {
                    if (index.get((int) j).equals(".")) {
                        noSwitch = true;
                        continue;
                    }
                    index.set((int) i, index.get((int) j));
                    index.set((int) j, ".");
                    noSwitch = false;
                    break;
                }
            }
        }
        return index;
    }

    private static List<String> part2Defrag(List<String> drive) {
        List<String> index = new ArrayList<>(drive);
        HashMap<String, Long> fileMap = new HashMap<>();

        for (String value : index) {
            if (!value.equals(".")) {
                fileMap.put(value, fileMap.getOrDefault(value, 0L) + 1);
            }
        }

        for (long i = index.size() - 1; i > 0; i--) {
            if (!index.get((int) i).equals(".")) {
                String currentValue = index.get((int) i);
                long fileLength = fileMap.get(currentValue);
                for (long j = 0; j < i; j++) {
                    boolean canPlace = true;
                    for (long k = j; k < j + fileLength; k++) {
                        if (k >= index.size() || !index.get((int) k).equals(".")) {
                            canPlace = false;
                            break;
                        }
                    }
                    if (canPlace) {
                        for (long k = 0; k < fileLength; k++) {
                            index.set((int) (j + k), currentValue);
                        }
                        for (long k = 0; k < fileLength; k++) {
                            index.set((int) (i - fileLength + 1 + k), ".");
                        }
                        break;
                    }
                }
            }
        }
        return index;
    }
}
