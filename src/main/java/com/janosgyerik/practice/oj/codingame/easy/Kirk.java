package com.janosgyerik.practice.oj.codingame.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Kirk {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        boolean right = true;

        while (true) {
            int sx = in.nextInt();
            in.nextInt();  // sy

            Map<Integer, Integer> mt = new HashMap<Integer, Integer>();
            for (int i = 0; i < 8; ++i) {
                mt.put(i, in.nextInt());
            }

            int highestPos = getHighestPos(mt, sx, right);
            if (sx == highestPos) {
                System.out.println("FIRE");
            } else {
                System.out.println("HOLD");
            }

            switch (sx) {
                case 0:
                    right = true;
                    break;
                case 7:
                    right = false;
                    break;
            }
        }
    }

    private static int getHighestPos(Map<Integer, Integer> mt, int sx, boolean isRight) {
        int max = 0;
        int index = sx;
        if (isRight) {
            for (int i = sx; i < 8; ++i) {
                if (mt.get(i) > max) {
                    index = i;
                    max = mt.get(i);
                }
            }
        } else {
            for (int i = sx; i >= 0; --i) {
                if (mt.get(i) > max) {
                    index = i;
                    max = mt.get(i);
                }
            }
        }
        return index;
    }
}
