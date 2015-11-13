package com.janosgyerik.practice.oj.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public boolean exist(char[][] board, String word) {
        for (int start : findStartPositions(board, word.charAt(0))) {
            int i = start / board[0].length;
            int j = start % board[0].length;

        }
        return false;
    }

    List<Integer> findStartPositions(char[][] board, char c) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == c) {
                    list.add(i * board[i].length + j);
                }
            }
        }
        return list;
    }

    char[][] makeCopy(char[][] arr) {
        char[][] copy = new char[arr.length][];
        for (int i = 0; i < arr.length; ++i) {
            copy[i] = arr[i].clone();
        }
        return copy;
    }
}

public class WordSearchTest {
}
