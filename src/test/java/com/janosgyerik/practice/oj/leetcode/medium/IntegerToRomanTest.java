package com.janosgyerik.practice.oj.leetcode.medium;

public class IntegerToRomanTest {
    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < num / 1000; ++i) {
            builder.append("M");
        }
        num %= 1000;

        if (num / 900 > 0) {
            builder.append("CM");
            num %= 900;
        }

        if (num / 500 > 0) {
            builder.append("D");
            num %= 500;
        }

        if (num / 400 > 0) {
            builder.append("CD");
            num %= 400;
        }

        for (int i = 0; i < num / 100; ++i) {
            builder.append("C");
        }
        num %= 100;

        if (num / 90 > 0) {
            builder.append("XC");
            num %= 90;
        }

        if (num / 50 > 0) {
            builder.append("L");
            num %= 50;
        }

        if (num / 40 > 0) {
            builder.append("XL");
            num %= 40;
        }

        for (int i = 0; i < num / 10; ++i) {
            builder.append("X");
        }
        num %= 10;

        if (num / 9 > 0) {
            builder.append("IX");
            num %= 9;
        }

        if (num / 5 > 0) {
            builder.append("V");
            num %= 5;
        }

        if (num / 4 > 0) {
            builder.append("IV");
            num %= 4;
        }

        for (int i = 0; i < num; ++i) {
            builder.append("I");
        }

        return builder.toString();
    }
}
