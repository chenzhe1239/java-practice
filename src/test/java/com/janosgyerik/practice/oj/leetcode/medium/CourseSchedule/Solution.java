package com.janosgyerik.practice.oj.leetcode.medium.CourseSchedule;

import java.util.*;

public class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph graph = createGraph(numCourses, prerequisites);
        return canFinish(graph);
    }

    private boolean canFinish(Graph graph) {
        while (graph.trim());
        return graph.isEmpty();
    }

    private Graph createGraph(int num, int[][] edges) {
        Graph graph = new Graph(num);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        return graph;
    }

    private static class Graph {
        Map<Integer, Set<Integer>> incoming = new HashMap<>();
        Map<Integer, Set<Integer>> outgoing = new HashMap<>();

        public Graph(int num) {
            for (int i = 0; i < num; ++i) {
                incoming.put(i, new HashSet<>());
                outgoing.put(i, new HashSet<>());
            }
        }

        public void addEdge(int from, int to) {
            outgoing.get(from).add(to);
            incoming.get(to).add(from);
        }

        public boolean trim() {
            boolean anyRemoved = false;
            for (Map.Entry<Integer, Set<Integer>> entry : new ArrayList<>(incoming.entrySet())) {
                if (entry.getValue().isEmpty()) {
                    remove(entry.getKey());
                    anyRemoved = true;
                }
            }
            return anyRemoved;
        }

        public void remove(Integer node) {
            if (outgoing.containsKey(node)) {
                for (Integer neighbor : outgoing.get(node)) {
                    incoming.get(neighbor).remove(node);
                    if (incoming.get(neighbor).isEmpty()) {
                        remove(neighbor);
                    }
                }
                outgoing.remove(node);
            }
            incoming.remove(node);
        }

        public boolean isEmpty() {
            return incoming.isEmpty();
        }
    }
}
