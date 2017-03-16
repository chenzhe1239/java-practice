package com.janosgyerik.practice.oj.leetcode.medium.PacificAtlanticWaterFlow;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PacificAtlanticWaterFlowTest {
    private final Solution solution = new Solution();

//    int solve(int... nums) {
//        return solution.solve(nums);
//    }

    @Test
    public void test_1_1_1_1() {
        solution.pacificAtlantic(new int[][]{{1, 1}, {1, 1}});
    }

    @Test
    public void test_40() {
        int[][] matrix = matrix("[[1,2,3,4,5,6,7,8,9,10,11,12,13,14],[52,53,54,55,56,57,58,59,60,61,62,63,64,15],[51,96,97,98,99,100,101,102,103,104,105,106,65,16],[50,95,132,133,134,135,136,137,138,139,140,107,66,17],[49,94,131,160,161,162,163,164,165,166,141,108,67,18],[48,93,130,159,180,181,182,183,184,167,142,109,68,19],[47,92,129,158,179,192,193,194,185,168,143,110,69,20],[46,91,128,157,178,191,196,195,186,169,144,111,70,21],[45,90,127,156,177,190,189,188,187,170,145,112,71,22],[44,89,126,155,176,175,174,173,172,171,146,113,72,23],[43,88,125,154,153,152,151,150,149,148,147,114,73,24],[42,87,124,123,122,121,120,119,118,117,116,115,74,25],[41,86,85,84,83,82,81,80,79,78,77,76,75,26],[40,39,38,37,36,35,34,33,32,31,30,29,28,27]]");
        solution.pacificAtlantic(matrix);
    }

    @Test
    public void test_82() {
        int[][] matrix = matrix("[[3,3,3,3,3,3],[3,0,3,3,0,3],[3,3,3,3,3,3]]");
        solution.pacificAtlantic(matrix);
    }

    int[][] matrix(String s) {
        String[] rows = s.replaceAll("^..|..$", "").split(Pattern.quote("],["));
        int[][] matrix = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            matrix[i] = Arrays.stream(rows[i].split(",")).mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }
}
