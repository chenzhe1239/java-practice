package com.janosgyerik.practice.codility.easy;

import org.junit.Assert;
import org.junit.Test;

public class BinGapTest {
    private final BinGap bingap = new BinGap();

    @Test
    public void test_longest_sequence_of_zeros() {
        Assert.assertEquals(0, bingap.longestSeqOfZeros(0));
        Assert.assertEquals(0, bingap.longestSeqOfZeros(1));
        Assert.assertEquals(1, bingap.longestSeqOfZeros(2));
        Assert.assertEquals(0, bingap.longestSeqOfZeros(3));
        Assert.assertEquals(2, bingap.longestSeqOfZeros(4));
        Assert.assertEquals(5, bingap.longestSeqOfZeros(65));
    }

    @Test
    public void test_longest_gap_0_1() {
        Assert.assertEquals(0, bingap.longestGapOfZeros(0));
        Assert.assertEquals(0, bingap.longestGapOfZeros(1));
    }

    @Test
    public void test_longest_gap_2() {
        Assert.assertEquals(0, bingap.longestGapOfZeros(2));
    }

    @Test
    public void test_longest_gap_3() {
        Assert.assertEquals(0, bingap.longestGapOfZeros(3));
    }

    @Test
    public void test_longest_gap_4() {
        Assert.assertEquals(0, bingap.longestGapOfZeros(4));
    }

    @Test
    public void test_longest_gap_5() {
        Assert.assertEquals(1, bingap.longestGapOfZeros(5));
        Assert.assertEquals(2, bingap.longestGapOfZeros(9));
        Assert.assertEquals(3, bingap.longestGapOfZeros(99));
        Assert.assertEquals(2, bingap.longestGapOfZeros(999));
        Assert.assertEquals(4, bingap.longestGapOfZeros(9999));
        Assert.assertEquals(4, bingap.longestGapOfZeros(99999));

    }
}
