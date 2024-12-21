package com.opencast.day13.part1;

import org.junit.jupiter.api.Test;
import com.opencast.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample1() {
        
        assertThat(Solution.getMinimumTokens(InputReader.readInput("day13/ex1.txt")))
                .isEqualTo(280L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCExample2() {
        
        assertThat(Solution.getMinimumTokens(InputReader.readInput("day13/ex2.txt")))
                .isEqualTo(480L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCInput() {
        
        assertThat(Solution.getMinimumTokens(InputReader.readInput("day13/input.txt")))
                .isEqualTo(28262L);
    }
}
