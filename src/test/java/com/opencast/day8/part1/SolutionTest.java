package com.opencast.day8.part1;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
      
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/testInput.txt")))
                .isEqualTo(14L);
    }


    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
      
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/input.txt")))
                .isEqualTo(390L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCTrivialExample() {
      
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/trivial.txt")))
                .isEqualTo(2L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCThreeAntennasExample() {
      
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/threeAntennas.txt")))
                .isEqualTo(4L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCAntennaWithoutPairExample() {
      
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/antennaWithoutPair.txt")))
                .isEqualTo(4L);
    }
}
