package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WordBreakTest {

    public boolean wordBreakHelper(String s, Set<String> dict) {
        int len = s.length();
        for (int pos = 1; pos <= len; ++pos) {
            String prefix = s.substring(0, pos);
            if (dict.contains(prefix)) {
                if (pos == len || wordBreakHelper(s.substring(pos), dict)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean wordBreak(String s, Set<String> dict) {
        Set<String> cleanedDict = new HashSet<>(dict);
        int maxLength = -1;
        for (String item : dict) {
            int len = item.length();
            if (len > maxLength) {
                maxLength = len;
            }
        }
        for (String item : dict) {
            StringBuilder multiple = new StringBuilder(item).append(item);
            while (multiple.length() <= maxLength) {
                cleanedDict.remove(multiple.toString());
                multiple.append(item);
            }
        }
        return wordBreakHelper(s, cleanedDict);
    }

    @Test
    public void testEmptyString() {
        assertFalse(wordBreak("", new HashSet<>(Arrays.asList("leet", "code"))));
    }

    @Test
    public void testEmptyDict() {
        assertFalse(wordBreak("hello", new HashSet<>()));
    }

    @Test
    public void testLeetCode() {
        assertTrue(wordBreak("leetcode", new HashSet<>(Arrays.asList("leet", "code"))));
    }

    @Test
    public void test_aaab() {
        assertFalse(wordBreak("aaab", new HashSet<>(Arrays.asList("aa", "aaa"))));
    }

    @Test
    public void test_aaaaaab() {
        assertFalse(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", new HashSet<>(Arrays.asList("a"))));
    }

    @Test
    public void test_aaaaaaa() {
        String word = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        Set<String> dict = new HashSet<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
        assertFalse(wordBreak(word, dict));
    }
}
