package com.janosgyerik.practice.oj.leetcode.medium.MaximumProductOfWordLengths;

import java.util.BitSet;

public class Solution {
    private static class Word {
        private final String word;
        private final BitSet bits;

        private Word(String word) {
            this.word = word;
            this.bits = new BitSet('z' - 'a' + 1);
            for (char c : word.toCharArray()) {
                bits.set(c - 'a');
            }
        }
    }

    public int maxProduct(String... words) {
        Word[] words2 = new Word[words.length];
        for (int i = 0; i < words.length; i++) {
            words2[i] = new Word(words[i]);
        }

        int max = 0;
        for (int i = 0; i < words2.length; i++) {
            for (int j = i + 1; j < words2.length; j++) {
                Word word1 = words2[i];
                Word word2 = words2[j];
                if (!word1.bits.intersects(word2.bits)) {
                    max = Math.max(max, word1.word.length() * word2.word.length());
                }
            }
        }
        return max;
    }
}
