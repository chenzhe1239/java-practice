package com.janosgyerik.practice.oj.leetcode.medium.ValidPerfectSquare;

public class Solution {
    public boolean isPerfectSquare(int num) {
        for (int i = 1; ; i++) {
            int square = i * i;
            if (square == num) {
                return true;
            }
            if (square > num || square < 0) {
                return false;
            }
        }
    }
}
