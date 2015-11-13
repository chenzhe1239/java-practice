package com.janosgyerik.practice.codility.easy;

public class IsPerm {
    public int submit(int[] A) {
        boolean[] B = new boolean[A.length];
        for (int i : A) {
            int j = i - 1;
            if (j >= A.length) {
                return 0;
            }
            if (B[j]) {
                return 0;
            }
            B[j] = true;
        }
        return 1;
    }
}
