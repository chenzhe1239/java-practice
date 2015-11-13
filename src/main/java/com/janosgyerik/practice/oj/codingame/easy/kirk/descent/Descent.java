package com.janosgyerik.practice.oj.codingame.easy.kirk.descent;

import java.util.Scanner;

class Player {
    static final int MOUNTAINS = 8;

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int nextTarget = 0;
        int prevx = 0;
        while (true) {
            int sx = scanner.nextInt();
            scanner.nextInt();

            if (sx == prevx) {
                // just turned around
                nextTarget = getNextTarget(readInts(scanner, MOUNTAINS));
            } else {
                readAndIgnoreInts(scanner, MOUNTAINS);
            }

            System.out.println(sx == nextTarget ? "FIRE" : "HOLD");
            prevx = sx;
        }
    }

    static int getNextTarget(int[] heights) {
        int nextTarget = 0;
        int highest = heights[0];
        for (int i = 1; i < heights.length; ++i) {
            int height = heights[i];
            if (height > highest) {
                highest = height;
                nextTarget = i;
            }
        }
        return nextTarget;
    }

    private static int[] readInts(Scanner scanner, int count) {
        int[] ints = new int[count];
        for (int i = 0; i < count; ++i) {
            ints[i] = scanner.nextInt();
        }
        return ints;
    }

    private static void readAndIgnoreInts(Scanner scanner, int count) {
        for (int i = 0; i < count; ++i) {
            scanner.nextInt();
        }
    }
}