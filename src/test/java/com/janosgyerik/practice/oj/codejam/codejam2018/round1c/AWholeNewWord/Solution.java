package com.janosgyerik.practice.oj.codejam.codejam2018.round1c.AWholeNewWord;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        new Solution().solve(new Scanner(System.in));
    }

    void solve(Scanner scanner) {
        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            int n = scanner.nextInt();
            int[] D = new int[n];
            int[] A = new int[n];
            int[] B = new int[n];
            for (int j = 0; j < n; j++) {
                D[j] = scanner.nextInt();
                A[j] = scanner.nextInt();
                B[j] = scanner.nextInt();
            }
//            int[] pair = solve(D, A, B);
//            System.out.printf("Case #%d: %d %d\n", (i + 1), pair[0], pair[1]);
        }
    }
}
