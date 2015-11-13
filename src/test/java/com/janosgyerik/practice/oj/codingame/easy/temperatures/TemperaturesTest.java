package com.janosgyerik.practice.oj.codingame.easy.temperatures;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TemperaturesTest {
    @Test
    public void testNoInput() {
        assertEquals(0, solve(new Scanner("0")));
    }

    @Test
    public void testSingleInput() {
        for (int i = -5; i < 5; ++i) {
            assertEquals(i, solve(new Scanner("1 " + i)));
        }
    }

    @Test
    public void testPreferPositive() {
        assertEquals(5, solve(new Scanner("2 -5 5")));
        assertEquals(5, solve(new Scanner("2 5 -5")));
        assertEquals(5, solve(new Scanner("3 -5 5 -5")));
    }

    @Test
    public void testNegativeWinsOnlyIfStrictlyCloser() {
        assertEquals(-4, solve(new Scanner("2 -4 5")));
    }

    @Test
    public void testZeroIsClosest() {
        assertEquals(0, solve(new Scanner("3 -4 5 0")));
        assertEquals(0, solve(new Scanner("3 0 -4 5")));
    }

    @Test
    public void testIgnoreExcessInput() {
        assertEquals(5, solve(new Scanner("1 5 0")));
    }

    @Test
    public void testAllNegative() {
        assertEquals(-3, solve(new Scanner("3 -4 -3 -5")));
    }

    private int solve(Scanner scanner) {
        return Temperatures.findClosest(scanner);
    }
}
