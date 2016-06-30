package com.janosgyerik.practice.oj.hackerrank.PoisonousPlants;

import java.util.Scanner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PoisonousPlantsTest {
    private int solve(String input) {
        Scanner scanner = new Scanner(input);
        return Solution.countDays(scanner);
    }

    @Test
    public void test_example_1() {
        assertEquals(2, solve("7\n6 5 8 4 7 10 9"));
    }

    @Test
    public void test_all_decreasing_should_give_0() {
        assertEquals(0, solve("5\n5 4 3 2 1"));
    }

    @Test
    public void test_all_increasing_should_give_1() {
        assertEquals(1, solve("5\n1 2 3 4 5"));
    }

    @Test
    public void test_all_decreasing_after_first_low_value_should_give_count() {
        assertEquals(4, solve("5\n1 5 4 3 2"));
    }
}
