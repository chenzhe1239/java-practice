package com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.RangeModularQueries;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }

        List<Query> queries = new ArrayList<>(q);
        for (int a0 = 0; a0 < q; a0++) {
            int left = in.nextInt();
            int right = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            queries.add(new Query(left, right, x, y));
        }

        Solver solver = new BruteForceSolver(a);
        queries.forEach(query -> System.out.println(solver.solve(query)));
    }

    static class BruteForceSolver implements Solver {
        final int[] nums;

        BruteForceSolver(int[] nums) {
            this.nums = nums;
        }

        @Override
        public int solve(Query q) {
            int count = 0;
            for (int i = q.left; i <= q.right; i++) {
                if (nums[i] % q.x == q.y) {
                    count++;
                }
            }
            return count;
        }
    }

    static class SimpleCachingSolver implements Solver {
        private final int[] nums;
        private final Solver fallback;
        private final Cache cache;

        SimpleCachingSolver(int[] nums, List<Query> queries) {
            this.nums = nums;
            this.cache = new Cache(queries);

            fallback = new BruteForceSolver(nums);
        }

        @Override
        public int solve(Query q) {
            if (cache.contains(q)) {
                return cache.get(q);
            }
            return fallback.solve(q);
        }

        class Cache {
            Map<Integer, int[][]> prefixSums = new HashMap<>();

            public Cache(List<Query> queries) {
                Set<Integer> set = new HashSet<>();
                queries.forEach(q -> {
                    if (!set.add(q.x)) {
                        if (!prefixSums.containsKey(q.x)) {
                            prefixSums.put(q.x, createPrefixSums(q.x));
                        }
                    }
                });
            }

            private int[][] createPrefixSums(int x) {
                int[][] sums = new int[x][nums.length + 1];
                for (int i = 0; i < nums.length; i++) {
                    sums[nums[i] % x][i + 1] = 1;
                }
                for (int[] psums : sums) {
                    for (int i = 1; i < psums.length; i++) {
                        psums[i] += psums[i - 1];
                    }
                }
                return sums;
            }

            public int get(Query q) {
                int[] sums = prefixSums.get(q.x)[q.y];
                return sums[q.right + 1] - sums[q.left];
            }

            public boolean contains(Query q) {
                return prefixSums.containsKey(q.x);
            }
        }
    }

    interface Solver {
        int solve(Query q);
    }

    static class Query {
        final int left, right, x, y;

        Query(int left, int right, int x, int y) {
            this.left = left;
            this.right = right;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "left=" + left +
                    ", right=" + right +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
