package com.janosgyerik.practice.oj.codingame.easy.horse;

import org.junit.Assert;
import org.junit.Test;

public class HorseRacingTest {

    private int diffBetweenTwoClosest(int[] arr) {
        return new HorseRacing().diffBetweenTwoClosest(arr);
    }

    @Test
    public void testOnlyOneHorse() {
        Assert.assertEquals(0, diffBetweenTwoClosest(new int[]{5}));
    }

    @Test
    public void testAllTheSameStrength() {
        Assert.assertEquals(0, diffBetweenTwoClosest(new int[]{5, 5, 5}));
    }

    @Test
    public void testBasicExample() {
        Assert.assertEquals(1, diffBetweenTwoClosest(new int[]{5, 8, 9}));
        Assert.assertEquals(1, diffBetweenTwoClosest(new int[]{5,
                15,
                17,
                3,
                8,
                11,
                28,
                6,
                55,
                7}));
    }

    @Test
    public void testHarderExample() {

    }
}
