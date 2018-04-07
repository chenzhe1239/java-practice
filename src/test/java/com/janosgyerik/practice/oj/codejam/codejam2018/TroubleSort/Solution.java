package com.janosgyerik.practice.oj.codejam.codejam2018.TroubleSort;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) {
        new Solution().solve(new Scanner(System.in));
    }

    void solve(Scanner scanner) {
        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = scanner.nextInt();
            }
            int pos = solve(nums);
            System.out.printf("Case #%d: %s\n", (i+1), pos > -1 ? pos : "OK");
        }
    }

    int solve(int... nums) {
        List<Integer> list = IntStream.of(nums).boxed().collect(Collectors.toList());

        Collections.sort(new SubArray(list, 0));
        Collections.sort(new SubArray(list, 1));

        return findFirstUnsortedPos(list);
    }

    static class SubArray extends AbstractList<Integer> {
        private final List<Integer> list;
        private final int start;
        private final int size;

        public SubArray(List<Integer> list, int start) {
            this.list = list;
            this.start = start;
            this.size = list.size() / 2 + (start == 0 ? list.size() % 2: 0);
        }

        @Override
        public Integer get(int index) {
            return list.get(start + index * 2);
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public Integer set(int index, Integer element) {
            return list.set(start + 2 * index, element);
        }
    }

    private int findFirstUnsortedPos(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) return i - 1;
        }
        return -1;
    }
}
