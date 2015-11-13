package com.janosgyerik.practice.oj.codingame.easy.horse2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] horses = new int[n];
        for (int i = 0; i < n; i++) {
            horses[i] = in.nextInt();
        }
        System.out.println(HorseRacing.findSmallestDifference(horses));
    }
}

class HorseRacing {
    public static int findSmallestDifference(int... ints) {
        List<Integer> list = new ArrayList<>();
        for (int i : ints) {
            list.add(i);
        }
        Collections.sort(list);
        int prev = list.get(1);
        int smallestDiff = prev - list.get(0);
        for (int i = 2; i < list.size() && smallestDiff > 0; ++i) {
            int current = list.get(i);
            int diff = current - prev;
            if (diff < smallestDiff) {
                smallestDiff = diff;
            }
            prev = current;
        }
        return smallestDiff;
    }
}
