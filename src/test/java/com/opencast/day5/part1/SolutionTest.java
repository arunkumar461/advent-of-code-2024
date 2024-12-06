package com.opencast.day5.part1;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
        //given
        //when//then
        assertThat(Solution.getSumOfCorrectMiddlePages(InputReader.readInput("day5/exampleInput/rules.txt"), InputReader.readInput("day5/exampleInput/order.txt")))
                .isEqualTo(143L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
        //given
        //when//then
        assertThat(Solution.getSumOfCorrectMiddlePages(InputReader.readInput("day5/input/rules.txt"), InputReader.readInput("day5/input/order.txt")))
                .isEqualTo(5713L);
    }
}
