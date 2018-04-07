package com.janosgyerik.practice.oj.codejam.codejam2018.TroubleSort;

import java.util.Scanner;
import org.junit.*;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void test_solve() {
        String input = "2\n" +
            "5\n" +
            "5 6 8 4 3\n" +
            "3\n" +
            "8 9 7";
        solution.solve(new Scanner(input));
    }

    @Test
    public void test_8_9_7() {
        assertThat(solution.solve(8, 9, 7)).isEqualTo(1);
    }

    @Test
    public void test_8_9_7_10() {
        assertThat(solution.solve(8, 9, 7, 10)).isEqualTo(1);
    }
}
