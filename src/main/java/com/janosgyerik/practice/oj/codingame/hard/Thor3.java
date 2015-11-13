package com.janosgyerik.practice.oj.codingame.hard;

import java.util.*;
import java.io.*;
import java.math.*;

public class Thor3 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read init information from standard input, if any
        int tx = in.nextInt();
        int ty = in.nextInt();

        while (true) {
            // Read information from standard input
            int h = in.nextInt();
            int n = in.nextInt();
            int sumX = 0;
            int sumY = 0;
            int minDX = 10;
            int minDY = 10;
            for (int i = 0; i < n; ++i) {
                int x = in.nextInt();
                int y = in.nextInt();
                sumX += x;
                sumY += y;
                minDX = Math.min(minDX, Math.abs(tx - x));
                minDY = Math.min(minDY, Math.abs(ty - y));
            }
            int lx = sumX / n;
            int ly = sumY / n;
            
            if (minDX < 3 && minDY < 3) {
                System.out.println("STRIKE");
                continue;
            }
            
            String move = "";
            if (ty < ly) {
                move = "S";
                ++ty;
            } else if (ty > ly) {
                move = "N";
                --ty;
            }
            if (tx < lx) {
                move += "E";
                ++tx;
            } else if (tx > lx) {
                move += "W";
                --tx;
            }
            
            if (move.isEmpty()) {
                System.out.println("WAIT");
            } else {
                System.out.println(move);
            }
        }
    }
}
