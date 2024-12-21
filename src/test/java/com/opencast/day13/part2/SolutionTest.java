package com.opencast.day13.part2;

import org.junit.jupiter.api.Test;
import com.opencast.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample1() {
        
        assertThat(Solution.getMinimumTokens(InputReader.readInput("day13/ex1.txt")))
                .isZero();
    }

    @Test
    void shouldReturnCorrectAnswerForAoCExample2() {
        
        assertThat(Solution.getMinimumTokens(InputReader.readInput("day13/ex2.txt")))
                .isEqualTo(875318608908L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCInput() {
        
        assertThat(Solution.getMinimumTokens(InputReader.readInput("day13/input.txt")))
                .isEqualTo(101406661266314L);
    }
}
