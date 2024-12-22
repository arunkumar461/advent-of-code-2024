package com.opencast.day16.part1;

import com.opencast.day16.Maze;
import com.opencast.day16.MazeTile;
import com.opencast.day16.Position;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private static final HashMap<Candidate, Long> cache = new HashMap<>();

    public static long getMinimumScore(List<String> input) {
        var maze = Maze.mapMaze(input);

        var initialCandidate = new Candidate(new Position(maze.startX(), maze.startY()), 0L, Direction.EAST);
        return findPath(initialCandidate, maze.maze(), Collections.emptySet());
    }

    private static long findPath(Candidate candidate, MazeTile[][] mazeMap, Set<Position> usedPositions) {
        System.out.printf("x: %s; y: %s%n", candidate.position().x(), candidate.position().y());
        if (mazeMap[candidate.position().y()][candidate.position().x()].equals(MazeTile.END)) {
            return 0L;
        }

        var usedPositionsUpdate = new HashSet<>(usedPositions);
        usedPositionsUpdate.add(candidate.position());

        var candidates = getCandidates(candidate.position().x(), candidate.position().y(), candidate.facing(), mazeMap, usedPositionsUpdate);
        return candidates.stream()
                .sorted(Comparator.comparing(Candidate::cost))
                .mapToLong(c -> {
                    long cost = c.cost() + findPath(c, mazeMap, usedPositionsUpdate);
                    cache.put(c, cost);
                    return cost;
                })
                .min().orElse(9999999999L);
    }

    private static Set<Candidate> getCandidates(int xPosition, int yPosition, Direction facingDirection, MazeTile[][] mazeMap, Set<Position> usedPositions) {
        var candidates = new HashSet<Candidate>();

        candidates.add(
                new Candidate(new Position(xPosition + facingDirection.getxVector(), yPosition + facingDirection.getyVector()), 1L, facingDirection)
        );

        Direction rightDirection = facingDirection.getHasOnRight();
        candidates.add(
                new Candidate(new Position(xPosition + rightDirection.getxVector(), yPosition + rightDirection.getyVector()), 1001L, rightDirection)
        );

        Direction backDirection = rightDirection.getHasOnRight();
        candidates.add(
                new Candidate(new Position(xPosition + backDirection.getxVector(), yPosition + backDirection.getyVector()), 2001L, backDirection)
        );

        Direction leftDirection = backDirection.getHasOnRight();
        candidates.add(
                new Candidate(new Position(xPosition + leftDirection.getxVector(), yPosition + leftDirection.getyVector()), 1001L, leftDirection)
        );

        return candidates.stream()
                .filter(c -> !usedPositions.contains(c.position()) && !mazeMap[c.position().y()][c.position().x()].equals(MazeTile.WALL))
                .collect(Collectors.toSet());
    }
}
