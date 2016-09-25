package com.janosgyerik.practice.oj.codechef;

import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class GoodStringsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inputs inputs = parseInput(scanner);
        for (int i = 0; i < inputs.start.length; ++i) {
            System.out.println(solve(inputs.string, inputs.start[i], inputs.end[i], inputs.startIndex[i], inputs.endIndex[i]));
        }
    }

    private static int solve(String string, char start, char end, int startIndex, int endIndex) {
        int countOfStartLetters = 0;
        int count = 0;
        for (int pos = startIndex - 1; pos < endIndex; ++pos) {
            char c = string.charAt(pos);
            if (c == start) {
                ++countOfStartLetters;
            } else if (c == end) {
                count += countOfStartLetters;
            }
        }
        return count;
    }

    private static class Inputs {
        private final String string;
        private final char[] start;
        private final char[] end;
        private final int[] startIndex;
        private final int[] endIndex;

        private final Map<String, Integer> solutions;
        private final int minStartIndex;
        private final int maxEndIndex;

        private Inputs(String string, char[] start, char[] end, int[] startIndex, int[] endIndex) {
            this.string = string;
            this.start = start;
            this.end = end;
            this.startIndex = startIndex;
            this.endIndex = endIndex;

            solutions = new HashMap<>(start.length);
            Set<Character> startChars = new HashSet<>();
            Set<Character> endChars = new HashSet<>();
            Set<Integer> startIndexSet = new TreeSet<>();
            Set<Integer> endIndexSet = new TreeSet<>(Collections.reverseOrder());

            for (int i = 0; i < start.length; ++i) {
                String key = String.format("%s-%s-%s-%s", start[i], end[i], startIndex[i], endIndex[i]);
                solutions.put(key, 0);
                startChars.add(start[i]);
                endChars.add(end[i]);
                startIndexSet.add(startIndex[i]);
                endIndexSet.add(endIndex[i]);
            }

            this.minStartIndex = startIndexSet.iterator().next();
            this.maxEndIndex = endIndexSet.iterator().next();

            Map<Character, Integer> countOfStart = new HashMap<>();
            Map<Character, Integer> countForEnd = new HashMap<>();
            for (Character startChar : startChars) {
                countOfStart.put(startChar, 0);
            }
            for (Character endChar : endChars) {
                countForEnd.put(endChar, 0);
            }
            for (int pos = minStartIndex - 1; pos < maxEndIndex; ++pos) {
                char c = string.charAt(pos);
                if (startChars.contains(c)) {
                    int count = countOfStart.get(c);
                    countOfStart.put(c, count + 1);
                    if (endChars.contains(c)) {
                        countForEnd.put(c, countForEnd.get(c) + count);
                    }
                }
                if (endChars.contains(c)) {
                    int count = countOfStart.get(c);
                    countForEnd.put(c, countForEnd.get(c) + count);
                }
            }
        }
    }

    private static Inputs parseInput(Scanner scanner) {
        String string = scanner.nextLine();
        int lines = scanner.nextInt();
        scanner.nextLine();

        char[] start = new char[lines];
        char[] end = new char[lines];
        int[] startIndex = new int[lines];
        int[] endIndex = new int[lines];
        for (int i = 0; i < lines; ++i) {
            start[i] = scanner.next().charAt(0);
            end[i] = scanner.next().charAt(0);
            startIndex[i] = scanner.nextInt();
            endIndex[i] = scanner.nextInt();
            scanner.nextLine();
        }
        return new Inputs(string, start, end, startIndex, endIndex);
    }

    @Ignore
    @Test
    public void testInput() {
        Scanner scanner = new Scanner("checfcheff\n" +
                "5\n" +
                "c h 1 10\n" +
                "c f 1 10\n" +
                "e c 1 10\n" +
                "c f 1 5\n" +
                "c f 6 10\n");
        Inputs inputs = parseInput(scanner);
        assertEquals("checfcheff", inputs.string);
        assertEquals(5, inputs.start.length);
        assertEquals("[10, 10, 10, 5, 10]", Arrays.toString(inputs.endIndex));
        assertEquals(1, inputs.minStartIndex);
        assertEquals(10, inputs.maxEndIndex);
    }

    private static int[] multiSolve(Inputs inputs) {
        int[] outputs = new int[inputs.start.length];

        return outputs;
    }

    @Test
    public void test_checfcheff_c_h_1_10() {
        assertEquals(4, solve("checfcheff", 'c', 'h', 1, 10));
    }

    @Test
    public void test_checfcheff_c_f_1_10() {
        assertEquals(8, solve("checfcheff", 'c', 'f', 1, 10));
    }

    @Test
    public void test_checfcheff_e_c_1_10() {
        assertEquals(2, solve("checfcheff", 'e', 'c', 1, 10));
    }

    @Test
    public void test_checfcheff_c_f_1_5() {
        assertEquals(2, solve("checfcheff", 'c', 'f', 1, 5));
    }

    @Test
    public void test_checfcheff_c_f_6_10() {
        assertEquals(2, solve("checfcheff", 'c', 'f', 6, 10));
    }
}

class ChefAndStrings {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String specialString = "";

    public static void main(String[] args) {
        PrintStream out = System.out;
        try {
            specialString = br.readLine();
            int numberOfQueries = Integer.parseInt(br.readLine());

            for (int i = 0; i < numberOfQueries; i++) {
                String nextQuery = nextQuery();
                out.println(getGoodStringCounts(nextQuery));
            }
        } catch (IOException ex) {
            out.println("Exception occurred while reading input");
        }
    }

    private static String nextQuery() throws IOException {
        return br.readLine();
    }

    private static int getGoodStringCounts(String query) {
        StringTokenizer tokenizer = new StringTokenizer(query, " ");
        char startLetter = tokenizer.nextToken().charAt(0);
        char endLetter = tokenizer.nextToken().charAt(0);
        int startIndex = Integer.parseInt(tokenizer.nextToken()) - 1;
        int endIndex = Integer.parseInt(tokenizer.nextToken()) - 1;

        int[] startLetterIndices = allIndicesGreaterThanMin(startLetter, startIndex);
        int[] endLetterIndices = allIndicesLessThanMax(endLetter, endIndex);

        int total = 0;
        int beginEndIndexFrom = 0;

        for (int index : startLetterIndices) {
            for (int j = beginEndIndexFrom; j < endLetterIndices.length; j++) {
                if (endLetterIndices[j] > index) {
                    beginEndIndexFrom = j;
                    total += (endLetterIndices.length - beginEndIndexFrom);
                    break;
                }
            }
        }

        return total;
    }

    private static int[] allIndicesGreaterThanMin(char letter, int startIndex) {
        int[] indices = new int[specialString.length() / 2];
        int currentIndex = 0;
        int index = specialString.indexOf(letter, startIndex);
        while (index != -1) {
            indices[currentIndex++] = index;
            startIndex = index + 1;
            index = specialString.indexOf(letter, startIndex);
        }
        int[] result = new int[currentIndex];
        System.arraycopy(indices, 0, result, 0, result.length);
        return result;
    }

    private static int[] allIndicesLessThanMax(char letter, int endIndex) {
        int[] indices = new int[specialString.length() / 2];
        int currentIndex = 0;
        int index = specialString.indexOf(letter);
        while (index != -1 && index <= endIndex) {
            indices[currentIndex++] = index;
            index = specialString.indexOf(letter, index + 1);
        }
        int[] result = new int[currentIndex];
        System.arraycopy(indices, 0, result, 0, result.length);
        return result;
    }
}
