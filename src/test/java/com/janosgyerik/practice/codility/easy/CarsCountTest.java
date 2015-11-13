package com.janosgyerik.practice.codility.easy;

import org.junit.Assert;
import org.junit.Test;

public class CarsCountTest {
    @Test
    public void test_1_car_0() {
        Assert.assertEquals(0, submit(new int[]{0}));
        Assert.assertEquals(0, submit(new int[]{1}));
    }

    @Test
    public void test_0_1() {
        Assert.assertEquals(1, submit(new int[]{0, 1}));
    }

    @Test
    public void test_1_0() {
        Assert.assertEquals(0, submit(new int[]{1, 0}));
    }

    @Test
    public void test_0_1_0_1_1() {
        Assert.assertEquals(5, submit(new int[]{0, 1, 0, 1, 1}));
    }

    @Test
    public void test_too_large() {
        int[] arr = new int[100000];
        for (int i = arr.length / 2; i < arr.length; ++i) {
            arr[i] = 1;
        }
        Assert.assertEquals(-1, submit(arr));
    }

    private int submit(int[] arr) {
        return new CarsCount().submit(arr);
    }
}
