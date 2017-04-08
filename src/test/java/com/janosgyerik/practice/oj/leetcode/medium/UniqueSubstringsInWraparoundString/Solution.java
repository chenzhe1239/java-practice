package com.janosgyerik.practice.oj.leetcode.medium.UniqueSubstringsInWraparoundString;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int findSubstringInWraproundString(String p) {
        List<String> sequences = findSequences(p);

        // sort sequences by start
        // remove complete sub-sequences
        // calculate count per sequence
        // sum counts, discounting overlaps

        return 0;
    }

    List<String> findSequences(String p) {
        List<String> sequences = new ArrayList<>();
        char[] chars = p.toCharArray();

        for (int i = 0; i < p.length();) {
            int start = i++;
            while (isConsecutive(chars, i)) {
                i++;
            }
            sequences.add(p.substring(start, i));
        }
        return sequences;
    }

    boolean isConsecutive(char[] chars, int index) {
        return chars.length > index &&
                (chars[index - 1] + 1 == chars[index]
                        || ((chars[index - 1] - 'a' + 1) % 26 + 'a') == chars[index]);
    }
}
