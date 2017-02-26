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
                max = Math.max(max, findMax(dummy, node));
            }
            solutions.put(key, max);
            return max;
        }

        private int findMax(Node<Integer> dummy, Node<Integer> node) {
            int score = node.value;

            Node<Integer> prev = node.prev;
            Node<Integer> next = node.next;

            if (prev != null && next != null) {
                score *= node.prev.value;
                score *= node.next.value;
                prev.next = next;
                next.prev = prev;
                score += findMax(dummy);
                prev.next = node;
                next.prev = node;
            } else if (prev != null) {
                score *= node.prev.value;
                prev.next = null;
                score += findMax(dummy);
                prev.next = node;
            } else if (next != null) {
                score *= node.next.value;
                next.prev = null;
                score += findMax(dummy);
                next.prev = node;
            }

            return score;
        }
    }

    public int maxCoins(int[] nums) {
        return new Balloons().findMax(nums);
    }
}
