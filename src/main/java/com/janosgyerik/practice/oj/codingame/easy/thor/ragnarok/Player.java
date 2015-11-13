package com.janosgyerik.practice.oj.codingame.easy.thor.ragnarok;

import java.util.Scanner;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int lx = in.nextInt();
        int ly = in.nextInt();
        int tx = in.nextInt();
        int ty = in.nextInt();

        while (true) {
            if (ly < ty) {
                System.out.print("N");
                --ty;
            } else if (ly > ty) {
                System.out.print("S");
                ++ty;
            }
            if (lx < tx) {
                System.out.print("W");
                --tx;
            } else if (lx > tx) {
                System.out.print("E");
                ++tx;
            }
            System.out.println();
        }
    }
}
