package com.janosgyerik.practice.oj.leetcode.medium.ReconstructOriginalDigitsFromEnglish;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

public class Demo {
    @Test
    public void test_2() {
        int[] houses = {282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923};
        int[] heaters = {823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612};
        System.out.println(findRadius(houses, heaters));
    }

    @Test
    public void test_3() {
        int[] houses = {1,2,3,4,5,6,7,4};
        int[] heaters = {1,2,3,4,5,6,7,4};
        System.out.println(findRadius(houses, heaters));
    }

    @Test
    public void test_4() {
        String[] arr = {"10","0001","111001","1","0"};
        Arrays.sort(arr, Comparator.comparingInt(String::length));
        System.out.println(Arrays.toString(arr));
    }

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        long max = Long.MIN_VALUE;
        int x = 0;
        for (int h : houses) {
            long min = Math.abs(h - heaters[x]);
            while (x < heaters.length - 1) {
                long dx = Math.abs(h - heaters[x + 1]);
                if (dx <= min) {
                    min = dx;
                    x++;
                } else {
                    break;
                }
            }
            max = Math.max(max, min);
        }
        return (int) max;
    }

    @Test
    public void test_() {
        Solution2 solution2 = new Solution2(new int[]{1, 2, 3, 3, 3});
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
        System.out.println(solution2.pick(3));
    }
}

class Solution2 {

    private final int[][] indexes;
    private final Random random = new Random();
    private final Map<Integer, int[]> startEndCache = new HashMap<>();

    public Solution2(int[] nums) {
        indexes = new int[nums.length][];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = new int[]{nums[i], i};
        }
        Arrays.sort(indexes, Comparator.comparingInt(x -> x[0]));
    }

    public int pick(int target) {
        int[] startEnd = startEndCache.get(target);
        int start, end;
        if (startEnd == null) {
            start = Arrays.binarySearch(indexes, new int[]{target}, Comparator.comparingInt(x -> x[0]));
            end = wouldBeIndex0(target + 1);
            start = normalize(start);
            startEndCache.put(target, new int[]{start, end});
        } else {
            start = startEnd[0];
            end = startEnd[1];
        }
        return indexes[start + random.nextInt(end - start)][1];
    }

    int wouldBeIndex(int index1) {
        for (int i = index1 + 1; i < indexes.length; i++) {
            if (indexes[i][0] != indexes[index1][0]) {
                return i;
            }
        }
        return indexes.length;
    }


    int wouldBeIndex0(int target) {
        int index = Arrays.binarySearch(indexes, new int[]{target}, Comparator.comparingInt(x -> x[0]));
        if (index >= 0) {
            return normalize(index);
        }
        return -1 - index;
    }

    int normalize(int index) {
        for (int i = index; i > 0; i--) {
            if (indexes[i - 1][0] != indexes[index][0]) {
                return i;
            }
        }
        return 0;
    }
}

