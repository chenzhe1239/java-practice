package com.janosgyerik.practice.codility.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinPerimRect {
    @Test
    public void testSample() {
        assertEquals(22, minPerimRect(30));
        assertEquals(24, minPerimRect(36));
    }

    @Test
    public void testSample2() {
        assertEquals(204, minPerimRect(101));
        assertEquals(1238, minPerimRect(1234));
        assertEquals(8552, minPerimRect(4564320));
        assertEquals(126500, minPerimRect(1000000000));
    }

    private int minPerimRect(int area) {
        int x = (int) (area / Math.sqrt(area));
        if (x * (area / x) == area) {
            return 2 * (x + area / x);
        }
        int y = x - 1;
        for (; y * (area / y) != area; --y);
        return 2 * (y + area / y);
    }
}
