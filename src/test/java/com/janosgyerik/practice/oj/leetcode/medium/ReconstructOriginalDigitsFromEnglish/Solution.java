package com.janosgyerik.practice.oj.leetcode.medium.ReconstructOriginalDigitsFromEnglish;

import java.util.*;

public class Solution {
    private static final String[] DIGIT_WORDS = {
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
    };

    private static final Map<String, Character> DIGITS = new HashMap<>();

    static {
        for (int i = 0; i < DIGIT_WORDS.length; i++) {
            DIGITS.put(DIGIT_WORDS[i], (char) ('0' + i));
        }
    }

    static class Counter {
        private final Map<Character, Integer> counts = new HashMap<>();

        void add(String s) {
            for (char c : s.toCharArray()) {
                counts.put(c, counts.getOrDefault(c, 0) + 1);
            }
        }

        private void remove(String s) {
            for (char c : s.toCharArray()) {
                counts.put(c, counts.get(c) - 1);
            }
        }

        public int eliminate(String word) {
            int count = word.chars()
                    .mapToObj(c -> counts.getOrDefault((char) c, 0))
                    .mapToInt(Integer::intValue).min().orElse(-1);
            for (int i = 0; i < count; i++) {
                remove(word);
            }
            return count;
        }

        public Map<Character, Integer> getMap() {
            return counts;
        }
    }

    static class DigitWordIterator implements Iterable<String> {
        @Override
        public Iterator<String> iterator() {
            // ordered by words that have a unique letter
            return Arrays.asList(
                    "zero",
                    "two",
                    "four",
                    "six",
                    "eight",
                    "one",
                    "three",
                    "five",
                    "seven",
                    "nine"
            ).iterator();
        }
    }

    public String originalDigits(String s) {
        Counter counter = new Counter();
        counter.add(s);

        List<Character> digits = new ArrayList<>();
        for (String word : new DigitWordIterator()) {
            int count = counter.eliminate(word);
            for (int i = 0; i < count; i++) {
                digits.add(toDigit(word));
            }
        }

        Collections.sort(digits);

        StringBuilder sb = new StringBuilder();
        digits.forEach(sb::append);
        return sb.toString();
    }

    private Character toDigit(String word) {
        return DIGITS.get(word);
    }
}
