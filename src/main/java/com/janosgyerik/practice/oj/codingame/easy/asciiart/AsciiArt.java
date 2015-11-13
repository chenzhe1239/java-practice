package com.janosgyerik.practice.oj.codingame.easy.asciiart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AsciiArt {

    public List<String> toAsciiArt(String[] alphabet, String text, int width) {
        List<String> lines = new ArrayList<String>();
        for (String line : alphabet) {
            StringBuilder builder = new StringBuilder();
            for (char c : text.toUpperCase().toCharArray()) {
                int start = (c - 'A') * width;
                if (start > line.length() || start < 0) {
                    start = line.length() - width;
                }
                String segment = line.substring(start, start + width);
                builder.append(segment);
            }
            lines.add(builder.toString());
        }
        return lines;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        in.nextLine();

        String text = in.nextLine();
        String[] ascii = new String[height];
        for (int i = 0; i < ascii.length; ++i) {
            ascii[i] = in.nextLine();
        }
        for (String line : new AsciiArt().toAsciiArt(ascii, text, width)) {
            System.out.println(line);
        }
    }
}
