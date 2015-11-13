package com.janosgyerik.practice.oj.codingame.easy.temperatures;


import java.util.Scanner;

class Solution {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Temperatures.findClosest(scanner));
    }
}

class Temperatures {
    public static int findClosest(Scanner scanner) {
        int n = scanner.nextInt();
        if (n == 0) {
            return 0;
        }
        int closest = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            int x = scanner.nextInt();
            if (closest > 0) {
                if (Math.abs(x) < closest) {
                    closest = x;
                }
            } else if (Math.abs(x) <= -closest) {
                closest = x;
            }
        }
        return closest;
    }
}
