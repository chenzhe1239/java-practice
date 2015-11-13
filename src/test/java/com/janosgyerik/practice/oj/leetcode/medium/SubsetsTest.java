package com.janosgyerik.practice.oj.leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SubsetsTest {
    private static class ListWithIndex {
        final List<Integer> list;
        final int index;

        ListWithIndex(List<Integer> list, int index) {
            this.list = list;
            this.index = index;
        }
    }

    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> results = new LinkedList<>();
        results.add(Arrays.asList());
        if (S.length == 0) {
            return results;
        }

        Arrays.sort(S);

        Queue<ListWithIndex> canGrow = new LinkedList<>();
        canGrow.add(new ListWithIndex(new LinkedList<>(), 0));

        while (!canGrow.isEmpty()) {
            ListWithIndex listWithIndex = canGrow.poll();
            for (int i = listWithIndex.index; i < S.length - 1; ++i) {
                List<Integer> copy = new LinkedList<>(listWithIndex.list);
                copy.add(S[i]);
                results.add(copy);
                canGrow.add(new ListWithIndex(copy, i + 1));
            }
            List<Integer> copy = new LinkedList<>(listWithIndex.list);
            copy.add(S[S.length - 1]);
            results.add(copy);
        }
        return results;
    }
}
