package com.janosgyerik.practice.oj.codejam.codejam2018.round1c.AWholeNewWord;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) {
        new Solution().solve(new Scanner(System.in));
    }

    void solve(Scanner scanner) {
        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            int N = scanner.nextInt();
            int L = scanner.nextInt();
            scanner.nextLine();
            List<String> words = IntStream.range(0, N).mapToObj(j -> scanner.nextLine()).collect(Collectors.toList());
            String word = findNewWord(words, L);
            System.out.printf("Case #%d: %s\n", (i + 1), word == null ? "-" : word);
        }
    }

    String findNewWord(List<String> words, int L) {
        if (L == 1) return null;

        Set<Character> chars1 = new HashSet<>();
        Set<Character> chars2 = new HashSet<>();
        words.forEach(word -> {
            chars1.add(word.charAt(0));
            chars2.add(word.charAt(1));
        });

        Set<String> known = new HashSet<>();
        known.addAll(words);

        char[] chars = new char[2];
        for (char c1 : chars1) {
            for (char c2 : chars2) {
                chars[0] = c1;
                chars[1] = c2;
                String candidate = new String(chars);
                if (!known.contains(candidate)) return candidate;
            }
        }
        return null;
    }

    String findNewWord2(List<String> words, int L) {
        Stats stats = new Stats(words, L);
        if (!canFindNewWord(stats)) return null;
        return findNewWord(stats);
    }

    private String findNewWord(Stats stats) {
        char[] chars = start(stats);

        PriorityQueue<Integer> indexes = new PriorityQueue<>((a, b) -> -Integer.compare(stats.chars.get(a).size(), stats.chars.get(b).size()));
        indexes.addAll(IntStream.range(0, chars.length).boxed().collect(Collectors.toList()));

        Set<String> known = new HashSet<>();
        known.addAll(stats.words);

        return findNewWord(chars, indexes, stats, known);
    }

    private String findNewWord(char[] chars, PriorityQueue<Integer> indexes, Stats stats, Set<String> known) {
        String current = new String(chars);
        if (!known.contains(current)) {
            return current;
        }
        if (indexes.isEmpty()) return null;

        int index = indexes.poll();
        for (Character c : stats.chars.get(index)) {
            chars[index] = c;
            String next = findNewWord(chars, indexes, stats, known);
            if (next != null) return next;
        }
        indexes.add(index);
        return null;
    }

    private char[] start(Stats stats) {
        char[] start = new char[stats.chars.size()];
        IntStream.range(0, start.length).forEach(i -> start[i] = stats.chars.get(i).iterator().next());
        return start;
    }

    static class Stats {
        private final List<String> words;
        List<Set<Character>> chars = new ArrayList<>();

        public Stats(List<String> words, int L) {
            this.words = words;
            IntStream.range(0, L).forEach(i -> chars.add(new HashSet<>()));
            words.forEach(word -> IntStream.range(0, L).forEach(i -> chars.get(i).add(word.charAt(i))));
        }
    }

    private boolean canFindNewWord(Stats stats) {
        int combinations = 1;
        for (int i = 0; i < stats.chars.size(); i++) {
            combinations *= stats.chars.get(i).size();
            if (combinations > stats.words.size()) return true;
        }
        return false;
    }
}
