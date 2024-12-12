package com.opencast.day12.part1;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample1() {
        //given
        //when//then
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/ex1.txt")))
                .isEqualTo(140L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCExample2() {
        //given
        //when//then
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/ex2.txt")))
                .isEqualTo(1930L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCInput() {
        //given
        //when//then
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/input.txt")))
                .isEqualTo(1363484L);
    }
}
