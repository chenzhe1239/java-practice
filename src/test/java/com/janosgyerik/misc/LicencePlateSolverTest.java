package com.janosgyerik.misc;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

class LicencePlateSolver {
    private Map<LetterCount, Set<String>> index;

    private LicencePlateSolver(Map<LetterCount, Set<String>> index) {
        this.index = index;
    }

    public static LicencePlateSolver fromWordsFile(String path) throws IOException {
        return fromWords(getWordsFromFile(path));
    }

    public static LicencePlateSolver fromWords(Set<String> words) {
        return new LicencePlateSolver(buildIndex(words));
    }

    private static Map<LetterCount, Set<String>> buildIndex(Set<String> words) {
        Map<LetterCount, Set<String>> index = new HashMap<>();
        for (String word : words) {
            for (LetterCount lc : getLetterCounts(word.toCharArray())) {
                for (int i = 1; i <= lc.count; ++i) {
                    LetterCount key = new LetterCount(lc.letter, i);
                    Set<String> subset = index.get(key);
                    if (subset == null) {
                        subset = new HashSet<>();
                    }
                    subset.add(word);
                    index.put(key, subset);
                }
            }
        }
        return index;
    }

    private static Set<String> getWordsFromFile(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Set<String> words = new HashSet<>();
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
            return words;
        }
    }

    String findShortestWord(String license) {
        char[] letters = getSanitizedLetters(license);
        List<LetterCount> letterCounts = getLetterCounts(letters);
        return findShortestMatch(letterCounts);
    }

    private String findShortestMatch(List<LetterCount> letterCounts) {
        Set<String> matches = new HashSet<>();
        boolean first = true;
        for (LetterCount lc : letterCounts) {
            Set<String> nextSet = index.get(lc);
            if (nextSet == null) {
                return null;
            }
            if (first) {
                first = false;
                matches.addAll(nextSet);
            } else {
                matches.retainAll(nextSet);
            }
        }

        if (matches.isEmpty()) {
            return null;
        }

        List<String> list = new ArrayList<>(matches);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        return list.get(0);
    }

    static class LetterCount {
        final char letter;
        final int count;

        LetterCount(char letter, int count) {
            this.letter = letter;
            this.count = count;
        }

        @Override
        public String toString() {
            return "LetterCount{" +
                    "letter=" + letter +
                    ", count=" + count +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            LetterCount that = (LetterCount) o;

            if (count != that.count) {
                return false;
            }
            if (letter != that.letter) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = (int) letter;
            result = 31 * result + count;
            return result;
        }
    }

    static List<LetterCount> getLetterCounts(char[] letters) {
        int[] counts = new int[26];
        for (char c : letters) {
            ++counts[c - 'a'];
        }

        List<LetterCount> letterCounts = new LinkedList<>();
        for (int i = 0; i < counts.length; ++i) {
            int count = counts[i];
            if (count > 0) {
                char letter = (char) (i + 'a');
                letterCounts.add(new LetterCount(letter, count));
            }
        }
        return letterCounts;
    }

    private char[] getSanitizedLetters(String license) {
        return license.toLowerCase().replaceAll("[^a-z]", "").toCharArray();
    }
}

public class LicencePlateSolverTest {

    private static LicencePlateSolver solver = LicencePlateSolver
            .fromWords(new HashSet<>(Arrays.asList("hello", "hi", "szia")));

    //    @BeforeClass
    //    public static void setUpBeforeClass() throws IOException {
    //        solver = LicencePlateSolver.fromWordsFile("src/test/resources/words-barrons.txt");
    //    }

    @Test
    public void test_abc_123() {
        assertEquals(null, solver.findShortestWord("abc-123"));
    }

    @Test
    public void test_eho_123() {
        assertEquals("hello", solver.findShortestWord("eho 123"));
    }

    @Test
    public void test_echo_123() {
        assertEquals(null, solver.findShortestWord("echo 123"));
    }

    @Test
    public void test_h_123() {
        assertEquals("hi", solver.findShortestWord("h 123"));
    }

    @Test
    public void test_hx_123() {
        assertEquals(null, solver.findShortestWord("haz 123"));
    }
}
