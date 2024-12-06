package com.opencast.day1.part1;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
        //given
        //when//then
        assertThat(Solution.getDistancesSummary(InputReader.readInput("day1/testInput.txt")))
                .isEqualTo(11);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
        //given
        //when//then
        assertThat(Solution.getDistancesSummary(InputReader.readInput("day1/input.txt")))
                .isEqualTo(1941353);
    }
}
