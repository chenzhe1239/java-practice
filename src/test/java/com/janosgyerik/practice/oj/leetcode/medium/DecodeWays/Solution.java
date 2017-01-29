package com.janosgyerik.practice.oj.leetcode.medium.DecodeWays;

public class Solution {
    public int numDecodings(String s) {
        if (s.isEmpty()) return 0;
        char[] chars = s.toCharArray();
        if (!isValid(chars)) return 0;
        return numDecodings(chars, 0);
    }

    private boolean isValid(char[] chars) {
        char prev = '3';
        for (char c : chars) {
            if (c == '0' && (prev > '2' || prev == '0')) return false;
            prev = c;
        }
        return true;
    }

    private int numDecodings(char[] chars, int pos) {
        try {
            return numDecodings(chars, pos, true);
        } catch (InvalidEncodingException e) {
            return 0;
        }
    }

    private static class InvalidEncodingException extends Exception { }

    private int numDecodings(char[] chars, int pos, boolean canUseSingleDigit) throws InvalidEncodingException {
        if (chars[pos] == '0') {
            throw new InvalidEncodingException();
        }
        if (isOneDigit(chars, pos)) {
            return canUseSingleDigit ? 1 : 0;
        }
        if (isTwoDigits(chars, pos)) {
            if (mustBeTwoDigits(chars, pos)) {
                return 1;
            }
            if (canBeTwoDigits(chars, pos)) {
                return canUseSingleDigit ? 2 : 1;
            }
            return canUseSingleDigit ? 1 : 0;
        }
        if (mustBeTwoDigits(chars, pos)) {
            return numDecodings(chars, pos + 2);
        }
        if (canBeTwoDigits(chars, pos)) {
            if (!canUseSingleDigit) {
                return numDecodings(chars, pos + 2);
            }
            int count = 0;
            count += 2 * numDecodings(chars, pos + 2);
            count += numDecodings(chars, pos + 1, false);
            return count;
        }
        return canUseSingleDigit ? numDecodings(chars, pos + 1) : 0;
    }

    private boolean canBeTwoDigits(char[] chars, int pos) {
        return chars[pos] == '1' || chars[pos] == '2' && chars[pos + 1] <= '6';
    }

    private boolean mustBeTwoDigits(char[] chars, int pos) {
        return chars[pos + 1] == '0';
    }

    private boolean isTwoDigits(char[] chars, int pos) {
        return pos + 2 == chars.length;
    }

    private boolean isOneDigit(char[] chars, int pos) {
        return pos + 1 == chars.length;
    }
}
