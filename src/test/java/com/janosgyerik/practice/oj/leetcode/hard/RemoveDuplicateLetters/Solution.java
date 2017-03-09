package com.janosgyerik.practice.oj.leetcode.hard.RemoveDuplicateLetters;

import java.util.*;

public class Solution {

    // TLE on 270 / 286

    public String removeDuplicateLetters(String s) {
        if (s.isEmpty()) return s;

        char[] chars = simplify(s.toCharArray());
        Map<Character, List<Integer>> posmap = buildPosMap(chars);
        return removeDuplicateLetters(chars, posmap, 'a');
    }

    char[] simplify(char[] chars) {
        int pos = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[pos] == chars[i]) {
                chars[i] = '\0';
            } else {
                pos = i;
            }
        }
        return chars;
    }

    public String removeDuplicateLetters(char[] chars, Map<Character, List<Integer>> posmap, char c) {
        if (c > 'z') {
            return toString(chars);
        }

        char next = (char) (c + 1);

        List<Integer> list = posmap.get(c);
        if (list == null || list.size() == 1) return removeDuplicateLetters(chars, posmap, next);

        clearChars(chars, list);
        return list.stream().map(pos -> {
            chars[pos] = c;
            String result = removeDuplicateLetters(chars, posmap, next);
            chars[pos] = '\0';
            return result;
        }).min(String::compareTo).get();
    }

    void clearChars(char[] chars, List<Integer> list) {
        for (int p : list) chars[p] = '\0';
    }

    String toString(char[] chars) {
        char[] copy = new char[chars.length];
        int pos = 0;
        for (char c : chars) {
            if (c != '\0') {
                copy[pos++] = c;
            }
        }
        return new String(Arrays.copyOf(copy, pos));
    }

    Map<Character, List<Integer>> buildPosMap(char[] chars) {
        Map<Character, List<Integer>> posmap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\0') continue;
            posmap.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }
        return posmap;
    }
}
