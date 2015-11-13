package com.janosgyerik.practice.oj.codingame.easy.horse;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int solution = new HorseRacing().diffBetweenTwoClosest(arr);
        System.out.println(solution);
    }
}

class HorseRacing {
    public int diffBetweenTwoClosest(int[] strengths) {
        TreeSet<Integer> sorted = new TreeSet<Integer>();
        for (int value : strengths) {
            sorted.add(value);
        }
        if (sorted.size() < 2) {
            return 0;
        }

        Iterator<Integer> iter = sorted.iterator();
        int first = iter.next();
        int prev = iter.next();
        int largestDiff = prev - first;
        while (iter.hasNext()) {
            int value = iter.next();
            int diff = value - prev;
            if (diff < largestDiff) {
                largestDiff = diff;
            }
            prev = value;
        }
        return largestDiff;
    }
}
