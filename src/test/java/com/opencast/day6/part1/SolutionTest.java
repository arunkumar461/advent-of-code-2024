package com.opencast.day6.part1;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
        //given
        //when//then
        assertThat(Solution.predictNumberOfVisitedPositions(InputReader.readInput("day6/testInput.txt")))
                .isEqualTo(41L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
        //given
        //when//then
        assertThat(Solution.predictNumberOfVisitedPositions(InputReader.readInput("day6/input.txt")))
                .isEqualTo(5305L);
    }
}
