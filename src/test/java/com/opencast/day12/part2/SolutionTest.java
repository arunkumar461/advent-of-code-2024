package com.opencast.day12.part2;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample1() {
      
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/ex1.txt")))
                .isEqualTo(80L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCExample2() {
      
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/ex2.txt")))
                .isEqualTo(1206L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCExample3() {
      
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/ex3.txt")))
                .isEqualTo(236L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCExample4() {
      
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/ex4.txt")))
                .isEqualTo(368L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCInput() {
      
        assertThat(Solution.getCostOfRegionSurrounding(InputReader.readInput("day12/input.txt")))
                .isEqualTo(838988L);
    }
}
