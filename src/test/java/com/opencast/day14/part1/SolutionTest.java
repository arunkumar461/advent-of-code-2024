package com.opencast.day14.part1;

import org.junit.jupiter.api.Test;
import com.opencast.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample1() {
        assertThat(Solution.getSecurityFactor(InputReader.readInput("day14/ex1.txt"), 100, 11, 7))
                .isEqualTo(12);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCInput() {

        assertThat(Solution.getSecurityFactor(InputReader.readInput("day14/input.txt"), 100, 101, 103))
                .isEqualTo(229069152);
    }
}
