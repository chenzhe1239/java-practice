package com.janosgyerik.practice.codility.easy;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ChocoByNumTest {

    private int gcd (int a, int b) {
        if (a % b == 0) return b;
        return gcd(b, a % b);
    }

    private int countEaten(int num, int skip) {
        return num / gcd(num, skip);
    }

    private int countEatenNaive(int num, int skip) {
        Set<Integer> eaten = new HashSet<Integer>();
        int count = 0;
        for (int i = 0; !eaten.contains(i); i = (i + skip) % num) {
            eaten.add(i);
            ++count;
        }
        return count;
    }

    @Test
    public void testExample() {
        assertEquals(10, countEaten(10, 1));
        assertEquals(5, countEaten(10, 2));
        assertEquals(10, countEaten(10, 3));
        assertEquals(5, countEaten(10, 4));
        assertEquals(2, countEaten(10, 5));
        assertEquals(5, countEaten(10, 6));
        assertEquals(10, countEaten(10, 7));
        assertEquals(5, countEaten(10, 8));
        assertEquals(10, countEaten(10, 9));
        assertEquals(1, countEaten(10, 10));
        assertEquals(10, countEaten(10, 11));
        assertEquals(10, countEaten(10, 17));
        assertEquals(5, countEaten(10, 12));
        assertEquals(5, countEaten(10, 304));
        assertEquals(2, countEaten(10, 355));
        assertEquals(1, countEaten(10, 350));
        assertEquals(10, countEaten(10, 21));
    }

    @Test
    public void testGreatestCommonDivisor() {
        assertEquals(1, gcd(1, 5));
        assertEquals(1, gcd(3, 1));
        assertEquals(2, gcd(6, 4));
        assertEquals(1, gcd(3, 5));
        assertEquals(1, gcd(3, 5));
        assertEquals(1, gcd(13, 5));
        assertEquals(5, gcd(15, 25));
        assertEquals(45, gcd(45, 90));
    }
}
