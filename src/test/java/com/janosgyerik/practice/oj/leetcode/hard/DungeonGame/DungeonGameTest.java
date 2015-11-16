package com.janosgyerik.practice.oj.leetcode.hard.DungeonGame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DungeonGameTest {

    private final Solution solution = new Solution();

    private int solve(int[][] grid) {
        return solution.calculateMinimumHP(grid);
    }

    @Test
    public void test_singleton_3() {
        assertEquals(1, solve(new int[][]{{3}}));
    }

    @Test
    public void test_singleton_m3() {
        assertEquals(4, solve(new int[][]{{-3}}));
    }

//    @Test
//    public void test_row_1_2_3() {
//        assertEquals(6, solve(new int[][]{{1, 2, 3}}));
//    }
//
//    @Test
//    public void test_column_1_2_3() {
//        assertEquals(6, solve(new int[][]{{1}, {2}, {3}}));
//    }
//
//    @Test
//    public void test_pass_through_top() {
//        assertEquals(0, solve(new int[][]{{0, 0, 0}, {1, 1, 0}}));
//    }
//
//    @Test
//    public void test_pass_through_bottom() {
//        assertEquals(0, solve(new int[][]{{0, 1, 1}, {0, 0, 0}}));
//    }
//
//    @Test
//    public void test_pass_through_zigzag() {
//        assertEquals(0, solve(new int[][]{{0, 1, 1}, {0, 0, 1}, {1, 0, 0}}));
//    }
//
//    @Test
//    public void test_multiple_min_paths() {
//        assertEquals(0, solve(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
//    }

    @Test
    public void test_dungeon() {
        assertEquals(7, solve(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}));
        /*
        -2  -3  3
        -5 -10  1
        10  30 -5

         // the minimum value that is inescapable until a cell
         // = min(min(top, left), value + min(top, left))
        -2  -5 -5
        -7 -15 -5
        -7  -7 -6

        (xmin, hp)
        -2,-2   -5,-5   -5,-2
        -7,-7  -15,-15  -5,-1
        -7,3    -7,33   -6,-6

        if left xmin < top xmin || left xmin == top xmin && left hp > top hp:
            nxmin = min(left xmin, left xmin + grid)
            hp = left hp + grid
        else:
            nxmin = min(top xmin, top xmin + grid)
            hp = top hp + grid
         */
    }
}
