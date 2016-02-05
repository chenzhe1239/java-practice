package com.janosgyerik.practice.oj.hackerrank.CutTheTree;

import java.util.*;

public class Solution {
    public static class Input {
        final List<Link> links;
        final Map<Integer, Integer> nodes;

        Input(List<Link> links, Map<Integer, Integer> nodes) {
            this.links = links;
            this.nodes = nodes;
        }

        static Input fromString(String text) {
            return fromScanner(new Scanner(text));
        }

        static Input fromScanner(Scanner scanner) {
            int count = scanner.nextInt();

            Map<Integer, Integer> nodes = new HashMap<>();
            for (int i = 1; i <= count; ++i) {
                nodes.put(i, scanner.nextInt());
            }

            List<Link> links = new ArrayList<>();
            while (scanner.hasNext()) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                links.add(new Link(from, to));
            }
            return new Input(links, nodes);
        }
    }

    static class Link {
        final int a, b;

        Link(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static class Tree {
        final Map<Integer, Integer> nodes;
        final Map<Integer, Set<Integer>> neighborMap = new HashMap<>();

        Tree(Map<Integer, Integer> nodes, List<Link> links) {
            this.nodes = nodes;
            for (Link link : links) {
                addLink(link);
            }
        }

        private void addLink(Link link) {
            getNeighbors(link.a).add(link.b);
            getNeighbors(link.b).add(link.a);
        }

        private Set<Integer> getNeighbors(int id) {
            Set<Integer> neighbors = neighborMap.get(id);
            if (neighbors == null) {
                neighbors = new HashSet<>();
                neighborMap.put(id, neighbors);
            }
            return neighbors;
        }

        public int calculate(int a, int b) {
            int total = nodes.get(b);
            for (int neighbor : getNeighbors(b)) {
                if (neighbor != a) {
                    total += calculate(b, neighbor);
                }
            }
            return total;
        }

        public int calculate() {
            int a = nodes.keySet().iterator().next();
            int b = neighborMap.get(a).iterator().next();
            return calculate(a, b) + calculate(b, a);
        }
    }

    public static void main(String[] args) {
        Input input = Input.fromScanner(new Scanner(System.in));
        Solution solution = new Solution();
        System.out.println(solution.solve(input));
    }

    public int solve(Input input) {
        Tree tree = new Tree(input.nodes, input.links);

        int total = tree.calculate();
        int minDiff = Integer.MAX_VALUE;

        for (Link link : input.links) {
//            int limit = (total + minDiff) / 2;
            int sub = tree.calculate(link.a, link.b);
            int diff = Math.abs(total - 2 * sub);
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }
}