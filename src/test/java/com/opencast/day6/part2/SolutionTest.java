package com.opencast.day6.part2;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {


    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
        //given
        //when//then
        assertThat(Solution.countPossibleLoopPositions(InputReader.readInput("day6/testInput.txt")))
                .isEqualTo(6L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
        //given
        //when//then
        assertThat(Solution.countPossibleLoopPositions(InputReader.readInput("day6/input.txt")))
                .isEqualTo(2143L);
    }
}
