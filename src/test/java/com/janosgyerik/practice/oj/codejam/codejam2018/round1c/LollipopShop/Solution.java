package com.janosgyerik.practice.oj.codejam.codejam2018.round1c.LollipopShop;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        new Solution().solve(new Scanner(System.in));
    }

    void solve(Scanner scanner) {
        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            int n = scanner.nextInt();
            int langs = scanner.nextInt();
            int[] votes = new int[langs];
            for (int j = 0; j < langs; j++) {
                votes[j] = scanner.nextInt();
            }
//            int max = solve(n, votes);
//            System.out.printf("Case #%d: %s\n", (i + 1), max);
        }
    }
}
