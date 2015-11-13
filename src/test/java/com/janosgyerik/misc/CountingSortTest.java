package com.janosgyerik.misc;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class CountingSortTest {
    private static final int MAX_AGE = 200;

    void sort(int[] arr) {
        int[] counts = new int[MAX_AGE];
        for (int item : arr) {
            ++counts[item];
        }
        for (int i = 0, item = 0; item < counts.length; ++item) {
            int count = counts[item];
            for (int k = 0; k < count; ++k) {
                arr[i++] = item;
            }
        }
    }

    private int[] newRandomArray(int num) {
        Random random = new Random(0);
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = random.nextInt(MAX_AGE);
        }
        return arr;
    }

    private void sortAndVerify(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        sort(arr);
        assertArrayEquals(copy, arr);
    }

    @Test
    public void test_empty() {
        sortAndVerify(new int[0]);
    }

    @Test
    public void test_1() {
        sortAndVerify(new int[]{1});
    }

    @Test
    public void test_1_2() {
        sortAndVerify(new int[]{1, 2});
    }

    @Test
    public void test_2_1() {
        sortAndVerify(new int[]{2, 1});
    }

    @Test
    public void test_1_2_3() {
        sortAndVerify(new int[]{1, 2, 3});
    }

    @Test
    public void test_3_2_1() {
        sortAndVerify(new int[]{3, 2, 1});
    }

    @Test
    public void test_random_10() {
        sortAndVerify(newRandomArray(10));
    }

    @Test
    public void test_random_1000() {
        sortAndVerify(newRandomArray(1000));
    }
}
