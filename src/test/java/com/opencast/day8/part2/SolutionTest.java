package com.opencast.day8.part2;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample() {
        //given
        //when//then
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/testInput.txt")))
                .isEqualTo(34L);
    }


    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
        //given
        //when//then
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/input.txt")))
                .isEqualTo(1246L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCTrivialExample() {
        //given
        //when//then
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/trivial.txt")))
                .isEqualTo(5L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCThreeAntennasExample() {
        //given
        //when//then
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/threeAntennas.txt")))
                .isEqualTo(8L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCAntennaWithoutPairExample() {
        //given
        //when//then
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/antennaWithoutPair.txt")))
                .isEqualTo(8L);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCAntennaPartTwoExample() {
        //given
        //when//then
        assertThat(Solution.getNumberOfAntinodes(InputReader.readInput("day8/partTwoAdditionalTest.txt")))
                .isEqualTo(9L);
    }
}
