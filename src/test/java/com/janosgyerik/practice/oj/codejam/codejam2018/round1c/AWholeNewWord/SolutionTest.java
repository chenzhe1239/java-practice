package com.janosgyerik.practice.oj.codejam.codejam2018.round1c.AWholeNewWord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void test_solve() {
        String input = "5\n" +
            "4 1\n" +
            "A\n" +
            "B\n" +
            "C\n" +
            "D\n" +
            "4 2\n" +
            "WW\n" +
            "AA\n" +
            "SS\n" +
            "DD\n" +
            "4 2\n" +
            "AA\n" +
            "AB\n" +
            "BA\n" +
            "BB\n" +
            "3 4\n" +
            "CAKE\n" +
            "TORN\n" +
            "SHOW\n" +
            "5 7\n" +
            "HELPIAM\n" +
            "TRAPPED\n" +
            "INSIDEA\n" +
            "CODEJAM\n" +
            "FACTORY";
        solution.solve(new Scanner(input));
    }

    @Test
    public void test_1() {
        List<String> words = new ArrayList<>(Arrays.asList(
            "AAA",
            "AAB",
            "AAC",
            "ABA",
            "ABB",
            "ABC",
            "ACA",
            "ACB",
            "ACC",
            "BAA",
            "BAB",
            "BAC",
            "BBA",
            "BBB",
            "BBC",
            "BCA",
            "BCB",
            "BCC",
            "CAA",
            "CAB",
            "CAC",
            "CBA",
            "CBB",
            "CBC",
            "CCA",
            "CCB",
            "CCC"
        ));
        assertThat(solution.findNewWord(words, 3)).isNull();
        words.remove(25);
        assertThat(solution.findNewWord(words, 3)).isEqualTo("CCB");
    }

    @Test
    public void test_brute() {
        Set<String> all = new HashSet<>();
        char[] chars = new char[2];
        for (int i = 0; i < 26; i++) {
            chars[0] = (char) ('A' + i);
            for (int j = 0; j < 26; j++) {
                chars[1] = (char) ('A' + j);
                all.add(new String(chars));
            }
        }

        assertThat(all.size()).isEqualTo(26 * 26);

        assertThat(solution.findNewWord(new ArrayList<>(all), 2)).isNull();

        for (int i = 0; i < 26 * 26; i++) {
            List<String> list = new ArrayList<>(all);
            String word = list.remove(i);
            assertThat(solution.findNewWord(list, 2)).isEqualTo(word);
        }
    }

}
