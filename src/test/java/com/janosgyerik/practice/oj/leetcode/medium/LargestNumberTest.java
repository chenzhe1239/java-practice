package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class LargestNumberTest {
    static int countDigits(int num) {
        return 1 + (int) Math.log10(num);
    }

    private static class FuzzyDigitComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            String s1 = "" + o1 + o2;
            String s2 = "" + o2 + o1;
            return -s1.compareTo(s2);
        }
    }

    public String largestNumber(int[] num) {
        List<Integer> numbers = new ArrayList<>(num.length);
        for (int item : num) {
            numbers.add(item);
        }
        Collections.sort(numbers, new FuzzyDigitComparator());
        StringBuilder builder = new StringBuilder();
        for (int item : numbers) {
            builder.append(item);
        }
        String result = builder.toString();
        return result.startsWith("0") ? "0" : result;
    }

    @Test
    public void test_3_30_34_5_9() {
        assertEquals("9534330", largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    @Test
    public void test_121_12() {
        assertEquals("12121", largestNumber(new int[]{121, 12}));
    }

    @Test
    public void test_12_121() {
        assertEquals("12121", largestNumber(new int[]{12, 121}));
    }

    @Test
    public void test_0_0() {
        assertEquals("0", largestNumber(new int[]{0, 0}));
    }

    @Test
    public void testComparator_3_30_34_5_9() {
        assertEquals(Arrays.asList(9, 5, 34, 3, 30), sortByDigits(3, 30, 34, 5, 9));
    }

    @Test
    public void testComparator_9_30() {
        assertEquals(Arrays.asList(9, 30), sortByDigits(9, 30));
    }

    @Test
    public void testComparator_30_9() {
        assertEquals(Arrays.asList(9, 30), sortByDigits(30, 9));
    }

    @Test
    public void testComparator_90_3() {
        assertEquals(Arrays.asList(90, 3), sortByDigits(90, 3));
    }

    @Test
    public void testComparator_90_9() {
        assertEquals(Arrays.asList(9, 90), sortByDigits(90, 9));
    }

    @Test
    public void testComparator_12_121() {
        assertEquals(Arrays.asList(12, 121), sortByDigits(12, 121));
    }

    private List<Integer> sortByDigits(int... nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list, new FuzzyDigitComparator());
        return list;
    }

    @Test
    public void testCountDigits_1() {
        assertEquals(1, countDigits(1));
    }

    @Test
    public void testCountDigits_12() {
        assertEquals(2, countDigits(12));
    }

    @Test
    public void testCountDigits_123() {
        assertEquals(3, countDigits(123));
    }
}
