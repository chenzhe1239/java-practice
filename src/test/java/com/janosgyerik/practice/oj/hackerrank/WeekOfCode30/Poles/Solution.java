package com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.Poles;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    // TLE for 3, 5, 7, 9, 10, 11 -> score = 16.67, #387 as of ~12:30

    // TLE for 5, 10, 11 -> score = 27.78, #832 as of ~21:04
    // same with prefix sum optimization,  #900 as of ~22:31

    /*
    R solution, TLE for 11 -> score = 41.67, #490 as of ~23:45

f <- file('stdin')
open(f)

numbers <- function(line) {
    as.numeric(unlist(strsplit(line, ' ')))
}
read.line.as.numbers <- function() {
    numbers(readLines(f, n=1, warn=F))
}

first <- read.line.as.numbers()
n <- first[1]
k <- first[2]

alt <- c()
wt <- c()
for (i in 1:n) {
    v <- read.line.as.numbers()
    alt <- c(alt, v[1])
    wt <- c(wt, v[2])
}
alt <- rev(alt)
wt <- rev(wt)

answer <- function() {
    C <- matrix(NA, nrow=k, ncol=n)
    C[1,] <- sapply(seq_len(n), function(i) sum(wt[1:i] * (alt[1:i] - alt[i])))
    if (k < 2) {
        return(C[1,n])
    }
    for (cc in 2:k) {
        for (j in cc:n) {
            C[cc,j] <- min(sapply((cc-1):(j-1), function(q) C[cc-1,q] + sum(wt[(q+1):j] * (alt[(q+1):j] - alt[j]))))
        }
    }
    C[k,n]
}

write(answer(), stdout())

     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        List<Pole> poles = new ArrayList<>(n);
        for (int id = 0; id < n; id++) {
            poles.add(new Pole(id, in.nextInt(), in.nextInt()));
        }

        System.out.println(new Allocation(poles).computeMinCost(k));
    }

    static class Pole {
        final int id, altitude, weight;

        Pole(int id, int altitude, int weight) {
            this.id = id;
            this.altitude = altitude;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("id=%s alt=%s w=%s", id, altitude, weight);
        }
    }

    static class Allocation {
        private final List<Pole> poles;
        private final Set<Integer> available;
        private final int[] psums;

        public Allocation(List<Pole> poles) {
            this.poles = poles;
            this.available = IntStream.range(1, poles.size()).boxed().collect(Collectors.toSet());
            this.psums = computePrefixSums(poles);
        }

        private int[] computePrefixSums(List<Pole> poles) {
            int[] psums = new int[poles.size() + 1];
            for (int i = 1; i <= poles.size(); i++) {
                psums[i] = psums[i - 1] + poles.get(i - 1).weight;
            }
            return psums;
        }

        int computeCost() {
            int base = 0;
            int cost = 0;
            for (Pole pole : poles) {
                if (!available.contains(pole.id)) {
                    base = pole.altitude;
                } else {
                    cost += (pole.altitude - base) * pole.weight;
                }
            }
            return cost;
        }

        public int computeMinCost(int k) {
            List<Pole> copy = new ArrayList<>(poles);
            Collections.reverse(copy);
            int[] alt = copy.stream().mapToInt(p -> p.altitude).toArray();
            int[] wt = copy.stream().mapToInt(p -> p.weight).toArray();
            int[] sums = computePrefixSums(copy);

            int[][] dp = new int[k][alt.length];
            for (int i = 1; i < alt.length; i++) {
                dp[0][i] = dp[0][i - 1] + (alt[i - 1] - alt[i]) * sums[i];
//                for (int j = 0; j < i; j++) {
//                    dp[0][i] += wt[j] * (alt[j] - alt[i]);
//                }
            }
            for (int cc = 1; cc < k; cc++) {
                for (int j = cc; j < alt.length; j++) {
                    int min = Integer.MAX_VALUE;
                    for (int q = cc - 1; q < j; q++) {
                        int cost = dp[cc - 1][q];
//                        if (q > 0) cost += dp[cc][q - 1];
//                        cost += (alt[q] - alt[q + 1]) * (sums[j] - sums[q]);
                        for (int qq = q + 1; qq < j; qq++) {
                            cost += wt[qq] * (alt[qq] - alt[j]);
                        }
                        min = Math.min(min, cost);
                    }
                    dp[cc][j] = min;
                }
            }
//            System.out.println(Arrays.toString(dp[1]));
            // [0, 0, 2, 28, 152, 216]

            return dp[k - 1][alt.length - 1];
        }

        public int computeMinCost2(int k) {
            int cost = 0;
            int base = poles.get(0).altitude;
            for (int i = 1; i < poles.size(); i++) {
                Pole pole = poles.get(i);
                cost += (pole.altitude - base) * pole.weight;
            }
            return computeMinCost(k, 1, cost);
        }

        private int computeMinCost(int k, int start, int cost) {
            if (k == 1) {
                return cost;
            }

            int min = Integer.MAX_VALUE;
            int base = poles.get(start - 1).altitude;
            for (int i = start; i < poles.size() - k + 2; i++) {
                Pole pole = poles.get(i);
                available.remove(pole.id);
                min = Math.min(min, computeMinCost(k - 1, i + 1,
                        cost - (pole.altitude - base) * (psums[psums.length - 1] - psums[i])));
                available.add(pole.id);
            }
            return min;
        }
    }
}
