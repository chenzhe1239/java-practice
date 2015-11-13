package com.janosgyerik.practice.oj.codingame.medium.worstloss;

import java.util.Scanner;

class Solution {
    static int solution(int[] curve) {
        int worstLoss = 0;
        int prevmin = curve[0];
        int prevmax = curve[0];
        int localmax = prevmin;
        for (int i = 1; i < curve.length; ++i) {
            int current = curve[i];
            if (current < prevmin) {
                prevmin = current;
                worstLoss = prevmin - prevmax;
            } else if (current > prevmax && current > localmax) {
                localmax = current;
            }
            if (current - localmax < worstLoss) {
                prevmax = localmax;
                prevmin = current;
                worstLoss = prevmin - prevmax;
            }
        }
        return worstLoss;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        in.nextLine();
        String vs = in.nextLine();

        String[] strValues = vs.split(" ");
        int[] curve = new int[strValues.length];
        for (int i = 0; i < strValues.length; ++i) {
            curve[i] = Integer.parseInt(strValues[i]);
        }

        System.out.println(solution(curve));
    }
}
