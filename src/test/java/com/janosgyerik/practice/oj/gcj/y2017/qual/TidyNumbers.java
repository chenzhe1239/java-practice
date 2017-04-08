package com.janosgyerik.practice.oj.gcj.y2017.qual;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TidyNumbers {
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

    String solve(String input) {
        return solve(new Scanner(input));
    }

    private String solve(Scanner scanner) {
        int T = scanner.nextInt();
        scanner.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            String num = scanner.nextLine();
            sb.append("Case #").append(i).append(": ").append(findLastTidy(num)).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String answer = new TidyNumbers().solve(new Scanner(Paths.get("/tmp/input.txt")));
        System.out.println(answer);
        try (FileWriter writer = new FileWriter("/tmp/output.txt")) {
            writer.write(answer);
        }
    }
}
