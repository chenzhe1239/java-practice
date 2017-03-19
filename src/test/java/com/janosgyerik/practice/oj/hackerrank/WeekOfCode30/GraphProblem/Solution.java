package com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.GraphProblem;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph g = Graph.fromScanner(in);
        Graph optimal = g.findOptimalSubGraph();
        System.out.println(optimal.size());
        System.out.println(optimal.ids().stream().map(x -> Integer.toString(x)).collect(Collectors.joining(" ")));
    }

    static class Graph {
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        int triangles;
        double ratio;

        static Graph fromScanner(Scanner scanner) {
            int n = scanner.nextInt();
            Graph g = new Graph();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (scanner.nextInt() == 1) {
                        g.connect(i + 1, j + 1);
                    }
                }
            }
            return g;
        }

        private void connect(int i, int j) {
            nodes.computeIfAbsent(i, k -> new HashSet<>()).add(j);
            nodes.computeIfAbsent(j, k -> new HashSet<>()).add(i);
        }

        Set<Integer> ids() {
            return nodes.keySet();
        }

        public int size() {
            return nodes.size();
        }

        public Graph findOptimalSubGraph() {
            simplify();
            if (countTriangles() == 0) {
                return new Graph();
            }

            Graph optimal = this;
            double max = ratio;
            for (int id : new ArrayList<>(ids())) {
                Set<Integer> neighbors = nodes.get(id);
                remove(id);
                Graph g2 = copy().findOptimalSubGraph();
                g2.countTriangles();
                if (g2.ratio > max) {
                    max = g2.ratio;
                    optimal = g2;
                }
                restore(id, neighbors);
            }
            return optimal;
        }

        void simplify() {
            boolean removed = true;
            while (removed) {
                removed = false;
                for (int id : new ArrayList<>(ids())) {
                    if (nodes.containsKey(id) && nodes.get(id).size() == 1) {
                        remove(id);
                        removed = true;
                    }
                }
            }
        }

        private void restore(int id, Set<Integer> neighbors) {
            neighbors.forEach(j -> connect(id, j));
        }

        private void remove(int id) {
            nodes.get(id).forEach(j -> nodes.get(j).remove(id));
            nodes.remove(id);
            nodes.entrySet().removeIf(e -> e.getValue().isEmpty());
        }

        private Graph copy() {
            Graph copy = new Graph();
            ids().forEach(id -> nodes.get(id).forEach(j -> copy.connect(id, j)));
            return copy;
        }

        int countTriangles() {
            Set<Set<Integer>> sets = new HashSet<>();
            for (int i : ids()) {
                for (int j : nodes.get(i)) {
                    for (int k : nodes.get(j)) {
                        if (nodes.get(i).contains(k)) {
                            sets.add(new HashSet<>(Arrays.asList(i, j, k)));
                        }
                    }
                }
            }
            this.triangles = sets.size();
            this.ratio = (double) triangles / size();
            return sets.size();
        }
    }
}
