package com.janosgyerik.practice.codility.easy;

public class BinGap {
    public int longestSeqOfZeros(int n) {
        if (n < 2) {
            return 0;
        }
        int x = n;
        int longest = 0;
        int streak = 0;
        while (x > 0) {
            if ((x & 1) == 0) {
                ++streak;
            } else {
                longest = Math.max(streak, longest);
                streak = 0;
            }
            x >>= 1;
        }
        return longest;
    }

    public int longestGapOfZeros(int n) {
        if (n < 2) {
            return 0;
        }
        int x = n;
        int longest = 0;
        int streak = 0;
        while (x > 0 && (x & 1) == 0) {
            x >>= 1;
        }
        while (x > 0) {
            if ((x & 1) == 0) {
                ++streak;
            } else {
                longest = Math.max(streak, longest);
                streak = 0;
            }
            x >>= 1;
        }
        return longest;
    }
}
