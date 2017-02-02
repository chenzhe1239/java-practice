package com.janosgyerik.practice.oj.leetcode.hard.PalindromePairs;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public List<List<Integer>> palindromePairs(String... words) {
        return new Finder(words).find();
    }

    static class Finder {
        final Set<List<Integer>> pairs = new HashSet<>();
        final Map<String, Integer> indexes;

        Finder(String[] words) {
            indexes = buildIndexCache(words);
        }

        private Map<String, Integer> buildIndexCache(String[] words) {
            Map<String, Integer> indexes = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                indexes.put(words[i], i);
            }
            return indexes;
        }

        List<List<Integer>> find() {
            addEmptyPairs();
            for (String word : indexes.keySet()) {
                if (word.length() < 2) continue;
                StringBuilder rev = new StringBuilder(word).reverse();
                checkAny(word, rev);
                checkRight(word, rev);
                checkLeft(word, rev);
                for (int i = 2; i < rev.length(); i++) {
                    checkRight(word, rev, i);
                    checkLeft(word, rev, i);
                }
            }
            return pairs.stream().collect(Collectors.toList());
        }

        private void addEmptyPairs() {
            Integer index = indexes.get("");
            if (index == null) return;
            for (Map.Entry<String, Integer> entry : indexes.entrySet()) {
                String word = entry.getKey();
                if (!word.isEmpty() && isPalindrome(new StringBuilder(word))) {
                    int index2 = indexes.get(word);
                    pairs.add(Arrays.asList(index, index2));
                    pairs.add(Arrays.asList(index2, index));
                }
            }
        }

        void checkAny(String word, StringBuilder sb) {
            if (isPalindrome(sb)) {
                return;
            }
            Integer index2 = indexes.get(sb.toString());
            if (index2 != null) {
                int index1 = indexes.get(word);
                pairs.add(Arrays.asList(index1, index2));
                pairs.add(Arrays.asList(index2, index1));
            }
        }

        void checkLeft(String word, StringBuilder sb) {
            Integer index2 = indexes.get(sb.substring(0, sb.length() - 1));
            if (index2 != null) {
                int index1 = indexes.get(word);
                pairs.add(Arrays.asList(index2, index1));
            }
        }

        void checkRight(String word, StringBuilder sb) {
            Integer index2 = indexes.get(sb.substring(1));
            if (index2 != null) {
                int index1 = indexes.get(word);
                pairs.add(Arrays.asList(index1, index2));
            }
        }

        void checkLeft(String word, StringBuilder sb, int count) {
            String word1 = sb.substring(0, sb.length() - count);
            Integer index2 = indexes.get(word1);
            if (index2 != null) {
                if (isPair(word1, word)) {
                    int index1 = indexes.get(word);
                    pairs.add(Arrays.asList(index2, index1));
                }
            }
        }

        void checkRight(String word, StringBuilder sb, int count) {
            String word2 = sb.substring(count);
            Integer index2 = indexes.get(word2);
            if (index2 != null) {
                if (isPair(word, word2)) {
                    int index1 = indexes.get(word);
                    pairs.add(Arrays.asList(index1, index2));
                }
            }
        }
    }

    static boolean isPair(String word1, String word2) {
        return isPalindrome(new StringBuilder(word1).append(word2));
    }

    static boolean isPalindrome(StringBuilder sb) {
        int len = sb.length();
        for (int i = 0; i < len / 2; ++i) {
            if (sb.charAt(i) != sb.charAt(len - i -1)) {
                return false;
            }
        }
        return true;
    }
}
