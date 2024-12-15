package com.opencast.day7.part1;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
      
        assertThat(Solution.getSumOfCalibrationResults(InputReader.readInput("day7/testInput.txt")))
                .isEqualTo(3749L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCOwnExample() {
      
        assertThat(Solution.getSumOfCalibrationResults(InputReader.readInput("day7/additional.txt")))
                .isEqualTo(19108L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
      
        assertThat(Solution.getSumOfCalibrationResults(InputReader.readInput("day7/input.txt")))
                .isEqualTo(5030892084481L);
    }
}
