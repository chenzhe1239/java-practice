package com.janosgyerik.practice.oj.leetcode.easy;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidSudokuTest {
    public boolean isValidSudoku(char[][] board) {
        int height = board.length;
        if (height == 0) {
            return true;
        }
        int width = board[0].length;
        if (height != width) {
            return false;
        }

        for (int row = 0; row < 9; ++row) {
            boolean[] usedValuesPerRow = new boolean[9];
            boolean[] usedValuesPerCol = new boolean[9];
            boolean[] usedValuesPerBox = new boolean[9];
            for (int col = 0; col < 9; ++col) {
                {
                    char cell1 = board[row][col];
                    if (cell1 != '.') {
                        int value = cell1 - '1';
                        if (usedValuesPerRow[value]) {
                            return false;
                        }
                        usedValuesPerRow[value] = true;
                    }
                }
                {
                    char cell2 = board[col][row];
                    if (cell2 != '.') {
                        int value = cell2 - '1';
                        if (usedValuesPerCol[value]) {
                            return false;
                        }
                        usedValuesPerCol[value] = true;
                    }
                }
                {
                    int boxRow = 3 * (row / 3) + col / 3;
                    int boxCol = 3 * (row % 3) + col % 3;
                    char cell3 = board[boxRow][boxCol];
                    if (cell3 != '.') {
                        int value = cell3 - '1';
                        if (usedValuesPerBox[value]) {
                            return false;
                        }
                        usedValuesPerBox[value] = true;
                    }
                }
            }
        }

        return true;
    }

    @Test
    public void testEmpty() {
        assertTrue(isValidSudoku(new char[0][0]));
    }

    @Ignore
    @Test
    public void testSingleton() {
        assertTrue(isValidSudoku(new char[][]{{'1'}}));
    }

    @Test
    public void testWide() {
        assertFalse(isValidSudoku(new char[][]{{'1', '2'}}));
    }

    @Test
    public void testTall() {
        assertFalse(isValidSudoku(new char[][]{{'1'}, {'2'}}));
    }

    private char[][] createBoard() {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                board[i][j] = '.';
            }
        }
        return board;
    }

    private char[][] createBoard(String... rows) {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; ++i) {
            board[i] = rows[i].toCharArray();
        }
        return board;
    }

    @Test
    public void testWellFormedInvalid() {
        char[][] board = createBoard();
        for (int i = 0; i < 9; ++i) {
            board[i][0] = '3';
        }
        assertFalse(isValidSudoku(board));
    }

    @Test
    public void testWellFormedValid() {
        char[][] board = createBoard();
        for (int i = 0; i < 9; ++i) {
            board[i][0] = (char) ('0' + i + 1);
        }
        assertTrue(isValidSudoku(board));
    }

    @Ignore
    @Test
    public void testComplete() {
        char[][] board = createBoard();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                board[i][j] = (char) ('0' + ((i + j) % 9) + 1);
            }
            //            System.out.println(Arrays.toString(board[i]));
        }
        assertTrue(isValidSudoku(board));
    }

    @Test
    public void testExample1() {
        char[][] board = createBoard("..4...63.", ".........", "5......9.", "...56....", "4.3.....1", "...7.....",
                "...5.....", ".........", ".........");
        assertFalse(isValidSudoku(board));
    }

    @Test
    public void testExample2() {
        char[][] board = createBoard("....5..1.", ".4.3.....", ".....3..1", "8......2.", "..2.7....", ".15......",
                ".....2...", ".2.9.....", "..4......");
        assertFalse(isValidSudoku(board));
    }
}
