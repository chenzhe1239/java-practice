package com.janosgyerik.practice.oj.leetcode.hard.SubstringWithConcatenationOfAllWords;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SubstringWithConcatenationOfAllWordsTest {

    private final Solution solution = new Solution();

    @Test
    public void test_barfoothefoobarman_foo_bar() {
        assertEquals(Arrays.asList(0, 9),
                solution.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }

    @Test
    public void test_wordgoodgoodgoodbestword_word_good_best_good() {
        assertEquals(Collections.singletonList(8),
                solution.findSubstring("wordgoodgoodgoodbestword",
                        new String[]{"word","good","best","good"}));
    }
}
