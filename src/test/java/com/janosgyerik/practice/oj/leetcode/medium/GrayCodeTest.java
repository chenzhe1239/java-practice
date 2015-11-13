package com.janosgyerik.practice.oj.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrayCodeTest {
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            return Arrays.asList(0);
        }
        List<Integer> prev = grayCode(n - 1);
        List<Integer> copy = new ArrayList<Integer>(prev);
        for (int i = prev.size() - 1; i >= 0; --i) {
            int num = prev.get(i) | (1 << (n - 1));
            copy.add(num);
        }
        return copy;
    }
}
