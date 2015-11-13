package com.janosgyerik.practice.codility.easy;


import org.junit.Assert;
import org.junit.Test;

public class TapeMinDiffTest {

    @Test
    public void test_diff_1_2_is_1() {
        Assert.assertEquals(1, submit(new int[]{1, 2}));
    }

    @Test
    public void test_diff_1_3_is_2() {
        Assert.assertEquals(2, submit(new int[]{1, 3}));
    }

    @Test
    public void test_diff_2_1_is_1() {
        Assert.assertEquals(1, submit(new int[]{2, 1}));
    }

    @Test
    public void test_diff_1_2_1_is_1() {
        Assert.assertEquals(2, submit(new int[]{1, 2, 1}));
    }

    @Test
    public void test_diff_1_2_3_is_1() {
        Assert.assertEquals(0, submit(new int[]{1, 2, 3}));
    }

    @Test
    public void test_diff_7_2_3_is_1() {
        Assert.assertEquals(2, submit(new int[]{7, 2, 3}));
    }

    @Test
    public void test_diff_3_1_2_4_3_is_1() {
        Assert.assertEquals(1, submit(new int[]{3, 1, 2, 4, 3}));
    }

    @Test
    public void test_diff_1_7() {
        Assert.assertEquals(6, submit(new int[]{1, 7}));
    }

    @Test
    public void test_diff_1_1_7() {
        Assert.assertEquals(5, submit(new int[]{1, 1, 7}));
    }

    @Test
    public void test_diff_1_1_1_7() {
        Assert.assertEquals(4, submit(new int[]{1, 1, 1, 7}));
    }

    @Test
    public void test_diff_1_1_1_1_7() {
        Assert.assertEquals(3, submit(new int[]{1, 1, 1, 1, 7}));
    }

    @Test
    public void test_diff_0_20() {
        Assert.assertEquals(20, submit(new int[]{0, 20}));
    }

    @Test
    public void test_diff_m1_m2() {
        Assert.assertEquals(1, submit(new int[]{-1, -2}));
    }

    @Test
    public void test_diff_2_m2() {
        Assert.assertEquals(4, submit(new int[]{2, -2}));
    }

    private int submit(int[] arr) {
        return new TapeMinDiff().submit(arr);
    }
}
