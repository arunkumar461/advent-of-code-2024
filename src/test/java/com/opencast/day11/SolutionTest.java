package com.opencast.day11;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput() {
      
        assertThat(Solution.getNumberOfStonesAfterBlinks("0 7 198844 5687836 58 2478 25475 894", 25))
                .isEqualTo(216996L);
    }


    @Test
    void shouldReturnCorrectAnswerForAoCPuzzleInput2() {
      
        assertThat(Solution.getNumberOfStonesAfterBlinks("0 7 198844 5687836 58 2478 25475 894", 75))
                .isEqualTo(257335372288947L);
    }
}
