package com.janosgyerik.practice.codility.medium;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class NonDivTest {
    @Test
    public void test() {
        assertArrayEquals(new int[]{0, 1}, findNonDiv(new int[]{3, 1}));
        assertArrayEquals(new int[]{1, 2, 1}, findNonDiv(new int[]{3, 1, 2}));
        assertArrayEquals(new int[]{1, 3, 2, 1}, findNonDiv(new int[]{3, 1, 2, 3}));
        assertArrayEquals(new int[]{2, 4, 3, 2, 0}, findNonDiv(new int[]{3, 1, 2, 3, 6}));
        assertArrayEquals(new int[]{4, 6, 5, 4, 2, 5, 5}, findNonDiv(new int[]{3, 1, 2, 3, 6, 7, 13}));
    }

    private int[] findNonDiv(int[] ints) {
        Map<Integer, Integer> nonDivMap = new HashMap<Integer, Integer>();
        int[] nonDiv = new int[ints.length];
        for (int i = 0; i < ints.length; ++i) {
            Integer nonDivCount = nonDivMap.get(ints[i]);
            if (nonDivCount != null) {
                nonDiv[i] = nonDivCount;
                continue;
            }
            for (int j = 0; j < ints.length; ++j) {
                if (i != j) {
                    if (ints[i] % ints[j] != 0) {
                        nonDiv[i]++;
                    }
                }
            }
            nonDivMap.put(ints[i], nonDiv[i]);
        }
        return nonDiv;
    }
}
