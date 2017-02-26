package com.janosgyerik.practice.oj.leetcode.hard.MaximumGap;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MaximumGapTest {
    private final Solution solution = new Solution();

    @Test
    public void test_16() {
        int[] nums = {15252, 16764, 27963, 7817, 26155, 20757, 3478, 22602, 20404, 6739, 16790, 10588, 16521, 6644, 20880, 15632, 27078, 25463, 20124, 15728, 30042, 16604, 17223, 4388, 23646, 32683, 23688, 12439, 30630, 3895, 7926, 22101, 32406, 21540, 31799, 3768, 26679, 21799, 23740};
        assertThat(solution.maximumGap(nums)).isEqualTo(2901);
    }
}
