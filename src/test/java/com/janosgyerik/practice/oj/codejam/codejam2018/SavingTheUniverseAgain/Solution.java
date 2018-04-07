package com.janosgyerik.practice.oj.codejam.codejam2018.SavingTheUniverseAgain;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        new Solution().solve(new Scanner(System.in));
    }

    void solve(Scanner scanner) {
        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            int shield = scanner.nextInt();
            String program = scanner.next().trim();
            int minSwaps = findMinSwaps(program, shield);
            System.out.printf("Case #%d: %s\n", i + 1, minSwaps > -1 ? minSwaps : "IMPOSSIBLE");
        }
    }

    int findMinSwaps(String program, int shield) {
        char[] commands = program.toCharArray();
        int[] charges = computeCharges(commands);
        int[] shotsAfter = computeShotsAfter(commands);
        return findMinSwaps(shield, commands, charges, shotsAfter);
    }

    private int findMinSwaps(int shield, char[] commands, int[] charges, int[] shotsAfter) {
        int damage = computeDamage(commands);
        int swaps = 0;
        for (int i = commands.length - 1; i >= 0; i--) {
            if (damage <= shield) return swaps;
            if (charges[i] == 0) continue;
            int power = charges[i] / 2;
            int minSwaps = computeMinSwaps(damage, shield, power, shotsAfter[i]);
            swaps += minSwaps;
            damage -= minSwaps * power;
            if (damage <= shield) return swaps;
        }
        return -1;
    }

    private int computeMinSwaps(int damage, int shield, int power, int shotsAfter) {
        int shotsNeeded = (damage - shield) / power;
        if ((damage - shield) % power > 0) {
            shotsNeeded++;
        }
        return Math.min(shotsNeeded, shotsAfter);
    }

    private int[] computeCharges(char[] commands) {
        int[] charges = new int[commands.length];
        int power = 1;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == 'C') {
                power <<= 1;
                charges[i] = power;
            }
        }
        return charges;
    }

    private int[] computeShotsAfter(char[] commands) {
        int[] shots = new int[commands.length];
        int count = 0;
        for (int i = commands.length - 1; i >= 0; i--) {
            if (commands[i] == 'S') {
                count++;
            }
            shots[i] = count;
        }
        return shots;
    }

    int computeDamage(String program) {
        return computeDamage(program.toCharArray());
    }

    private int computeDamage(char[] steps) {
        int total = 0;
        int power = 1;
        for (char c : steps) {
            if (c == 'S') {
                total += power;
            } else if (c == 'C') {
                power *= 2;
            }
        }
        return total;
    }
}
