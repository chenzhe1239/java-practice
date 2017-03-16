package com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.Poles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    // TLE for 3, 5, 7, 9, 10, 11 -> score = 16.67, #387 as of ~12:30

    // TLE for 5, 10, 11 -> score = 27.78, #832 as of ~21:04
    // same with prefix sum optimization,  #900 as of ~22:31

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
        private final int id, altitude, weight;

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
