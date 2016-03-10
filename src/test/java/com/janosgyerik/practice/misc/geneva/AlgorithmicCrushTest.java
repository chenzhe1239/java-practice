package com.janosgyerik.practice.misc.geneva;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AlgorithmicCrushTest {
    private final AlgorithmicCrush solution = new AlgorithmicCrush();

    private long solve(Scanner scanner) {
        return solution.solve(scanner);
    }

    @Test
    public void test_example_ops_1() {
        assertEquals(200, solve(new Scanner("5 3\n" +
                "1 2 100\n" +
                "2 5 100\n" +
                "3 4 100\n")));
    }

    @Test
    public void test_example_ops_2() {
        assertEquals(882, solve(new Scanner("4 3\n" +
                "2 3 603\n" +
                "1 1 286\n" +
                "4 4 882\n" +
                "")));
    }
}
