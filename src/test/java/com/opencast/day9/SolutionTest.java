package com.opencast.day9;

import com.opencast.InputReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {


    @Test
    void shouldReturnCorrectAnswerForPart1AoCPuzzleInput() {
        assertThat(Solution.defragPart1(InputReader.readInput("day9/input.txt").getFirst())).isEqualTo(6370402949053L);
    }

    @Test
    void shouldReturnCorrectAnswerForPart2AoCPuzzleInput() {
        assertThat(Solution.defragPart2(InputReader.readInput("day9/input.txt").getFirst())).isEqualTo(6398096697992L);
    }


}
