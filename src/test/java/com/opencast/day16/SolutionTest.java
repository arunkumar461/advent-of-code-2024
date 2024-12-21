package com.opencast.day16;

import org.junit.jupiter.api.Test;
import com.opencast.InputReader;
import com.opencast.day16.part1.SolutionDjikstra;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample1() {

        assertThat(SolutionDjikstra.getMinimumScore(InputReader.readInput("day16/ex.txt")))
                .isEqualTo(7036L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCInput() {

        assertThat(SolutionDjikstra.getMinimumScore(InputReader.readInput("day16/input.txt")))
                .isEqualTo(7036L);
    }
}
