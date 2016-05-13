package com.janosgyerik.practice.oj.leetcode.hard.BurstBalloons;

import java.util.*;

public class Solution {
    static class Balloons {

        static class Balloon {
            Balloon right;
            Balloon left;
            final int value;
            int order;

            Balloon(int value) {
                this.value = value;
                this.order = value;
            }

            static Balloon createEdge() {
                return new Balloon(1);
            }
        }

        private final PriorityQueue<Balloon> heap;

        public Balloons(int[] nums) {
            heap = new PriorityQueue<>(nums.length, (a, b) -> Integer.compare(a.order, b.order));

            Balloon first = Balloon.createEdge();
            Balloon prev = first;

            for (int num : nums) {
                Balloon current = new Balloon(num);
                heap.add(current);

                current.left = prev;
                prev.right = current;

                prev = current;
            }

            Balloon last = Balloon.createEdge();
            last.left = prev;
            prev.right = last;


            last.left.order = Integer.MAX_VALUE;
            heap.remove(last.left);
            heap.add(last.left);

            first.right.order = Integer.MAX_VALUE;
            heap.remove(first.right);
            heap.add(first.right);
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        public int burstNext() {
            if (heap.size() < 3) {
                Balloon first = heap.poll();
                if (heap.isEmpty()) {
                    return first.value;
                }
                Balloon second = heap.poll();
                return first.value * second.value + Math.max(first.value, second.value);
            }
            Balloon min = heap.poll();
            int coins = min.left.value * min.value * min.right.value;
            min.right.left = min.left;
            min.left.right = min.right;
            return coins;
        }
    }

    public int maxCoins(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int coins = 0;
        Balloons balloons = new Balloons(nums);
        while (!balloons.isEmpty()) {
            coins += balloons.burstNext();
        }

        return coins;
    }
}
