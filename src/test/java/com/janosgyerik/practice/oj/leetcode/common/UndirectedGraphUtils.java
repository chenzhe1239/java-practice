package com.janosgyerik.practice.oj.leetcode.common;

import java.util.*;

public class UndirectedGraphUtils {

    private UndirectedGraphUtils() {
        // utility class, forbidden constructor
    }

    public UndirectedGraphNode deserialize(String s) {
        if (s.length() == 2) {
            return null;
        }
        Map<String, UndirectedGraphNode> nodes = new LinkedHashMap<>();
        String[] parts = s.substring(1, s.length() - 1).split("#");
        for (String part : parts) {
            String[] nodeParts = part.split(",");
            UndirectedGraphNode node = getOrCreateNode(nodes, nodeParts[0]);
            for (int i = 1; i < nodeParts.length; ++i) {
                UndirectedGraphNode neighbor = getOrCreateNode(nodes, nodeParts[i]);
                node.neighbors.add(neighbor);
            }
        }
        return nodes.values().iterator().next();
    }

    UndirectedGraphNode getOrCreateNode(Map<String, UndirectedGraphNode> nodes, String label) {
        UndirectedGraphNode node = nodes.get(label);
        if (node == null) {
            node = new UndirectedGraphNode(Integer.parseInt(label));
            nodes.put(label, node);
        }
        return node;
    }

    String serialize(UndirectedGraphNode pivot) {
        StringBuilder builder = new StringBuilder("{");

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<Integer> done = new HashSet<>();
        if (pivot != null) {
            queue.add(pivot);
            done.add(pivot.label);
        }
        while (!queue.isEmpty()) {
            UndirectedGraphNode node = queue.poll();
            builder.append(node.label);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                builder.append(",").append(neighbor.label);
                if (!done.contains(neighbor.label)) {
                    queue.add(neighbor);
                    done.add(neighbor.label);
                }
            }
            if (!queue.isEmpty()) {
                builder.append("#");
            }
        }

        builder.append("}");
        return builder.toString();
    }
}
