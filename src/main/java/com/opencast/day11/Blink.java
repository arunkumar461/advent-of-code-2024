package com.opencast.day11;

import java.util.Objects;

public record Blink(long stone, int blinksToDo) {
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Blink)) {
            return false;
        }

        Blink c = (Blink) o;

        return c.stone == stone && c.blinksToDo == blinksToDo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stone + blinksToDo);
    }
}
