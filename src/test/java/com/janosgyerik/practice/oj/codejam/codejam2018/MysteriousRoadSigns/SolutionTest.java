package com.janosgyerik.practice.oj.codejam.codejam2018.MysteriousRoadSigns;

import java.util.Scanner;
import org.junit.Test;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void test_solve() {
        String input = "3\n" +
            "1\n" +
            "1 1 1\n" +
            "5\n" +
            "2 7 12\n" +
            "6 3 11\n" +
            "8 10 1\n" +
            "11 11 12\n" +
            "13 9 14\n" +
            "5\n" +
            "1 3 3\n" +
            "2 2 2\n" +
            "3 1 1\n" +
            "4 2 2\n" +
            "5 3 3";
        solution.solve(new Scanner(input));
    }

}
