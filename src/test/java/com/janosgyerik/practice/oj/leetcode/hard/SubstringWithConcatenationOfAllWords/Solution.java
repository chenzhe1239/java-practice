package com.janosgyerik.practice.oj.leetcode.hard.SubstringWithConcatenationOfAllWords;

import java.util.*;

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> indices = new ArrayList<>();

        int wordsLen = words[0].length() * words.length;

        for (int pos = 0; pos <= s.length() - wordsLen; ++pos) {
            if (contains(s, pos, words, new boolean[words.length], 0)) {
                indices.add(pos);
            }
        }

        return indices;
    }

    private boolean contains(String s, int offset, String[] words, boolean[] used, int count) {
        if (count == words.length) {
            return true;
        }
        for (int i = 0; i < used.length; ++i) {
            if (used[i]) {
                continue;
            }
            String word = words[i];
            if (s.startsWith(word, offset)) {
                used[i] = true;
                return contains(s, offset + word.length(), words, used, count + 1);
            }
        }
        return false;
    }
}
