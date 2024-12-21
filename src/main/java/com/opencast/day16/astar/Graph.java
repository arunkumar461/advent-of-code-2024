package com.opencast.day16.astar;

import java.util.Set;

public record Graph(Set<GraphElement> content, GraphElement start, GraphElement end) {
}
