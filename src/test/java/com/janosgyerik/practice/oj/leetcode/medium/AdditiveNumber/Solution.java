package com.janosgyerik.practice.oj.leetcode.medium.AdditiveNumber;

public class Solution {
    public boolean isAdditiveNumber(String num) {
        for (int firstLen = 1; firstLen <= num.length() / 2; ++firstLen) {
            String numString = toValidNumString(num, 0, firstLen);
            if (numString == null) {
                continue;
            }
            long first = Integer.valueOf(numString);
            if (isAdditiveNumber(num, firstLen, first)) {
                return true;
            }
        }
        return false;
    }

    private String toValidNumString(String num, int start, int end) {
        if (num.charAt(start) == '0') {
            return (end - start) == 1 ? "0" : null;
        }
        return num.substring(start, end);
    }

    private boolean isAdditiveNumber(String num, int firstLen, long first) {
        for (int secondLen = 1; Math.max(firstLen, secondLen) <= num.length() - firstLen - secondLen; ++secondLen) {
            String numString = toValidNumString(num, firstLen, firstLen + secondLen);
            if (numString == null) {
                continue;
            }
            long second = Integer.valueOf(numString);
            if (isAdditiveNumber(num, firstLen + secondLen, first, second)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdditiveNumber(String num, int pos, long first, long second) {
        if (pos == num.length()) {
            return true;
        }
        long third = first + second;
        String thirdStr = String.valueOf(third);
        if (num.startsWith(thirdStr, pos)) {
            return isAdditiveNumber(num, pos + thirdStr.length(), second, third);
        }
        return false;
    }
}
