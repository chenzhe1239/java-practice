package com.janosgyerik.practice.codility.easy;

import org.junit.Assert;
import org.junit.Test;

public class IsPermTest {
    @Test
    public void test_1() {
        Assert.assertEquals(1, submit(new int[]{1}));
    }

    @Test
    public void test_1_2() {
        Assert.assertEquals(1, submit(new int[]{1, 2}));
    }

    @Test
    public void test_2() {
        Assert.assertEquals(0, submit(new int[]{2}));
    }

    private int submit(int[] arr) {
        return new IsPerm().submit(arr);
    }
}
