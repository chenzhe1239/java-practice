package com.janosgyerik.practice.oj.leetcode.hard.BurstBalloons;

import java.util.HashMap;
import java.util.Map;

// fails with TLE on test case 34 of 70
public class Solution {
    static class Balloons {
        static class Node<T> {
            Node<T> prev, next;
            final T value;

            Node(T value) {
                this.value = value;
            }
        }

        public int findMax(int[] nums) {
            Node<Integer> dummy = new Node<>(1);
            Node<Integer> prev = dummy;
            for (int num : nums) {
                if (num == 0) continue;
                prev.next = new Node<>(num);
                prev.next.prev = prev;
                prev = prev.next;
            }
            return findMax(dummy);
        }

        private final Map<String, Integer> solutions = new HashMap<>();

        private String toString(Node<Integer> dummy) {
            StringBuilder sb = new StringBuilder();
            for (Node<Integer> node = dummy.next; node != null; node = node.next) {
                sb.append(node.value).append(':');
            }
            return sb.toString();
        }

        private int findMax(Node<Integer> dummy) {
            String key = toString(dummy);
            Integer solution = solutions.get(key);
            if (solution != null) {
                return solution;
            }

            int max = 0;
            for (Node<Integer> node = dummy.next; node != null; node = node.next) {
                if (!(node.value.equals(node.prev.value) && node.next != null && node.next.value.equals(node.value))) {
                    max = Math.max(max, findMax(dummy, node));
                }
            }
            solutions.put(key, max);
            return max;
        }

        private int findMax(Node<Integer> dummy, Node<Integer> node) {
            int score = node.value;
            if (node.prev != null) {
                score *= node.prev.value;
            }
            if (node.next != null) {
                score *= node.next.value;
            }

            Node<Integer> prev = node.prev;
            Node<Integer> next = node.next;
            if (prev != null) prev.next = next;
            if (next != null) next.prev = prev;
            score += findMax(dummy);
            if (prev != null) prev.next = node;
            if (next != null) next.prev = node;

            return score;
        }
    }

    public int maxCoins(int[] nums) {
        return new Balloons().findMax(nums);
    }
}
