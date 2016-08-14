package com.janosgyerik.practice.oj.leetcode.hard.SudokuSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {
    static class DecisionPoint implements Comparable<DecisionPoint> {
        final int row, column, block;
        final Set<Integer> choices = new HashSet<>();

        DecisionPoint(int row, int column) {
            this.row = row;
            this.column = column;
            this.block = toBlockNum(row, column);

            choices.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }

        @Override
        public int compareTo(DecisionPoint o) {
            return Integer.compare(choices.size(), o.choices.size());
        }

        @Override
        public String toString() {
            return String.format("(%s, %s) (%s) = %s", row, column, block, choices);
        }
    }

    static int toBlockNum(int row, int column) {
        return (row / 3) * 3 + (column / 3);
    }

    static class Solver {
        private static final char EMPTY = '.';

        final char[][] board;

        final Set<DecisionPoint>[] rows = createIndex();
        final Set<DecisionPoint>[] columns = createIndex();
        final Set<DecisionPoint>[] blocks = createIndex();

        final PriorityQueue<DecisionPoint> undecided = new PriorityQueue<>();

        public Solver(char[][] board) {
            this.board = board;
            init();
        }

        private Set<DecisionPoint>[] createIndex() {
            Set<DecisionPoint>[] sets = new Set[9];
            for (int i = 0; i < sets.length; i++) {
                sets[i] = new HashSet<>();
            }
            return sets;
        }

        public void solve() {
            while (!undecided.isEmpty()) {
                if (!eliminateNext()) {
                    break;
                }
            }
        }

        private void init() {
            List<DecisionPoint> points = new ArrayList<>();

            for (int row = 0; row < board.length; row++) {
                for (int column = 0; column < board[row].length; column++) {
                    if (board[row][column] == EMPTY) {
                        DecisionPoint dp = new DecisionPoint(row, column);
                        points.add(dp);
                        rows[row].add(dp);
                        columns[column].add(dp);
                        blocks[dp.block].add(dp);
                    }
                }
            }

            for (int row = 0; row < board.length; row++) {
                for (int column = 0; column < board[row].length; column++) {
                    if (board[row][column] != EMPTY) {
                        int value = board[row][column] - '0';
                        for (DecisionPoint dp : rows[row]) {
                            dp.choices.remove(value);
                        }
                        for (DecisionPoint dp : columns[column]) {
                            dp.choices.remove(value);
                        }
                        for (DecisionPoint dp : blocks[toBlockNum(row, column)]) {
                            dp.choices.remove(value);
                        }
                    }
                }
            }

            undecided.addAll(points);
        }

        private boolean eliminateNext() {
            DecisionPoint next = undecided.poll();
            if (next.choices.isEmpty()) {
                return false;
            }
            while (!next.choices.isEmpty()) {
                int choice = next.choices.iterator().next();
                next.choices.remove(choice);
                if (next.choices.isEmpty()) {
                    eliminate(next, choice);
                    return true;
                }
                char[][] copy = copy(board);
                copy[next.row][next.column] = (char)('0' + choice);
                solveSudoku(copy);
                if (isComplete(copy)) {
                    copy(copy, board);
                    return false;
                }
            }
            return false;
        }

        private boolean isComplete(char[][] board) {
            for (char[] row : board) {
                for (char c : row) {
                    if (c == EMPTY) return false;
                }
            }
            return true;
        }

        private char[][] copy(char[][] board) {
            char[][] copy = new char[board.length][];
            for (int i = 0; i < copy.length; i++) {
                copy[i] = board[i].clone();
            }
            return copy;
        }

        private void copy(char[][] source, char[][] target) {
            System.arraycopy(source, 0, target, 0, target.length);
        }

        private void eliminate(DecisionPoint point, int value) {
            board[point.row][point.column] = (char)('0' + value);

            Iterator<DecisionPoint> iterator = rows[point.row].iterator();
            while (iterator.hasNext()) {
                DecisionPoint dp = iterator.next();
                if (removeChoice(value, dp) && dp.choices.isEmpty()) {
                    iterator.remove();
                }
            }

            iterator = columns[point.column].iterator();
            while (iterator.hasNext()) {
                DecisionPoint dp = iterator.next();
                if (removeChoice(value, dp) && dp.choices.isEmpty()) {
                    iterator.remove();
                }
            }

            iterator = blocks[point.block].iterator();
            while (iterator.hasNext()) {
                DecisionPoint dp = iterator.next();
                if (removeChoice(value, dp) && dp.choices.isEmpty()) {
                    iterator.remove();
                }
            }
        }

        private boolean removeChoice(int value, DecisionPoint dp) {
            if (dp.choices.remove(value)) {
                undecided.remove(dp);
                if (!dp.choices.isEmpty()) undecided.add(dp);
                return true;
            }
            return false;
        }
    }

    public static void solveSudoku(char[][] board) {
        new Solver(board).solve();
    }
}
