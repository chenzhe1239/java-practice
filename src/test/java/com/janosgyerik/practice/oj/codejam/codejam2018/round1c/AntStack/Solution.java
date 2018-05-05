package com.janosgyerik.practice.oj.codejam.codejam2018.round1c.AntStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        new Solution().solve(new Scanner(System.in));
    }

    void solve(Scanner scanner) {
        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            int N = scanner.nextInt();
            int[] weights = new int[N];
            for (int j = 0; j < N; j++) {
                weights[j] = scanner.nextInt();
            }
            int max = solve(weights);
            System.out.printf("Case #%d: %s\n", (i + 1), max);
        }
    }

    int solve(int... weights) {
        return minWeightsPerLength(weights).size() - 1;
    }

    List<Integer> minWeightsPerLength(int... weights) {
        List<Integer> minWeightsPerLength = new ArrayList<>();
        minWeightsPerLength.add(0);

        for (int w : weights) {
            int lastWeight = minWeightsPerLength.get(minWeightsPerLength.size() - 1);
            if (lastWeight <= 6 * w) {
                minWeightsPerLength.add(lastWeight + w);
            }

            int len = minWeightsPerLength.size() - 1;
            for (; len > 0; len--) {
                if (minWeightsPerLength.get(len - 1) <= 6 * w) {
                    if (minWeightsPerLength.get(len - 1) + w < minWeightsPerLength.get(len)) {
                        minWeightsPerLength.set(len, minWeightsPerLength.get(len - 1) + w);
                    }
                }
            }
        }
        return minWeightsPerLength;
    }
}
