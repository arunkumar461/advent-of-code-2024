package com.opencast.day16.part1;

import com.opencast.day16.Position;

import java.util.Objects;

public record Candidate(Position position, long cost, Direction facing) {
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Candidate)) {
            return false;
        }

        Candidate c = (Candidate) o;

        return c.position.x() == position.x() && c.position.y() == position.y() && c.facing.equals(facing);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position.hashCode() + facing.hashCode());

    }
}
