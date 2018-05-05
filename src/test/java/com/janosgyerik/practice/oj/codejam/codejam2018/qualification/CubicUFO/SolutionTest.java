package com.janosgyerik.practice.oj.codejam.codejam2018.qualification.CubicUFO;

import java.util.Scanner;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {
    @Test
    public void test_solve() {
        new Solution().solve(new Scanner("2\n" +
            "1.000000\n" +
            "1.414213"));
    }
}
