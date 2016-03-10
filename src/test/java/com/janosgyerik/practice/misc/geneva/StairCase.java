package com.janosgyerik.practice.misc.geneva;

import org.junit.Test;

import java.util.Arrays;

public class StairCase {
    static void stairCase(int n) {
        char[] markers = new char[n];
        Arrays.fill(markers, ' ');
        for (int i = 1; i <= n; ++i) {
            markers[n - i] = '#';
            System.out.println(new String(markers));
        }
    }

    @Test
    public void stairCase() {
        stairCase(6);
    }
}
