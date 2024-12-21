package com.opencast.day16;

import java.util.Objects;

public record Position(int x, int y) {
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Position)) {
            return false;
        }

        Position c = (Position) o;

        return c.x == x && c.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x + y);
    }
}
