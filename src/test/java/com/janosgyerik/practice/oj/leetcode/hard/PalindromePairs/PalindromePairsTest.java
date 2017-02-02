package com.janosgyerik.practice.oj.leetcode.hard.PalindromePairs;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromePairsTest {
    private final Solution solution = new Solution();

    @Test
    public void test_0() {
        assertThat(solution.palindromePairs("a", ""))
                .containsExactlyInAnyOrder(
                        Arrays.asList(0, 1),
                        Arrays.asList(1, 0)
                        );
    }

    @Test
    public void test_1() {
        assertThat(solution.palindromePairs("abcd", "dcba", "lls", "s", "sssll"))
                .containsExactlyInAnyOrder(
                        Arrays.asList(0, 1),
                        Arrays.asList(1, 0),
                        Arrays.asList(3, 2),
                        Arrays.asList(2, 4)
                        );
    }

    @Test
    public void test_2() {
        assertThat(solution.palindromePairs("lls", "s", "sssll"))
                .containsExactlyInAnyOrder(
                        Arrays.asList(1, 0),
                        Arrays.asList(0, 2)
                        );
    }

    @Test
    public void test_3() {
        assertThat(solution.palindromePairs("a","abc","aba",""))
                .containsExactlyInAnyOrder(
                        Arrays.asList(0, 3),
                        Arrays.asList(3, 0),
                        Arrays.asList(2, 3),
                        Arrays.asList(3, 2)
                        );
    }
}
