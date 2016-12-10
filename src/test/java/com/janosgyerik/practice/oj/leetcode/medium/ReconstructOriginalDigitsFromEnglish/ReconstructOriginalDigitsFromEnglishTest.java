package com.janosgyerik.practice.oj.leetcode.medium.ReconstructOriginalDigitsFromEnglish;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReconstructOriginalDigitsFromEnglishTest {
    private final Solution solution = new Solution();

    @Test
    public void test_owoztneoer_is_012() {
        assertThat(solution.originalDigits("owoztneoer")).isEqualTo("012");
    }

    @Test
    public void test_zerozero_is_00() {
        assertThat(solution.originalDigits("zerozero")).isEqualTo("00");
    }
}
