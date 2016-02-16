package com.janosgyerik.practice.oj.leetcode.hard.SubstringWithConcatenationOfAllWords;

import java.util.*;

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> indices = new ArrayList<>();

        int wordsLen = words[0].length() * words.length;

        for (int pos = 0; pos <= s.length() - wordsLen; ++pos) {
            if (contains(s, pos, new Counter(words))) {
                indices.add(pos);
            }
        }

        return indices;
    }

    static class Counter {
        private Map<String, Integer> counts = new HashMap<>();

        public Counter(String[] words) {
            for (String word : words) {
                Integer count = counts.get(word);
                if (count == null) {
                    counts.put(word, 1);
                } else {
                    counts.put(word, count + 1);
                }
            }
        }

        public boolean isEmpty() {
            return counts.isEmpty();
        }

        public Iterator<String> iterator() {
            return counts.keySet().iterator();
        }

        public void decrement(String word) {
            int count = counts.get(word);
            if (count == 1) {
                counts.remove(word);
            } else {
                counts.put(word, count - 1);
            }
        }
    }

    private boolean contains(String s, int offset, Counter counter) {
        if (counter.isEmpty()) {
            return true;
        }
        Iterator<String> iterator = counter.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (s.startsWith(word, offset)) {
                counter.decrement(word);
                return contains(s, offset + word.length(), counter);
            }
        }
        return false;
    }
}
