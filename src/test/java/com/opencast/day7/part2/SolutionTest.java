package com.opencast.day7.part2;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
      
        assertThat(Solution.getSumOfCalibrationResults(InputReader.readInput("day7/testInput.txt")))
                .isEqualTo(11387L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCOwnExample() {
      
        assertThat(Solution.getSumOfCalibrationResults(InputReader.readInput("day7/additional.txt")))
                .isEqualTo(19108L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
      
        assertThat(Solution.getSumOfCalibrationResults(InputReader.readInput("day7/input.txt")))
                .isEqualTo(91377448644679L);
    }
}
