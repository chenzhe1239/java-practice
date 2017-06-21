package com.janosgyerik.practice.oj.codejam.codejam2017.round1a.AlphabetCake;

import com.janosgyerik.practice.oj.codejam.codejam2017.common.*;

import java.io.IOException;
import java.util.*;

public class Ratatouille implements Problem {
    @Override
    public Inputs inputs(Scanner scanner) {
        Inputs inputs = new Inputs();
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int p = scanner.nextInt();
            int[] recipe = new int[n];
            for (int j = 0; j < n; j++) {
                recipe[j] = scanner.nextInt();
            }
            int[][] packages = new int[n][p];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < p; k++) {
                    packages[j][k] = scanner.nextInt();
                }
            }
            Input input = new RatatouilleInput(n, p, recipe, packages);
            inputs.add(input);
        }
        return inputs;
    }

    @Override
    public Solver solver(Inputs inputs) {
        return new RatatouilleSolver();
    }

    static class RatatouilleInput implements Input {
        private final int n;
        private final int p;
        private final int[] recipe;
        private final int[][] packages;

        public RatatouilleInput(int n, int p, int[] recipe, int[][] packages) {
            this.n = n;
            this.p = p;
            this.recipe = recipe;
            this.packages = packages;
        }
    }

    static class Package {
        final int id;
        final int lower;
        final int upper;

        Package(int id, int recipe, int amount) {
            this.id = id;
            this.lower = (int)(amount * 0.9 / recipe);
            this.upper = (int)(amount * 1.1 / recipe);
        }
    }

    static class RatatouilleSolver implements Solver {
        @Override
        public Answer solve(Input input0) {
            RatatouilleInput input = (RatatouilleInput) input0;

            PriorityQueue<Package> heap = new PriorityQueue<>((p1, p2) -> {
               int cmp = Integer.compare(p1.lower, p2.lower);
               if (cmp != 0) return cmp;
               return Integer.compare(p1.upper, p2.upper);
            });

            for (int i = 0; i < input.n; i++) {
                for (int j = 0; j < input.p; j++) {
                    heap.add(new Package(i, input.recipe[i], input.packages[i][j]));
                }
            }

            Map<Integer, Package> packages = new HashMap<>();
            int count = 0;

            while (!heap.isEmpty()) {
                Package pack = heap.poll();
                packages.put(pack.id, pack);
                if (packages.size() == input.n) {
                    int lowestUpper = Integer.MAX_VALUE;
                    int highestLower = Integer.MIN_VALUE;
                    for (Package p2 : packages.values()) {
                        lowestUpper = Math.min(lowestUpper, p2.upper);
                        highestLower = Math.max(highestLower, p2.lower);
                    }
                    if (highestLower < lowestUpper) {
                        count++;
                        packages.clear();
                    }
                }
            }

            int result = count;

            return () -> String.valueOf(result);
        }
    }

    public static void main(String[] args) throws IOException {
        Runner.create(new Ratatouille()).run("dummy.in");
    }
}
