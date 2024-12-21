package com.opencast.day14.part2;

import org.junit.jupiter.api.Test;
import com.opencast.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCInput() {
        
        assertThat(Solution.getTreeSecond(InputReader.readInput("day14/input.txt"), 101, 103))
                .isEqualTo(7383);
    }
}
