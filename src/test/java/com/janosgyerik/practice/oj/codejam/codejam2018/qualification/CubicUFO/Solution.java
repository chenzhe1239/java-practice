package com.janosgyerik.practice.oj.codejam.codejam2018.qualification.CubicUFO;

import java.util.Scanner;

public class Solution {

    public static final double DELTA = 1e-9;

    public static void main(String[] args) {
        new Solution().solve(new Scanner(System.in));
    }

    void solve(Scanner scanner) {
        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            double target = scanner.nextDouble();
            double a = computeA(target);
            double b = computeB(a);
            System.out.printf("Case #%d:\n", (i+1));
            System.out.printf("%e %e 0\n", a / 2, b / 2);
            System.out.printf("%e %e 0\n", -b / 2, a / 2);
            System.out.println("0 0 0.5");
        }
    }

    private double computeA(double target) {
        double guess = 0.1;
        while (!isGoodEnough(guess, target)) {
            guess = nextGuess(guess, target);
        }
        return guess;
    }

    private double nextGuess(double guess, double target) {
        double b = computeB(guess);
        return target - b;
    }

    private boolean isGoodEnough(double guess, double target) {
        double b = computeB(guess);
        return Math.abs(target - guess - b) < DELTA;
    }

    private double computeB(double a) {
        return Math.sqrt(1 - a * a);
    }

}
