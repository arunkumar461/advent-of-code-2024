package com.opencast.day4.part1;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
      
        assertThat(Solution.getNumberOfXmassOccurrences(InputReader.readInput("day4/testInput.txt")))
                .isEqualTo(18L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
      
        assertThat(Solution.getNumberOfXmassOccurrences(InputReader.readInput("day4/input.txt")))
                .isEqualTo(2573L);
    }
}
