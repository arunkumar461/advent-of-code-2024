package com.opencast.day15.part2;

import org.junit.jupiter.api.Test;
import com.opencast.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectAnswerForAoCExample1() {
        
        assertThat(Solution.countFinalPackageGPS(InputReader.readInput("day15/ex_map.txt"), InputReader.readInput("day15/ex_instr.txt")))
                .isEqualTo(1751);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCExample2() {
        
        assertThat(Solution.countFinalPackageGPS(InputReader.readInput("day15/ex2_map.txt"), InputReader.readInput("day15/ex2_instr.txt")))
                .isEqualTo(9021);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCExample3() {
        
        assertThat(Solution.countFinalPackageGPS(InputReader.readInput("day15/ex3_map.txt"), InputReader.readInput("day15/ex3_instr.txt")))
                .isEqualTo(618);
    }

    @Test
    void shouldReturnCorrectAnswerForAoCInput() {
        
        assertThat(Solution.countFinalPackageGPS(InputReader.readInput("day15/input_map.txt"), InputReader.readInput("day15/input_instr.txt")))
                .isEqualTo(1576353);
    }
}
