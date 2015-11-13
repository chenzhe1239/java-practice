package com.janosgyerik.practice.codility.easy;

public class TapeMinDiff {
    public int minDiff(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int acc = arr[0];
        sum -= acc;
        int mindiff = Math.abs(acc - sum);
        for (int i = 1; i < arr.length - 1; ++i) {
            acc += arr[i];
            sum -= arr[i];
            int diff = Math.abs(acc - sum);
            if (diff < mindiff) {
                mindiff = diff;
            }
        }
        return mindiff;
    }

    public int submit(int[] arr) {
        return minDiff(arr);
    }
}
