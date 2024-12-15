package com.opencast.day4.part2;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
      
        assertThat(Solution.getNumberOfXmasOccurrences(InputReader.readInput("day4/testInput.txt")))
                .isEqualTo(9L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
      
        assertThat(Solution.getNumberOfXmasOccurrences(InputReader.readInput("day4/input.txt")))
                .isEqualTo(1850L);
    }
}
