package com.janosgyerik.practice.codility.easy;

import org.junit.Assert;
import org.junit.Test;

public class MaxTripletProdTest {
    @Test
    public void test_example() {
        Assert.assertEquals(60, submit(new int[]{-3, 1, 2, -2, 5, 6}));
    }

    @Test
    public void test_3_items() {
        Assert.assertEquals(6, submit(new int[]{1, 2, 3}));
    }

    @Test
    public void test_one_kind() {
        Assert.assertEquals(8, submit(new int[]{2, 2, 2}));
    }

    @Test
    public void test_2kinds() {
        Assert.assertEquals(12, submit(new int[]{2, 2, 3}));
    }

    @Test
    public void test_negative_highest() {
        Assert.assertEquals(-6, submit(new int[]{-1, -2, -3}));
    }

    private int submit(int[] arr) {
        return new MaxTripletProd().submit(arr);
    }
}
