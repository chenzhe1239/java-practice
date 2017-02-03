package com.janosgyerik.practice.oj.leetcode.hard.PerfectRectangle;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PerfectRectangleTest {
    private final Solution solution = new Solution();

    boolean solve(int[]... rectangles) {
        return solution.isRectangleCover(rectangles);
    }

    @Test
    public void test_example_1() {
        assertThat(solve(
                new int[]{1, 1, 3, 3},
                new int[]{3, 1, 4, 2},
                new int[]{3, 2, 4, 4},
                new int[]{1, 3, 2, 4},
                new int[]{2, 3, 3, 4}
        )).isTrue();
    }

    @Test
    public void test_example_2() {
        assertThat(solve(
                new int[]{0, 0, 1, 1},
                new int[]{0, 1, 3, 2},
                new int[]{1, 0, 2, 2}
        )).isFalse();
    }

    @Test
    public void test_example_3() {
        assertThat(solve(
                new int[]{1, 1, 3, 3},
                new int[]{3, 1, 4, 2},
                new int[]{3, 2, 4, 4},
                new int[]{1, 3, 2, 4},
                new int[]{2, 3, 3, 4}
        )).isTrue();
    }

    @Test
    public void test_example_4() {
        assertThat(solve(
                new int[]{1, 1, 3, 3},
                new int[]{2, 2, 4, 4},
                new int[]{4, 1, 5, 4},
                new int[]{1, 3, 2, 4}
        )).isFalse();
    }
}
