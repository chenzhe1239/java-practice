package com.janosgyerik.practice.oj.leetcode.hard.SudokuSolver;

import org.junit.Test;

import static com.janosgyerik.practice.oj.leetcode.hard.SudokuSolver.Solution.toBlockNum;
import static org.junit.Assert.assertEquals;

public class SudokuSolverTest {
    private final Solution solution = new Solution();
    
    @Test
    public void test_() {
        char[][] board = new char[][]{
                "53..7....".toCharArray(),
                "6..195...".toCharArray(),
                ".98....6.".toCharArray(),
                "8...6...3".toCharArray(),
                "4..8.3..1".toCharArray(),
                "7...2...6".toCharArray(),
                ".6....28.".toCharArray(),
                "...419..5".toCharArray(),
                "....8..79".toCharArray(),
        };
        board = createBoard("[\"..9748...\",\"7........\",\".2.1.9...\",\"..7...24.\",\".64.1.59.\",\".98...3..\",\"...8.3.2.\",\"........6\",\"...2759..\"]");
        Solution.Solver solver = new Solution.Solver(board);
        solver.solve();

//        printUndecidedPoints(solver);
//        print(board);
    }

    private void printUndecidedPoints(Solution.Solver solver) {
        for (Solution.DecisionPoint dp : solver.undecided) {
            System.out.println(dp);
        }
    }

    private void print(char[][] board) {
        for (char[] row : board) {
            System.out.println(row);
        }
    }

    private char[][] createBoard(String s) {
        char[][] board = new char[9][];
        String[] parts = s.substring(1, s.length() - 1).split(",");
        for (int i = 0; i < 9; i++) {
            board[i] = parts[i].substring(1, parts[i].length() - 1).toCharArray();
        }
        return board;
    }

    @Test
    public void test_toBlockNum_0_0_is_0() {
        assertEquals(0, toBlockNum(0, 0));
    }

    @Test
    public void test_toBlockNum_2_2_is_0() {
        assertEquals(0, toBlockNum(2, 2));
    }

    @Test
    public void test_toBlockNum_3_3_is_4() {
        assertEquals(4, toBlockNum(3, 3));
    }

    @Test
    public void test_toBlockNum_8_8_is_8() {
        assertEquals(8, toBlockNum(8, 8));
    }

    @Test
    public void test_toBlockNum_8_0_is_6() {
        assertEquals(6, toBlockNum(8, 0));
    }

    @Test
    public void test_toBlockNum_0_8_is_2() {
        assertEquals(2, toBlockNum(0, 8));
    }
}
