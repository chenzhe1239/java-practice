package com.janosgyerik.practice.oj.leetcode.easy;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class FactorialTrailingZerosTest {
    BigInteger factorial(int n) {
        BigInteger result = BigInteger.valueOf(n);
        for (int i = n - 1; i > 1; --i) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    int countTrailingZeros(BigInteger number) {
        int count = 0;
        String str = number.toString();
        for (int pos = str.length() - 1; str.charAt(pos) == '0'; --pos) {
            ++count;
        }
        return count;
    }

    int deriveTrailingZeros2(int n) {
        int count = 0;
        int max = Math.min(Integer.MAX_VALUE / 5, n);
        for (int i = 5; ; i *= 5) {
            count += n / i;
            if (i >= max) {
                break;
            }
        }
        return count;
    }

    int deriveTrailingZeros(int n) {
        int count = 0;
        for (long i = 5; i <= n; i *= 5) {
            count += n / i;
        }
        return count;
    }

    //@Test
    public void play() {
        assertEquals(BigInteger.valueOf(2), factorial(2));
        //        assertEquals(6, factorial(3));
        //        assertEquals(24, factorial(4));
        //        assertEquals(120, factorial(5));
        for (int i = 6; i < 210; ++i) {
            BigInteger number = factorial(i);
            System.out.println(i + ": " + number + " ; " + countTrailingZeros(number) + " " + (i / 5));
            assertEquals(i / 5 + i / 25 + i / 125, countTrailingZeros(number));
            assertEquals(i / 5 + i / 25 + i / 125, deriveTrailingZeros(i));
        }
    }

    @Test
    public void test() {
        assertEquals(53687082, deriveTrailingZeros(214748364));
        assertEquals(536870902, deriveTrailingZeros(2147483647));
    }
}
