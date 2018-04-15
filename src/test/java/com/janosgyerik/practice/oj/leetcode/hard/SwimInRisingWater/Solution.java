package com.janosgyerik.practice.oj.leetcode.hard.SwimInRisingWater;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {
    public int swimInWater(int[][] grid) {
        Grid g = new Grid(grid);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(g::value));
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        pq.add(0);
        int lowest = 0;

        while (!pq.isEmpty()) {
            int current = pq.poll();
            lowest = Math.max(lowest, g.value(current));
            if (current == g.target) break;
            for (Integer neighbor : g.neighbors(current)) {
                if (visited.add(neighbor)) {
                    pq.add(neighbor);
                }
            }
        }
        return lowest;
    }

    static class Grid {
        private final int[][] grid;
        private final int width;
        private final int height;
        private final int target;

        public Grid(int[][] grid) {
            this.grid = grid;
            this.width = grid[0].length;
            this.height = grid.length;
            this.target = width * height - 1;
        }

        public int value(int id) {
            return grid[row(id)][col(id)];
        }

        private int row(int id) {
            return id / width;
        }

        private int col(int id) {
            return id % width;
        }

        private int id(int row, int col) {
            return row * width + col;
        }

        public Collection<Integer> neighbors(int id) {
            Collection<Integer> neighbors = new ArrayList<>();
            int row = row(id);
            int col = col(id);
            if (row > 0) {
                neighbors.add(id(row - 1, col));
            }
            if (row < height - 1) {
                neighbors.add(id(row + 1, col));
            }
            if (col > 0) {
                neighbors.add(id(row, col - 1));
            }
            if (col < width - 1) {
                neighbors.add(id(row, col + 1));
            }
            return neighbors;
        }
    }
}
