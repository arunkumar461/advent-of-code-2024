package com.opencast.day8;

import java.util.Objects;

public record AntennaPair(Antenna firsAntenna, Antenna secondAntenna, int relativeX, int relativeY) {
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AntennaPair c)) {
            return false;
        }

        return (firsAntenna.equals(c.firsAntenna) && secondAntenna.equals(c.secondAntenna)) ||
                (firsAntenna.equals(c.secondAntenna) && secondAntenna.equals(c.firsAntenna));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firsAntenna, secondAntenna) + Objects.hash(secondAntenna, firsAntenna);
    }
}
