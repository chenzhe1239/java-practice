package com.janosgyerik.practice.oj.leetcode.medium.UniqueBinarySearchTrees2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UniqueBinarySearchTrees2Test {
    private final Solution solution = new Solution();

    @Test
    public void test_3() {
        assertThat(solution.generateTrees(3)).hasSize(5);
    }

    @Test
    public void test_7() {
        assertThat(solution.generateTrees(7)).hasSize(429);
    }

    @Test
    public void test_8() {
        assertThat(solution.generateTrees(8)).hasSize(1430);
    }
}
