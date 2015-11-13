package com.janosgyerik.practice.oj.leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CombinationsTest {
    private static class ListWithIndex {
        final List<Integer> list;
        final int index;

        ListWithIndex(List<Integer> list, int index) {
            this.list = list;
            this.index = index;
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new LinkedList<>();

        Queue<ListWithIndex> canGrow = new LinkedList<>();
        canGrow.add(new ListWithIndex(Arrays.asList(), 1));

        while (!canGrow.isEmpty()) {
            ListWithIndex listWithIndex = canGrow.poll();

            for (int i = listWithIndex.index; i <= n; ++i) {
                List<Integer> copy = new LinkedList<>(listWithIndex.list);
                copy.add(i);
                if (copy.size() < k && i < n) {
                    canGrow.add(new ListWithIndex(copy, i + 1));
                } else if (copy.size() == k) {
                    results.add(copy);
                }
            }
        }

        return results;
    }
}
