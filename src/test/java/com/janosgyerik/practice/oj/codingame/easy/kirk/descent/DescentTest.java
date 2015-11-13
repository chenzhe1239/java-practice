package com.janosgyerik.practice.oj.codingame.easy.kirk.descent;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DescentTest {
    @Test
    public void testGetNextTarget() {
        int length = Player.MOUNTAINS;
        for (int i = 0; i < length; ++i) {
            int[] ints = new int[length];
            ints[i] = 1;
            assertEquals(i, getNextTarget(ints));
        }
    }

    private int getNextTarget(int... heights) {
        return Player.getNextTarget(heights);
    }
}
