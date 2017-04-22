package com.janosgyerik.practice.oj.gcj.y2017.qual;

import com.janosgyerik.practice.oj.gcj.y2017.common.Input;
import com.janosgyerik.practice.oj.gcj.y2017.common.Inputs;
import com.janosgyerik.practice.oj.gcj.y2017.common.Problem;
import com.janosgyerik.practice.oj.gcj.y2017.common.Solver;

import java.util.Arrays;
import java.util.Scanner;

public class TidyNumbers implements Problem {
    String findLastTidy(String s) {
        char[] chars = s.toCharArray();
        int pos = findFirstUntidyPos(chars);
        if (pos < 0) return s;
        pos = findFirstSamePos(chars, pos);
        return tidy(chars, pos);
    }

    int findFirstUntidyPos(char[] chars) {
        for (int pos = 0; pos < chars.length - 1; pos++) {
            if (chars[pos] > chars[pos + 1]) {
                return pos;
            }
        }
        return -1;
    }

    int findFirstSamePos(char[] chars, int pos) {
        for (int i = pos; i > 0; i--) {
            if (chars[i - 1] != chars[pos]) {
                return i;
            }
        }
        return 0;
    }

    private String tidy(char[] chars, int pos) {
        char[] result;

        if (pos == 0 && chars[pos] == '1') {
            result = new char[chars.length - 1];
            Arrays.fill(result, '9');
        } else {
            result = chars;
            result[pos]--;
            Arrays.fill(result, pos + 1, result.length, '9');
        }
        return new String(result);
    }

    static class TidyNumbersInput implements Input {
        private final String num;

        TidyNumbersInput(String num) {
            this.num = num;
        }
    }

    @Override
    public Inputs inputs(Scanner scanner) {
        int T = scanner.nextInt();
        scanner.nextLine();

        Inputs inputs = new Inputs();
        for (int i = 1; i <= T; i++) {
            String num = scanner.nextLine();
            inputs.add(new TidyNumbersInput(num));
        }
        return inputs;
    }

    @Override
    public Solver solver(Inputs inputs) {
        return input -> () -> findLastTidy(((TidyNumbersInput) input).num);
    }
}
