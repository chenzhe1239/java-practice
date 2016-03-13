package com.janosgyerik.practice.misc.geneva;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MergeArrays {
    static int[] mergeArrays(int[] first, int[] second) {
        int[] merged = new int[first.length + second.length];

        int firstIndex = 0;
        int secondIndex = 0;
        int index = 0;

        while (firstIndex < first.length && secondIndex < second.length) {
            if (first[firstIndex] < second[secondIndex]) {
                merged[index++] = first[firstIndex++];
            } else {
                merged[index++] = second[secondIndex++];
            }
        }

        while (firstIndex < first.length) {
            merged[index++] = first[firstIndex++];
        }

        while (secondIndex < second.length) {
            merged[index++] = second[secondIndex++];
        }

        return merged;
    }

    @Test
    public void test_example_1() {
        assertEquals(
                Arrays.toString(new int[]{0, 1, 2, 2, 3, 4, 4, 5, 9, 9}),
                Arrays.toString(mergeArrays(new int[]{2, 4, 5, 9, 9}, new int[]{0, 1, 2, 3, 4}))
        );
    }
}
