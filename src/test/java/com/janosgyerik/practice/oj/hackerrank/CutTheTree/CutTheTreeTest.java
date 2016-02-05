package com.janosgyerik.practice.oj.hackerrank.CutTheTree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CutTheTreeTest {

    private final Solution.Input input = Solution.Input.fromString("6\n" +
            "100 200 100 500 100 600\n" +
            "1 2\n" +
            "2 3\n" +
            "2 5\n" +
            "4 5\n" +
            "5 6");

    @Test
    public void test_input() {
        Map<Integer, Integer> nodes = new HashMap<>();
        nodes.put(1, 100);
        nodes.put(2, 200);
        nodes.put(3, 100);
        nodes.put(4, 500);
        nodes.put(5, 100);
        nodes.put(6, 600);
        assertEquals(nodes, input.nodes);
    }

    @Test
    public void test_calculate() {
        Solution.Tree tree = new Solution.Tree(input.nodes, input.links);
        assertEquals(1600, tree.calculate());
    }

    @Test
    public void test_example_1() {
        assertEquals(400, new Solution().solve(input));
    }
}
