package com.janosgyerik.practice.oj.leetcode.hard.NQueens;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        solveNQueens(new int[n], 0, solutions);
        return solutions;
    }

    public void solveNQueens(int[] placements, int pos, List<List<String>> solutions) {
        if (pos == placements.length) {
            solutions.add(toSolutionFormat(placements));
            return;
        }
        for (int placement = 0; placement < placements.length; ++placement) {
            if (isValid(placements, pos, placement)) {
                int[] copy = placements.clone();
                copy[pos] = placement;
                solveNQueens(copy, pos + 1, solutions);
            }
        }
    }

    private List<String> toSolutionFormat(int[] placements) {
        List<String> solution = new ArrayList<>();
        char[] chars = new char[placements.length];
        for (int placement : placements) {
            for (int i = 0; i < placements.length; ++i) {
                chars[i] = i == placement ? 'Q' : '.';
            }
            solution.add(new String(chars));
        }
        return solution;
    }

    public boolean isValid(int[] placements, int pos, int placement) {
        for (int i = 0; i < pos; ++i) {
            if (placements[i] == placement) {
                return false;
            }
            int diff = pos - i;
            if (placements[i] + diff == placement || placements[i] - diff == placement) {
                return false;
            }
        }
        return true;
    }
}
