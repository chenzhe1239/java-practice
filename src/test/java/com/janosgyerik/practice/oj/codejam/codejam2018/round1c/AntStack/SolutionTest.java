package com.janosgyerik.practice.oj.codejam.codejam2018.round1c.AntStack;

import java.util.Arrays;
import java.util.Scanner;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void test_solve() {
        String input = "3\n" +
            "2\n" +
            "9 1\n" +
            "3\n" +
            "8 4 100\n" +
            "9\n" +
            "10 10 10 10 10 10 10 10 100";
        solution.solve(new Scanner(input));
    }

    @Test
    public void test_7_2_4_9_10_1_8_3_5_6() {
        assertThat(solution.solve(7, 2, 4, 9, 10, 1, 8, 3, 5, 6)).isEqualTo(7);
    }

    @Test
    public void test_2_9_7_10_6_4_8_5_1_3() {
        assertThat(solution.solve(2, 9, 7, 10, 6, 4, 8, 5, 1, 3)).isEqualTo(6);
    }

    @Test
    public void test_minWeightsPerLength() {
        assertThat(solution.minWeightsPerLength(7, 2, 4)).isEqualTo(Arrays.asList(0, 2, 6, 13));
    }

}
