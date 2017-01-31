package com.janosgyerik.practice.oj.leetcode.medium.ReconstructItinerary;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReconstructItineraryTest {
    private final Solution solution = new Solution();

    List<String> solve(String[][] tickets) {
        return solution.findItinerary(tickets);
    }

    private String[][] tickets(String... addresses) {
        String[][] tickets = new String[addresses.length - 1][];
        for (int i = 0; i < addresses.length - 1; i++) {
            tickets[i] = new String[]{addresses[i], addresses[i + 1]};
        }
        return tickets;
    }

    void verify(String... addresses) {
        assertThat(solve(tickets(addresses))).containsExactly(addresses);
    }

    @Test
    public void test_one_step() {
        verify("JFK", "LCA");
    }

    @Test
    public void test_two_steps() {
        verify("JFK", "LCA", "SFO");
    }

    @Test
    public void test_example_1() {
        verify("JFK", "MUC", "LHR", "SFO", "SJC");
    }

    @Test
    public void test_nonstraight() {
        verify("JFK", "NRT", "JFK", "KUL");
    }
}
