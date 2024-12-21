package com.opencast.day16.astar;

import java.util.Objects;

public record GraphElement(int x, int y, long cost) {
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof GraphElement)) {
            return false;
        }

        GraphElement c = (GraphElement) o;

        return c.x == x && c.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x + y);
    }
}
