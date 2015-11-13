package com.janosgyerik.practice.oj.careercup;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class MatcherWithPlaceholders {

    protected final Set<String> index = new HashSet<>();

    public MatcherWithPlaceholders(List<String> words) {
        words.forEach(this::addPermutations);
    }

    private final void addPermutations(String word) {
        char[] letters = word.toCharArray();
        for (List<Integer> starPositions : enumerateStarPositions(word.length())) {
            String wordWithStars = getWordWithStars(letters, starPositions);
            index.add(wordWithStars);
        }
    }

    private static class ListWithIndex {
        final List<Integer> list;
        final int index;

        private ListWithIndex(List<Integer> list, int index) {
            this.list = list;
            this.index = index;
        }
    }

    protected List<List<Integer>> enumerateStarPositions(int length) {
        List<List<Integer>> results = new LinkedList<>();
        results.add(Arrays.asList());

        Queue<ListWithIndex> canGrow = new LinkedList<>();
        canGrow.add(new ListWithIndex(new LinkedList<>(), 0));

        while (!canGrow.isEmpty()) {
            ListWithIndex listWithIndex = canGrow.poll();
            for (int i = listWithIndex.index; i < length - 1; ++i) {
                List<Integer> copy = new LinkedList<>(listWithIndex.list);
                copy.add(i);
                results.add(copy);
                canGrow.add(new ListWithIndex(copy, i + 1));
            }
            List<Integer> copy = new LinkedList<>(listWithIndex.list);
            copy.add(length - 1);
            results.add(copy);
        }
        return results;
    }

    private String getWordWithStars(char[] letters, List<Integer> starPositions) {
        char[] copy = letters.clone();
        for (int pos : starPositions) {
            copy[pos] = '*';
        }
        return new String(copy);
    }

    public boolean hasMatch(String word) {
        return index.contains(word);
    }

}

public class MatcherWithPlaceholdersTest {
    private MatcherWithPlaceholders matcher =
            new MatcherWithPlaceholders(Arrays.asList("hazem", "ahmed", "moustafa", "fizo"));

    private boolean hasMatch(String query) {
        return matcher.hasMatch(query);
    }

    @Test
    public void test_enumerateStarPos_1() {
        assertEquals("[[], [0]]", matcher.enumerateStarPositions(1).toString());
    }

    @Test
    public void test_enumerateStarPos_2() {
        assertEquals("[[], [0], [1], [0, 1]]",
                matcher.enumerateStarPositions(2).toString());
    }

    @Test
    public void test_enumerateStarPos_3() {
        assertEquals("[[], [0], [1], [2], [0, 1], [0, 2], [1, 2], [0, 1, 2]]",
                matcher.enumerateStarPositions(3).toString());
    }

    @Test
    public void test_ahmed() {
        assertTrue(hasMatch("ahmed"));
    }

    @Test
    public void test_m00stafa() {
        assertTrue(hasMatch("m**stafa"));
    }

    @Test
    public void test_00() {
        assertFalse(hasMatch("**"));
    }

    @Test
    public void test_0000() {
        assertTrue(hasMatch("****"));
    }

    @Test
    public void test_00000() {
        assertTrue(hasMatch("*****"));
    }

    @Test
    public void test_000000000000000() {
        assertFalse(hasMatch("***************"));
    }

    @Test
    public void test_fizoo() {
        assertFalse(hasMatch("fizoo"));
    }

    @Test
    public void test_fizd() {
        assertFalse(hasMatch("fizd"));
    }
}
