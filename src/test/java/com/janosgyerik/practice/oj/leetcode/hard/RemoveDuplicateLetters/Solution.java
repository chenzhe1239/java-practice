package com.janosgyerik.practice.oj.leetcode.hard.RemoveDuplicateLetters;

import java.util.*;

public class Solution {

    public String removeDuplicateLetters(String s) {
        return removeDuplicateLetters(buildPosMap(s.toCharArray()));
    }

    private String removeDuplicateLetters(SortedMap<Character, List<Integer>> posmap) {
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        while (!posmap.isEmpty()) {
            for (Map.Entry<Character, List<Integer>> entry : posmap.entrySet()) {
                char c = entry.getKey();
                List<Integer> poslist = entry.getValue();
                int index = index(poslist, pos);
                int nextPos = poslist.get(index);
                if (index < poslist.size() && allExistAfterPos(posmap, nextPos)) {
                    sb.append(c);
                    posmap.remove(c);
                    pos = nextPos;
                    break;
                }
            }
        }
        return sb.toString();
    }

    private boolean allExistAfterPos(SortedMap<Character, List<Integer>> posmap, int pos) {
        return posmap.values().stream().allMatch(list -> list.get(list.size() - 1) >= pos);
    }

    private int index(List<Integer> poslist, int pos) {
        int ret = Collections.binarySearch(poslist, pos);
        if (ret >= 0) return ret;
        return -1 - ret;
    }

    SortedMap<Character, List<Integer>> buildPosMap(char[] chars) {
        SortedMap<Character, List<Integer>> posmap = new TreeMap<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\0') continue;
            posmap.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }
        return posmap;
    }
}
