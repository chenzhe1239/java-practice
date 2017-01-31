package com.janosgyerik.practice.oj.leetcode.medium.ReconstructItinerary;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static final String START = "JFK";

    static class Graph {
        final Map<String, List<String>> neighbors = new HashMap<>();

        public void add(String from, String to) {
            List<String> links = neighbors.computeIfAbsent(from, k -> new ArrayList<>());
            links.add(to);
            Collections.sort(links);
        }

        public void remove(String from, String to) {
            List<String> targets = neighbors.get(from);
            if (targets.size() == 1) {
                neighbors.remove(from);
            } else {
                targets.remove(to);
            }
        }

        public boolean isEmpty() {
            return neighbors.isEmpty();
        }

        public List<String> get(String from) {
            return neighbors.get(from);
        }

        private static Graph fromTickets(String[][] tickets) {
            Graph graph = new Graph();
            for (String[] ticket : tickets) {
                graph.add(ticket[0], ticket[1]);
            }
            return graph;
        }
    }

    public List<String> findItinerary(String[][] tickets) {
        Graph graph = Graph.fromTickets(tickets);
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push(START);
        findItinerary(graph, START, stack);

        List<String> plan = stack.stream().collect(Collectors.toList());
        Collections.reverse(plan);
        return plan;
    }

    private boolean findItinerary(Graph graph, String from, ArrayDeque<String> stack) {
        if (graph.isEmpty()) {
            return true;
        }
        List<String> targets = graph.get(from);
        if (targets == null) {
            return false;
        }
        List<String> copy = new ArrayList<>(targets);
        for (String to : copy) {
            graph.remove(from, to);
            stack.push(to);
            if (findItinerary(graph, to, stack)) {
                return true;
            }
            stack.poll();
            graph.add(from, to);
        }
        return false;
    }
}
