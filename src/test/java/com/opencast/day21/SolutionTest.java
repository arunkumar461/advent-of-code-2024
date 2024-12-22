package com.opencast.day21;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCPart1() {

        assertThat(Solution.part1(InputReader.readInput("day21/input.txt")))
                .isEqualTo(152942L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPart2() {

        assertThat(Solution.part2(InputReader.readInput("day21/input.txt")))
                .isEqualTo(189235298434780L);
    }

}
