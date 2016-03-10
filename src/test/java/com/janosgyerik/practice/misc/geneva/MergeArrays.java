package com.janosgyerik.practice.misc.geneva;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MergeArrays {
    static int[] mergeArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];

        int aIndex = 0;
        int bIndex = 0;
        int index = 0;

        while (aIndex < a.length && bIndex < b.length) {
            if (a[aIndex] < b[bIndex]) {
                merged[index++] = a[aIndex++];
            } else {
                merged[index++] = b[bIndex++];
            }
        }

        while (aIndex < a.length) {
            merged[index++] = a[aIndex++];
        }

        while (bIndex < b.length) {
            merged[index++] = b[bIndex++];
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
