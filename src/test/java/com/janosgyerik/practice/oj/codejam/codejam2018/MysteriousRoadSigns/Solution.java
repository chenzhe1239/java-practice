package com.janosgyerik.practice.oj.codejam.codejam2018.MysteriousRoadSigns;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

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
            int[] pair = solve(D, A, B);
            System.out.printf("Case #%d: %d %d\n", (i + 1), pair[0], pair[1]);
        }
    }

    static int[] solve(int[] D, int[] A, int[] B) {
        int[] left = IntStream.range(0, D.length).map(i -> D[i] + A[i]).toArray();
        int[] right = IntStream.range(0, D.length).map(i -> D[i] - B[i]).toArray();
        Map<Integer, Integer> counts1 = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            check(left, right, counts1, q);
        }

        q.add(0);
        Map<Integer, Integer> counts2 = new HashMap<>();
        while (!q.isEmpty()) {
            check(right, left, counts2, q);
        }

        int longest1 = counts1.keySet().stream().max(Integer::compare).get();
        int longest2 = counts2.keySet().stream().max(Integer::compare).get();
        if (longest1 > longest2) {
            return new int[]{longest1, counts1.get(longest1)};
        }
        return new int[]{longest2, counts2.get(longest2)};
    }

    private static void check(int[] left, int[] right, Map<Integer, Integer> counts, Queue<Integer> q) {
        int start = q.poll();
        int value = left[start];
        boolean altIsSet = false;
        int alt = 0;

        int length = 1;
        for (int i = start + 1; i < left.length; i++) {
            if (left[i] == value) {
                length++;
                continue;
            }
            q.add(i);

            if (!altIsSet) {
                altIsSet = true;
                alt = right[i];
                length++;
                continue;
            }
            if (right[i] == alt) {
                length++;
                continue;
            }
            break;
        }
        counts.merge(length, 1, Integer::sum);
    }

}
