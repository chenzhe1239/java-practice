package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BestTimeBuySellStock2Test {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int profits = 0;
        int prev = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            int current = prices[i];
            if (current > prev) {
                profits += current - prev;
            }
            prev = current;
        }
        return profits;
    }

    @Test
    public void testNoBuy() {
        assertEquals(0, maxProfit(new int[]{-1}));
    }

    @Test
    public void testDecreasing() {
        assertEquals(0, maxProfit(new int[]{3, 2, 1}));
    }

    @Test
    public void testIncreasing() {
        assertEquals(2, maxProfit(new int[]{1, 2, 3}));
    }

    @Test
    public void testUpDown() {
        assertEquals(2, maxProfit(new int[]{1, 2, 3, 2, 1}));
    }

    @Test
    public void testNoChange() {
        assertEquals(0, maxProfit(new int[]{3, 3, 3, 3, 3}));
    }

    @Test
    public void testZigZag() {
        assertEquals(7, maxProfit(new int[]{0, 3, -2, 2}));
    }
}
