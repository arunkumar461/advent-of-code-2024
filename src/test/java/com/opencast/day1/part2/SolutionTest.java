package com.opencast.day1.part2;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
        //given
        //when//then
        assertThat(Solution.getSimilarity(InputReader.readInput("day1/testInput.txt")))
                .isEqualTo(31L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
        //given
        //when//then
        assertThat(Solution.getSimilarity(InputReader.readInput("day1/input.txt")))
                .isEqualTo(22539317L);
    }
}
