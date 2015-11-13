package com.janosgyerik.practice.oj.chef;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MemoryTestForSoldiersTest {
    private int[] solution(int num, int... positions) {
        int[] soldiers = new int[num];
        int max = 0;
        int min = num - 1;
        for (int pos : positions) {
            min = Math.min(min, pos);
            max = Math.max(max, pos);
        }
        for (int i = 0; i < soldiers.length; ++i) {
            soldiers[i] = Math.max(Math.abs(i - max), Math.abs(i - min));
        }
        return soldiers;
    }

    private int[] solution_bruteforce(int soldiers, int... positions) {
        int[] result = new int[soldiers];
        for (int pos : positions) {
            for (int i = 0; i < result.length; ++i) {
                result[i] = Math.max(result[i], Math.abs(pos - i));
            }
        }
        return result;
    }

    @Test
    public void testBasic1() {
        assertArrayEquals(new int[]{1, 0, 1, 2}, solution(4, 1));
    }

    @Test
    public void testBasic2() {
        assertArrayEquals(new int[]{3, 2, 1, 1, 2, 3}, solution(6, 2, 3));
    }
}
