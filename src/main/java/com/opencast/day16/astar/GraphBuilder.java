package com.opencast.day16.astar;

import com.opencast.day16.part1.Direction;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class GraphBuilder {
    private static Long INFINTE = 99999999L;

    public static Graph buildGraph(List<String> input) {
        int ySize = input.size();
        int xSize = input.getFirst().length();
        var graph = new HashSet<GraphElement>();
        GraphElement start = null;
        GraphElement end = null;

        for (int y = 0; y < ySize; y++) {
            char[] line = input.get(y).toCharArray();

            for (int x = 0; x < xSize; x++) {
                var element = switch (line[x]) {
                    case '#' -> null;
                    case 'S' -> {
                        start = new GraphElement(x, y, 0L);
                        yield start;
                    }
                    case 'E' -> {
                        end = new GraphElement(x, y, INFINTE);
                        yield end;
                    }
                    default -> new GraphElement(x, y, INFINTE);
                };

                if (element != null) {
                    graph.add(element);
                }
            }
        }

        var checkedGraph = new HashSet<GraphElement>();
        calculateCostFromNode(start, graph, checkedGraph, Direction.EAST, end, true);

        return new Graph(graph, start, end);
    }

    private static void calculateCostFromNode(GraphElement node, HashSet<GraphElement> graph, HashSet<GraphElement> checked, Direction previousDirection, GraphElement end, boolean sameDirection) {
        if (checked.isEmpty()) {
            graph.remove(node);
            checked.add(new GraphElement(node.x(), node.y(), 0L));
        }

        var neighbours = graph.stream()
                .filter(ge -> (abs(ge.x() - node.x()) == 1 && (ge.y() - node.y()) == 0) || (abs(ge.y() - node.y()) == 1 && abs(ge.x() - node.x()) == 0))
                .collect(Collectors.toSet());

        if (neighbours.isEmpty() && !node.equals(end)) {
            graph.remove(node);
            checked.add(new GraphElement(node.x(), node.y(), INFINTE));
        }

        if (neighbours.isEmpty() && node.equals(end)) {
            graph.remove(node);
            checked.add(new GraphElement(node.x(), node.y(), 1L));
        }

        if (graph.contains(node)) {
            graph.remove(node);
            checked.add(new GraphElement(node.x(), node.y(), sameDirection ? 1L : 1001L));
        }

        neighbours.stream()
                .forEach(ge -> {
                    var newDirection = detectDirection(node.x(), ge.x(), node.y(), ge.y());
                    calculateCostFromNode(ge, graph, checked, newDirection, end, previousDirection.equals(newDirection));
                });

    }

    private static Direction detectDirection(int nodeX, int neighbourX, int nodeY, int neighbourY) {
        if (neighbourX - nodeX == 1) {
            return Direction.EAST;
        }

        if (neighbourX - nodeX == -1) {
            return Direction.WEST;
        }

        if (neighbourY - nodeY == 1) {
            return Direction.SOUTH;
        }

        return Direction.NORTH;
    }
}
