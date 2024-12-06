package com.opencast.day5.part2;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
        //given
        //when//then
        assertThat(Solution.getSumOfFixedMiddlePages(InputReader.readInput("day5/exampleInput/rules.txt"), InputReader.readInput("day5/exampleInput/order.txt")))
                .isEqualTo(123L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
        //given
        //when//then
        assertThat(Solution.getSumOfFixedMiddlePages(InputReader.readInput("day5/input/rules.txt"), InputReader.readInput("day5/input/order.txt")))
                .isEqualTo(5180L);
    }
}
