package com.janosgyerik.practice.oj.codejam.codejam2018.RoundingError;

import java.util.PriorityQueue;
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
            int langs = scanner.nextInt();
            int[] votes = new int[langs];
            for (int j = 0; j < langs; j++) {
                votes[j] = scanner.nextInt();
            }
            int max = solve(n, votes);
            System.out.printf("Case #%d: %s\n", (i + 1), max);
        }
    }

    int solve(int total, int... votes) {
        int minVotesToRoundUp = minVotesToRoundUp(total);

        if (minVotesToRoundUp == 0) return 100;

        int missing = total - IntStream.of(votes).sum();
        int[] rounded = rounded(total, votes);
        int sum = IntStream.of(rounded).sum();

        if (minVotesToRoundUp == 1) {
            return sum + missing * rounded(total, 1);
        }

        PriorityQueue<Integer> groups = new PriorityQueue<>();
        IntStream.of(votes).forEach(v -> {
            int min2 = minVotesToRoundUp(total, v, minVotesToRoundUp);
            if (0 < min2 && min2 < minVotesToRoundUp) {
                groups.add(min2);
            }
        });

        while (!groups.isEmpty() && missing > 0) {
            int needed = groups.poll();
            if (needed > missing) break;
            sum += rounded(total, needed) + 1;
            missing -= needed;
        }

        sum += rounded(total, minVotesToRoundUp) * (missing / minVotesToRoundUp);
        sum += rounded(total, missing % minVotesToRoundUp);

        return sum;
    }

    private int[] rounded(int total, int[] votes) {
        return IntStream.of(votes).map(v -> rounded(total, v)).toArray();
    }

    static int rounded(int total, int vote) {
        float div = (float) vote / total;
        int rem = (int) (div * 10000) % 100;
        return (int) (div * 100) + (rem >= 50 ? 1 : 0);
    }

    static int minVotesToRoundUp(int total) {
        for (int i = 1; i < total; i++) {
            float div = (float) i / total;
            int rem = (int) (div * 10000) % 100;
            if (rem == 0) return 0;
            if (rem >= 50) return i;
        }
        throw new IllegalStateException("should have found an optimal point to round up");
    }

    static int minVotesToRoundUp(int total, int v, int minVotesToRoundUp) {
        for (int i = 0; i < minVotesToRoundUp; i++) {
            float div = (float) (v + i) / total;
            int rem = (int) (div * 10000) % 100;
            if (rem >= 50) return i;
        }
        return minVotesToRoundUp;
    }
}
