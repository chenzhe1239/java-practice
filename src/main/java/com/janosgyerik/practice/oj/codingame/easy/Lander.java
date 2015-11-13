package com.janosgyerik.practice.oj.codingame.easy;

import java.util.*;
import java.io.*;
import java.math.*;

public class Lander {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int num = in.nextInt();
        int prevX = 0;
        int prevY = -1;
        int targetX1 = 0;
        int targetX2 = 0;
        for (int i = 0; i < num; ++i) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (y == prevY) {
                targetX1 = prevX;
                targetX2 = x;
            }
            prevX = x;
            prevY = y;
        }
        
        int edge = (targetX2 - targetX1) / 4;

        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            int hs = in.nextInt();
            int vs = in.nextInt();
            int f = in.nextInt();
            int r = in.nextInt();
            int p = in.nextInt();

            int targetR, targetP = 0;
            if (x < targetX1) {
                targetR = r - 15;
                targetP = increaseP(p);
            } else if (x > targetX2) {
                targetR = r + 15;
                targetP = increaseP(p);
            } else if (x < targetX1 + edge) {
                targetR = r - 15;
                targetP = reduceP(p);
            } else if (x > targetX2 - edge) {
                targetR = r + 15;
                targetP = reduceP(p);
            } else {
                targetR = 0;
                if (vs < -30) {
                    targetP = increaseP(p);
                } else if (vs > -10) {
                    targetP = reduceP(p);
                }
            }
            System.out.println(targetR + " " + targetP);
        }
    }
    
    private static int increaseP(int p) {
        return Math.min(4, p + 1);
    }
    private static int reduceP(int p) {
        return Math.max(0, p - 1);
    }
}
