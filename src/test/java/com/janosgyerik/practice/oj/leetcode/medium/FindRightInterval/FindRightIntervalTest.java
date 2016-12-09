package com.janosgyerik.practice.oj.leetcode.medium.FindRightInterval;

import com.janosgyerik.practice.oj.leetcode.common.Interval;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FindRightIntervalTest {
    private final Solution solution = new Solution();

    @Test
    public void test_1_4__2_3__3_4() {
        Interval[] intervals = {
                new Interval(1, 4),
                new Interval(2, 3),
                new Interval(3, 4)
        };
        assertThat(solution.findRightInterval(intervals)).isEqualTo(new int[]{-1, 2, -1});
    }

    @Test
    public void test_3_4__2_3__1_2() {
        Interval[] intervals = {
                new Interval(3, 4),
                new Interval(2, 3),
                new Interval(1, 2)
        };
        assertThat(solution.findRightInterval(intervals)).isEqualTo(new int[]{-1, 0, 1});
    }

    @Test
    public void test_1_2() {
        Interval[] intervals = {new Interval(1, 2)};
        assertThat(solution.findRightInterval(intervals)).isEqualTo(new int[]{-1});
    }
}
