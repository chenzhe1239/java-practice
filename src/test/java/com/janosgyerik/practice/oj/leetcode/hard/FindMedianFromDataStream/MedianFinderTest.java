package com.janosgyerik.practice.oj.leetcode.hard.FindMedianFromDataStream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MedianFinderTest {

    private static final double DELTA = 0.0001;

    private MedianFinder create(int... nums) {
        MedianFinder finder = new MedianFinder();
        for (int num : nums) {
            finder.addNum(num);
        }
        return finder;
    }

    @Test
    public void example_1_2_gives_1p5() {
        MedianFinder finder = create(1, 2);
        assertEquals(1.5, finder.findMedian(), DELTA);
    }

    @Test
    public void example_1_2_3_gives_2() {
        MedianFinder finder = create(1, 2, 3);
        assertEquals(2, finder.findMedian(), DELTA);
    }

    @Test
    public void example_2_3_gives_2p5() {
        MedianFinder finder = create(2, 3);
        assertEquals(2.5, finder.findMedian(), DELTA);
    }

    @Test
    public void example_2_3_4_gives_3() {
        MedianFinder finder = create(2, 3, 4);
        assertEquals(3, finder.findMedian(), DELTA);
    }

    @Test
    public void example_m1_m2_gives_m1p5() {
        MedianFinder finder = create(-1, -2);
        assertEquals(-1.5, finder.findMedian(), DELTA);
    }
}
