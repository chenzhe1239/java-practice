package com.janosgyerik.practice.codility.easy;

import org.junit.Assert;
import org.junit.Test;

public class FroggyTest {

    @Test
    public void test_d_overshoot() {
        Assert.assertEquals(3, submit(10, 85, 30));
    }

    @Test
    public void test_d_to_exact() {
        Assert.assertEquals(2, submit(10, 70, 30));
    }

    int submit(int x, int y, int d) {
        return new Froggy().submit(x, y, d);
    }
}
