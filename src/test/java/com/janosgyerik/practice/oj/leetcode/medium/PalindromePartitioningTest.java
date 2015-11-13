package com.janosgyerik.practice.oj.leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PalindromePartitioningTest {
    private static class PartitionStep {
        final List<String> palindromes;
        final int pos;

        PartitionStep(List<String> palindromes, int pos) {
            this.palindromes = palindromes;
            this.pos = pos;
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> results = new LinkedList<>();
        char[] chars = s.toCharArray();

        Queue<PartitionStep> queue = new LinkedList<>();
        queue.add(new PartitionStep(Arrays.asList(), 0));

        while (!queue.isEmpty()) {
            PartitionStep step = queue.poll();
            for (int i = step.pos + 1; i <= chars.length; ++i) {
                String substring = s.substring(step.pos, i);
                if (isPalindrome(substring)) {
                    List<String> copy = new LinkedList<>(step.palindromes);
                    copy.add(substring);
                    if (i == chars.length) {
                        results.add(copy);
                    } else {
                        queue.add(new PartitionStep(copy, i));
                    }
                }
            }
        }
        return results;
    }

    boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; ++i) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
