package com.opencast.day12.part1;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample1() {
      
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/ex1.txt")))
                .isEqualTo(140L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCExample2() {
      
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/ex2.txt")))
                .isEqualTo(1930L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCInput() {
      
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/input.txt")))
                .isEqualTo(1363484L);
    }
}
