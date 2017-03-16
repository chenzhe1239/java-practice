package com.janosgyerik.practice.oj.leetcode.medium.PacificAtlanticWaterFlow;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    // wrong answer on 82 / 113

    static Comparator<Point> pointComparator = (p1, p2) -> {
        int cmp = Integer.compare(p1.x, p2.x);
        if (cmp != 0) return cmp;
        return Integer.compare(p1.y, p2.y);
    };

    static class Point {
        final int x, y;
        final Set<Point> incoming = new TreeSet<>(pointComparator);

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private void receive(Point other) {
            incoming.add(other);
        }

        @Override
        public boolean equals(Object o) {
            Point other = (Point) o;
            return x == other.x && y == other.y;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", x, y);
        }
    }

    static class PointFactory {
        private final Map<Integer, Point> map = new HashMap<>();
        private final int rows;

        public PointFactory(int rows) {
            this.rows = rows;
        }

        Point point(int x, int y) {
            return map.computeIfAbsent(x + rows * y, k -> new Point(x, y));
        }
    }

    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return Collections.emptyList();

        PointFactory pf = new PointFactory(matrix.length);
        Point pacific = pf.point(-1, -1);
        Point atlantic = pf.point(-2, -2);

        for (int i = 0; i < matrix.length; i++) {
            pacific.receive(pf.point(i, 0));
            atlantic.receive(pf.point(i, matrix[0].length - 1));
        }

        for (int i = 0; i < matrix[0].length; i++) {
            pacific.receive(pf.point(0, i));
            atlantic.receive(pf.point(matrix.length - 1, i));
        }

        for (int r = 1; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                Point here = pf.point(r, c);
                int value = matrix[r][c];
                if (matrix[r - 1][c] <= value) pf.point(r - 1, c).receive(here);
                if (matrix[r - 1][c] >= value) here.receive(pf.point(r - 1, c));
            }
        }

        for (int c = 1; c < matrix[0].length; c++) {
            for (int r = 0; r < matrix.length; r++) {
                Point here = pf.point(r, c);
                int value = matrix[r][c];
                if (matrix[r][c - 1] <= value) pf.point(r, c - 1).receive(here);
                if (matrix[r][c - 1] >= value) here.receive(pf.point(r, c - 1));
            }
        }

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                Point here = pf.point(r, c);
                System.out.println(here + " " + here.incoming);
            }
        }

        Set<Point> rp = populate(pacific);
        Set<Point> ra = populate(atlantic);
        rp.retainAll(ra);
        return rp.stream().map(p -> new int[]{p.x, p.y}).collect(Collectors.toList());
    }

    private SortedSet<Point> populate(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);

        SortedSet<Point> incoming = new TreeSet<>(pointComparator);
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (Point pp : p.incoming) {
                if (incoming.add(pp)) {
                    q.add(pp);
                }
            }
        }
        return incoming;
    }
}
