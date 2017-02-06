package com.janosgyerik.practice.oj.leetcode.hard.CountSmallerNumbersAfterSelf;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class CountSmallerNumbersAfterSelfTest {

    private final Solution solution = new Solution();

    private List<Integer> solve(int... nums) {
        return solution.countSmaller(nums);
    }

    @Test
    public void test_for_5_2_6_1_should_return_2_1_1_0() {
        assertEquals(Arrays.asList(2, 1, 1, 0), solve(5, 2, 6, 1));
    }

    @Test
    public void test_for_m1_should_return_0() {
        assertEquals(Collections.singletonList(0), solve(-1));
    }

    @Test
    public void test_for_m1_m1_should_return_0_0() {
        assertEquals(Arrays.asList(0, 0), solve(-1, -1));
    }

    @Test
    public void test_SortedList_insert() {
        Solution.SortedList list = new Solution.SortedList(10);
        list.insert(0, 3);
        list.insert(0, 2);
        list.insert(2, 4);

        assertThat(list.list()).containsExactly(2, 3, 4);
    }

    @Test
    public void test_SortedList_countSmaller() {
        Solution.SortedList list = new Solution.SortedList(10);
        list.insert(0, 3);
        list.insert(0, 2);
        list.insert(2, 4);

        assertThat(list.countSmaller(0)).isEqualTo(0);
        assertThat(list.countSmaller(3)).isEqualTo(1);
        assertThat(list.countSmaller(4)).isEqualTo(2);
        assertThat(list.countSmaller(5)).isEqualTo(3);
    }

    @Test
    public void test_SortedList_countSmaller_insert() {
        Solution.SortedList list = new Solution.SortedList(10);

        assertThat(list.countSmaller(1)).isEqualTo(0);
        list.insert(list.countSmaller(1), 1);

        assertThat(list.countSmaller(6)).isEqualTo(1);
        list.insert(list.countSmaller(6), 6);

        assertThat(list.countSmaller(2)).isEqualTo(1);
        list.insert(list.countSmaller(2), 2);

        assertThat(list.countSmaller(5)).isEqualTo(2);
        list.insert(list.countSmaller(5), 5);

        assertThat(list.list()).containsExactly(1, 2, 5, 6);
    }
}
