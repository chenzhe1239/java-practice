package com.janosgyerik.practice.oj.codejam.codejam2018.qualification.GoGophers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        new Solution().solve(new Scanner(System.in));
    }

    void solve(Scanner scanner) {
        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            int minArea = scanner.nextInt();
            new Solver().solve(minArea, scanner);
        }
    }

    static int computeOptimalRectangleEdge(int targetSize) {
        int shorter = (int) Math.sqrt(targetSize);
        if (shorter * shorter < targetSize) {
            return shorter + 1;
        }
        return shorter;
    }

    static class Solver {
        private static final int SIZE = 1000;
        private int[][] grid = new int[SIZE + 1][SIZE + 1];
        private boolean[][] done = new boolean[SIZE + 1][SIZE + 1];

        final Map<Pos, Set<Pos>> neighbors = new HashMap<>();
        final PriorityQueue<Pos> candidates = new PriorityQueue<>(neighborCountComparator());

        private final int minX = 2;
        private final int minY = 2;
        private int maxX = 2;
        private int maxY = 2;

        private Comparator<Pos> neighborCountComparator() {
            return (a, b) -> -Integer.compare(grid[a.x][a.y], grid[b.x][b.y]);
        }

        private void solve(int targetSize, Scanner scanner) {
            init(targetSize);

            while (true) {
                Pos pos = pickPos();
                print(pos);

                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (x == 0 && y == 0) return;
                if (x == -1 && y == -1) System.exit(1);
                prepare(x, y);
            }
        }

        void init(int targetSize) {
            int longerEdge = computeOptimalRectangleEdge(targetSize);
            int shorterEdge = longerEdge;
            if (longerEdge * longerEdge > targetSize) {
                shorterEdge = longerEdge - 1;
            }

            maxX = longerEdge;
            maxY = shorterEdge;

            for (int x = minX - 1; x < maxX + 1; x++) {
                for (int y = minY - 1; y < maxY + 1; y++) {
                    initNeighbors(x, y);
                }
            }

            for (int x = minX; x < maxX; x++) {
                for (int y = minY; y < maxY; y++) {
                    candidates.add(new Pos(x, y));
                }
            }
        }

        private void initNeighbors(int x, int y) {
            Pos pos = new Pos(x, y);
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i < minX - 1 || i > maxX || j < minY - 1 || j > maxY) continue;
                    neighbors.computeIfAbsent(pos, key -> new HashSet<>()).add(new Pos(i, j));
                }
            }
            grid[x][y] = neighbors.get(pos).size();
        }

        private Pos pickPos() {
            return candidates.peek();
        }

        void prepare(int x, int y) {
            if (done[x][y]) return;
            done[x][y] = true;

            Pos pos = new Pos(x, y);

            List<Pos> origNeighbors = new ArrayList<>(neighbors.get(pos));
            for (Pos neigh : origNeighbors) {
                grid[neigh.x][neigh.y]--;
                if (candidates.contains(neigh)) {
                    candidates.remove(neigh);
                    candidates.add(neigh);
                }
            }
        }

        private void print(Pos pos) {
            System.out.printf("%d %d\n", pos.x, pos.y);
            System.out.flush();
        }

        void summary() {
            List<Map.Entry<Pos, Set<Pos>>> sorted = neighbors.entrySet().stream().sorted((a, b) -> {
                int cmpy = Integer.compare(a.getKey().y, b.getKey().y);
                if (cmpy != 0) return cmpy;
                return Integer.compare(a.getKey().x, b.getKey().x);
            }).collect(Collectors.toList());

            if (false) {
                System.out.println("Candidates");
                System.out.println("----------");
                for (Solution.Pos candidate : candidates) {
                    System.out.println(candidate);
                }
                System.out.println();

                System.out.println("Neighbors");
                System.out.println("---------");
                for (Map.Entry<Pos, Set<Pos>> e : sorted) {
                    System.out.println(e.getKey() + " -> " + e.getValue().size());
                }
                System.out.println();
            }

            int count = 0;
            for (Map.Entry<Pos, Set<Pos>> e : sorted) {
                System.out.print(grid[e.getKey().x][e.getKey().y]);
//                System.out.print(e.getValue().size());
                count++;
                if (count == (maxX - minX + 2)) {
                    System.out.println();
                    count = 0;
                }
            }
            System.out.println();
        }
    }

    static class Pos {
        final int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x &&
                y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Pos{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }
}
