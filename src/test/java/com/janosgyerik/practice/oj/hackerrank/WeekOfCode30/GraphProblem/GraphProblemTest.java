package com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.GraphProblem;

import com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.GraphProblem.Solution.Graph;
import org.junit.Test;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphProblemTest {
    @Test
    public void test_example_0() {
        Scanner scanner = new Scanner("6\n" +
                "0 1 1 0 0 0\n" +
                "1 0 1 1 0 0\n" +
                "1 1 0 1 0 0\n" +
                "0 1 1 0 1 1\n" +
                "0 0 0 1 0 1\n" +
                "0 0 0 1 1 0");
        Graph graph = Graph.fromScanner(scanner);
        Graph g2 = graph.findOptimalSubGraph();
        assertThat(g2.size()).isEqualTo(6);
    }

    @Test
    public void test_example_1() {
        Scanner scanner = new Scanner("6\n" +
                "0 1 1 1 0 0\n" +
                "1 0 1 0 1 0\n" +
                "1 1 0 0 0 1\n" +
                "1 0 0 0 0 0\n" +
                "0 1 0 0 0 0\n" +
                "0 0 1 0 0 0");
        Graph graph = Graph.fromScanner(scanner);
        Graph g2 = graph.findOptimalSubGraph();
        assertThat(g2.triangles).isEqualTo(1);
        assertThat(g2.ratio).isEqualTo(1. / 3);
    }

    @Test
    public void test_example_1_2() {
        Scanner scanner = new Scanner("6\n" +
                "0 0 0 0 0 0\n" +
                "0 0 1 1 0 0\n" +
                "0 1 0 1 0 0\n" +
                "0 1 1 0 1 1\n" +
                "0 0 0 1 0 1\n" +
                "0 0 0 1 1 0");
        Graph graph = Graph.fromScanner(scanner);
        Graph g2 = graph.findOptimalSubGraph();
        assertThat(g2.triangles).isEqualTo(2);
        assertThat(g2.ratio).isEqualTo(0.4);
    }

    @Test
    public void test_size_2() {
        Scanner scanner = new Scanner("2\n" +
                "0 1\n" +
                "1 0");
        Graph graph = Graph.fromScanner(scanner);
        Graph g2 = graph.findOptimalSubGraph();
        assertThat(g2.size()).isEqualTo(0);
    }

    @Test
    public void test_size_3_in_a_line() {
        Scanner scanner = new Scanner("3\n" +
                "0 1 0\n" +
                "0 0 1\n" +
                "0 1 0");
        Graph graph = Graph.fromScanner(scanner);
        Graph g2 = graph.findOptimalSubGraph();
        assertThat(g2.size()).isEqualTo(0);
    }

    @Test
    public void test_size_4_split() {
        Scanner scanner = new Scanner("4\n" +
                "0 1 0 0\n" +
                "1 0 0 0\n" +
                "0 0 0 1\n" +
                "0 0 1 0");
        Graph graph = Graph.fromScanner(scanner);
        Graph g2 = graph.findOptimalSubGraph();
        assertThat(g2.size()).isEqualTo(0);
    }

    @Test
    public void test_simplify() {
        Scanner scanner = new Scanner("4\n" +
                "0 1 0 0\n" +
                "1 0 0 0\n" +
                "0 0 0 1\n" +
                "0 0 1 0");
        Graph graph = Graph.fromScanner(scanner);
        graph.simplify();
        assertThat(graph.size()).isEqualTo(0);
    }
}
