package com.janosgyerik.practice.codility.easy;

public class CarsCount {
    final int LIMIT = 1000 * 1000 * 1000;
    int submit(int[] arr) {
        int count = 0;
        int east = 0;
        for (int i : arr) {
            if (i == 0) {
                ++east;
            } else {
                count += east;
                if (count > LIMIT) {
                    return -1;
                }
            }
        }
        return count;
    }
}
