package com.janosgyerik.practice.oj.codejam.codejam2018.TroubleSort;

import java.util.Arrays;
import java.util.Scanner;

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
        int[] even = extractSubArray(nums, 0);
        int[] odd = extractSubArray(nums, 1);
        Arrays.sort(even);
        Arrays.sort(odd);
        return findFirstUnsortedPos(even, odd);
    }

    private int[] extractSubArray(int[] nums, int start) {
        int[] sub = new int[nums.length / 2 + nums.length % 2];
        for (int i = start; i < nums.length; i += 2) {
            sub[i / 2] = nums[i];
        }
        if (nums.length % 2 == 1 && start == 1) {
            sub[sub.length - 1] = Integer.MAX_VALUE;
        }
        return sub;
    }

    private int[] merge(int[] even, int[] odd) {
        int[] merged = new int[even.length * 2];
        for (int i = 0; i < even.length; i++) {
            merged[2 * i] = even[i];
            merged[2 * i + 1] = odd[i];
        }
        return merged;
    }

    private int findFirstUnsortedPos(int[] even, int[] odd) {
        int[] merged = merge(even, odd);
        for (int i = 1; i < merged.length; i++) {
            if (merged[i - 1] > merged[i]) return i - 1;
        }
        return -1;
    }
}
