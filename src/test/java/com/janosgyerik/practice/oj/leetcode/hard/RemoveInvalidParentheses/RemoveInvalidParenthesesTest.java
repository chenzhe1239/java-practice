package com.janosgyerik.practice.oj.leetcode.hard.RemoveInvalidParentheses;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RemoveInvalidParenthesesTest {
    private final Solution solution = new Solution();

    private List<String> solve(String s) {
        return solution.removeInvalidParentheses(s);
    }

    @Test
    public void should_empty_for_empty() {
        String empty = "";
        assertThat(solve(empty)).containsExactlyInAnyOrder(empty);
    }

    @Test
    public void should_remove_nothing_if_balanced() {
        String balanced = "(())()";
        assertThat(solve(balanced)).containsExactlyInAnyOrder(balanced);
    }

    @Test
    public void should_find_two_variants() {
        assertThat(solve("()())()")).containsExactlyInAnyOrder("()()()", "(())()");
    }

    @Test
    public void should_ignore_non_brackets() {
        assertThat(solve("(a)())()")).containsExactlyInAnyOrder("(a)()()", "(a())()");
    }

    @Test
    public void should_find_three_variants() {
        assertThat(solve("()()())")).containsExactlyInAnyOrder("(()())", "()(())", "()()()");
    }

    @Test
    public void should_remove_invalid_closing_and_opening() {
        assertThat(solve(")(")).containsExactlyInAnyOrder("");
    }

    @Test
    public void should_remove_invalid_opening() {
        assertThat(solve("(a()")).containsExactlyInAnyOrder("(a)", "a()");
    }

    @Test
    public void should_remove_invalid_opening_2() {
        assertThat(solve("(a(b()")).containsExactlyInAnyOrder("(ab)", "ab()", "a(b)");
    }

    @Test
    public void should_remove_excess_opening() {
        assertThat(solve("()(()((")).containsExactlyInAnyOrder("()()");
    }

    @Test
    public void should_remove_excess_opening_2() {
        assertThat(solve("()(a()((")).containsExactlyInAnyOrder("()a()", "()(a)");
    }

    @Test
    public void test_example_1() {
        assertThat(solve("(((k()((")).containsExactlyInAnyOrder("(k)", "k()");
    }

    @Test
    public void test_example_2() {
        assertThat(solve("(()()()(()")).containsExactlyInAnyOrder("()()()()", "(())()()", "(()())()", "(()()())");
    }

    @Test
    public void test_example_3() {
        assertThat(solve(")()))())))")).containsExactlyInAnyOrder("(())", "()()");
    }

    @Test
    public void test_example_4() {
        assertThat(solve("())())")).containsExactlyInAnyOrder("(())", "()()");
    }

    @Test
    public void test_example_5() {
        assertThat(solve("((()((s((((()")).containsExactlyInAnyOrder("(()s)", "()(s)", "()s()");
    }
}
