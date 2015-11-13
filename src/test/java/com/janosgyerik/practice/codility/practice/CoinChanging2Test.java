package com.janosgyerik.practice.codility.practice;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class CoinChanging2Test {
    private int getMinCoins(SortedSet<Integer> coins, int targetAmount) {
        int[] counts = new int[targetAmount * coins.first() + 1];
        for (int i = 1; i < counts.length; ++i) {
            counts[i] = Integer.MAX_VALUE;
        }
        for (int coin : coins) {
            for (int i = 1; i < counts.length; ++i) {
                int count = i / coin;
                int remainder = i % coin;
                int countUsingCoin = count + counts[remainder];
                int cointNotUsingCoin = counts[i];
                counts[i] = Math.min(countUsingCoin, cointNotUsingCoin);
            }
        }
        return counts[targetAmount];
    }

    @Test
    public void test_1_3_4_x_6() {
        int expected = 2;
        assertEquals(expected, getMinCoins(getCoins(1, 3, 4), 6));
    }

    @Test
    public void test_1_3_4_x_7() {
        int expected = 2;
        assertEquals(expected, getMinCoins(getCoins(1, 3, 4), 7));
    }

    @Test
    public void test_1_3_4_x_8() {
        int expected = 2;
        assertEquals(expected, getMinCoins(getCoins(1, 3, 4), 8));
    }

    @Test
    public void test_2_3_4_x_8() {
        int expected = 2;
        assertEquals(expected, getMinCoins(getCoins(2, 3, 4), 8));
    }

    @Test
    public void test_2_3_x_8() {
        int expected = 3;
        assertEquals(expected, getMinCoins(getCoins(2, 3), 8));
    }

    private SortedSet<Integer> getCoins(int... denominations) {
        SortedSet<Integer> coins = new TreeSet<>();
        for (int coin : denominations) {
            coins.add(coin);
        }
        return coins;
    }
}
