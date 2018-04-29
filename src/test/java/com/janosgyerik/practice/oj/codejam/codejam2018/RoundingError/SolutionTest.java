package com.janosgyerik.practice.oj.codejam.codejam2018.RoundingError;

import java.util.Scanner;
import org.junit.Test;

import static com.janosgyerik.practice.oj.codejam.codejam2018.RoundingError.Solution.minVotesToRoundUp;
import static com.janosgyerik.practice.oj.codejam.codejam2018.RoundingError.Solution.rounded;
import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void test_solve() {
        String input = "4\n" +
            "3 2\n" +
            "1 1\n" +
            "10 3\n" +
            "1 3 2\n" +
            "6 2\n" +
            "3 1\n" +
            "9 8\n" +
            "1 1 1 1 1 1 1 1";
        solution.solve(new Scanner(input));
    }

//    @Ignore
    @Test
    public void test_3_2__1_1() {
        assertThat(solution.solve(3, 1, 1)).isEqualTo(100);
    }

//    @Ignore
    @Test
    public void test_10_3__1_3_2() {
        assertThat(solution.solve(10, 1, 3, 2)).isEqualTo(100);
    }

//    @Ignore
    @Test
    public void test_6_2__3_1() {
        assertThat(solution.solve(6, 3, 1)).isEqualTo(101);
    }

//    @Ignore
    @Test
    public void test_9_8__1_1_1_1_1_1_1_1() {
        assertThat(solution.solve(9, 1, 1, 1, 1, 1, 1, 1, 1)).isEqualTo(99);
    }

    @Test
    public void minVotesToRoundUp_3_is_2() {
        assertThat(minVotesToRoundUp(3)).isEqualTo(2);
    }

    @Test
    public void minVotesToRoundUp_9_is_5() {
        assertThat(minVotesToRoundUp(9)).isEqualTo(5);
    }

    @Test
    public void minVotesToRoundUp_7_is_2() {
        assertThat(minVotesToRoundUp(7)).isEqualTo(2);
    }

    @Test
    public void minVotesToRoundUp_9_1_5_is_4() {
        assertThat(minVotesToRoundUp(9, 1, 5)).isEqualTo(4);
    }

    @Test
    public void minVotesToRoundUp_9_2_5_is_3() {
        assertThat(minVotesToRoundUp(9, 2, 5)).isEqualTo(3);
    }

    @Test
    public void minVotesToRoundUp_9_3_5_is_2() {
        assertThat(minVotesToRoundUp(9, 3, 5)).isEqualTo(2);
    }

    @Test
    public void minVotesToRoundUp_3_1_2_is_1() {
        assertThat(minVotesToRoundUp(3, 1, 2)).isEqualTo(1);
    }

    @Test
    public void minVotesToRoundUp_19_15_is_0() {
        assertThat(minVotesToRoundUp(19, 15, 2)).isEqualTo(0);
    }

    @Test
    public void minVotesToRoundUp_19_16_is_1() {
        assertThat(minVotesToRoundUp(19, 16, 2)).isEqualTo(2);
    }

    @Test
    public void minVotesToRoundUp_19_17_is_1() {
        assertThat(minVotesToRoundUp(19, 17, 2)).isEqualTo(1);
    }

    @Test
    public void rounded_7_1_is_14() {
        assertThat(rounded(7, 1)).isEqualTo(14);
    }

    @Test
    public void rounded_7_2_is_29() {
        assertThat(rounded(7, 2)).isEqualTo(29);
    }
}
