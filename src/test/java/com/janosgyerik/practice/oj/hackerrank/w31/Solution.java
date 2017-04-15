package com.janosgyerik.practice.oj.hackerrank.w31;

import java.util.*;

public class Solution {
    static class Rational implements Comparable<Rational> {
        final int num;
        final int denom;
        final double weight;

        Rational(int num, int denom) {
            this.num = num;
            this.denom = denom;
            this.weight = (double) num / denom;
        }

        Rational reduced() {
            int gcd = gcd(num, denom);
            return new Rational(num / gcd, denom / gcd);
        }

        static int gcd(int num, int denom) {
            int small, big;
            if (num < denom) {
                small = num;
                big = denom;
            } else {
                small = denom;
                big = num;
            }
            if (small == 0) return big;
            return gcd(small, big % small);
        }

        @Override
        public int compareTo(Rational o) {
            return Double.compare(weight, o.weight);
        }

        @Override
        public String toString() {
            return num + "/" + denom;
        }
    }

    static class Graph {
        Map<Integer, List<Edge>> map = new HashMap<>();

        void connect(int from, int to, Rational weight) {
            Edge edge = new Edge(from, to, weight);
            map.computeIfAbsent(from, k -> new ArrayList<>()).add(edge);
        }

        Rational findMax() {
            PriorityQueue<Edge> q = new PriorityQueue<>((o1, o2) -> o2.weight.compareTo(o1.weight));
            map.values().forEach(q::addAll);

            Set<Integer> visited = new HashSet<>();

            Edge first = q.poll();
            Rational result = first.weight;
            visited.add(first.from);
            visited.add(first.to);

            while (!q.isEmpty()) {
                Edge edge = q.poll();
                if (visited.contains(edge.from) && visited.contains(edge.to)) {
                    continue;
                }
                List<Edge> skipped = new ArrayList<>();
                while (!visited.contains(edge.from) && !visited.contains(edge.to)) {
                    skipped.add(edge);
                    do {
                        edge = q.poll();
                    } while (visited.contains(edge.from) && visited.contains(edge.to));
                }
                result = new Rational(result.num + edge.weight.num, result.denom + edge.weight.denom);
                visited.add(edge.from);
                visited.add(edge.to);

                q.addAll(skipped);
            }

            return result.reduced();
        }
    }

    static class Edge {
        final int from, to;
        final Rational weight;

        Edge(int from, int to, Rational weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        Graph g = new Graph();
        for(int a0 = 0; a0 < m; a0++){
            int u = in.nextInt();
            int v = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            g.connect(u, v, new Rational(a, b));
        }

        System.out.println(g.findMax());
    }
}
