package com.janosgyerik.practice.oj.leetcode.hard.MinimumWindowSubstring;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Solution {
    public String minWindow(String s, String t) {
        String min = "";

        Tracker tracker = new Tracker(s, t);

        for (char c : s.toCharArray()) {
            tracker.add(c);
            if (tracker.isWindowComplete()) {
                String window = tracker.getWindow();
                if (min.isEmpty() || window.length() < min.length()) {
                    min = window;
                }
                tracker.advance();
            }
        }
        return min;
    }

    private static class Tracker {
        private final String source;

        private final Map<Integer, Long> targetCounts;
        private final Map<Integer, Long> runningCounts = new HashMap<>();
        private final SortedSet<CharPos> sortedPos = new TreeSet<>((p1, p2) -> Integer.compare(p1.pos, p2.pos));
        private int pos = -1;

        private static class CharPos {
            private final char c;
            private final int pos;

            private CharPos(char c, int pos) {
                this.c = c;
                this.pos = pos;
            }
        }

        public Tracker(String source, String target) {
            this.source = source;
            targetCounts = target.chars()
                    .boxed()
                    .collect(groupingBy(Function.identity(), counting()));
        }

        void add(char c) {
            ++pos;
            Long count = targetCounts.get((int) c);
            if (count == null) {
                return;
            }
            if (count.equals(runningCounts.get((int) c))) {
                removeFirstSeen(c);
            } else {
                incrementCount(c);
            }
            sortedPos.add(new CharPos(c, pos));
        }

        private void removeFirstSeen(char c) {
            Iterator<CharPos> iterator = sortedPos.iterator();
            while (iterator.hasNext()) {
                CharPos item = iterator.next();
                if (item.c == c) {
                    iterator.remove();
                    break;
                }
            }
        }

        private void incrementCount(int c) {
            Long runningCount = runningCounts.get(c);
            if (runningCount == null) {
                runningCounts.put(c, 1L);
            } else {
                runningCounts.put(c, runningCount + 1);
            }
        }

        boolean isWindowComplete() {
            return runningCounts.equals(targetCounts);
        }

        public String getWindow() {
            int start = sortedPos.first().pos;
            int end = pos + 1;
            return source.substring(start, end);
        }

        public void advance() {
            CharPos first = sortedPos.first();
            sortedPos.remove(first);
            runningCounts.put((int) first.c, runningCounts.get((int) first.c) - 1);
        }
    }
}
