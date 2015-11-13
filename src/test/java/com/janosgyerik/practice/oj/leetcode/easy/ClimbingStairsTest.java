package com.janosgyerik.practice.oj.leetcode.easy;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class ClimbingStairsTest {
    public int climbStairs(int n) {
        int count = 1;
        int maxPossibleTwoSteps = n / 2;
        for (int i = 1; i <= maxPossibleTwoSteps; ++i) {
            int numberOfStepsIncludingNTwoSteps = getNumberOfStepsIncludingNTwoSteps(n, i);
            count += nCr(numberOfStepsIncludingNTwoSteps, i);
        }
        return count;
    }

    private int getNumberOfStepsIncludingNTwoSteps(int n, int i) {
        return i + (n - i * 2);
    }

    private int nCr(int n, int r) {
        return factorial(n, r).divide(factorial(n - r)).intValue();
    }

    private BigInteger factorial(int n) {
        BigInteger product = BigInteger.ONE;
        for (int i = 2; i <= n; ++i) {
            product = product.multiply(BigInteger.valueOf(i));
        }
        return product;
    }

    private BigInteger factorial(int n, int r) {
        BigInteger product = BigInteger.ONE;
        for (int i = r + 1; i <= n; ++i) {
            product = product.multiply(BigInteger.valueOf(i));
        }
        return product;
    }

    @Test
    public void testSteps_5_1() {
        assertEquals(4, getNumberOfStepsIncludingNTwoSteps(5, 1));
    }

    @Test
    public void testSteps_5_2() {
        assertEquals(3, getNumberOfStepsIncludingNTwoSteps(5, 2));
    }

    @Test
    public void test_1() {
        assertEquals(1, climbStairs(1));
    }

    @Test
    public void test_2() {
        assertEquals(2, climbStairs(2));
    }

    @Test
    public void test_3() {
        assertEquals(3, climbStairs(3));
    }

    @Test
    public void test_4() {
        // 1 1 1 1
        // 2 1 1
        // 1 2 1
        // 1 1 2
        // 2 2
        assertEquals(5, climbStairs(4));
    }

    @Test
    public void test_38() {
        assertEquals(63245986, climbStairs(38));
    }

    @Test
    public void test_35() {
        assertEquals(14930352, climbStairs(35));
    }

    @Test
    public void test_factorial_2() {
        assertEquals(2, factorial(2).intValue());
    }

    @Test
    public void test_factorial_3() {
        assertEquals(6, factorial(3).intValue());
    }

    @Test
    public void test_factorial_4() {
        assertEquals(24, factorial(4).intValue());
    }

    @Test
    public void test_factorial_4_3() {
        assertEquals(4, factorial(4, 3).intValue());
    }

    @Test
    public void test_factorial_4_2() {
        assertEquals(12, factorial(4, 2).intValue());
    }

    @Test
    public void test_5() {
        // 1 1 1 1 1
        // 2 1 1 1
        // 1 2 1 1
        // 1 1 2 1
        // 1 1 1 2
        // 2 2 1
        // 2 1 2
        // 1 2 2
        assertEquals(8, climbStairs(5));
    }

    @Test
    public void test_6() {
        assertEquals(13, climbStairs(6));
    }

    @Test
    public void test_nCr_3_2() {
        assertEquals(3, nCr(3, 2));
    }

    @Test
    public void test_nCr_4_1() {
        assertEquals(4, nCr(4, 1));
    }

    @Test
    public void test_nCr_2_2() {
        assertEquals(1, nCr(2, 2));
    }

    @Test
    public void test_factorial_37() {
        assertEquals(1096907932701818880L, factorial(37).longValue());
    }
}
