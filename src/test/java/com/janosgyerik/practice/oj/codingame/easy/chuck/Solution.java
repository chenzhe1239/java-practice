package com.janosgyerik.practice.oj.codingame.easy.chuck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SequenceItem {
    final int num;
    final int count;

    public SequenceItem(int num, int count) {
        this.num = num;
        this.count = count;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        System.out.println(encodeChuckNorrisSequence(line));
    }

    public static String encodeChuckNorrisSequence(String text) {
        List<SequenceItem> items = new ArrayList<SequenceItem>();
        boolean first = true;
        int prevNum = 0;
        int count = 0;
        for (int c : text.toCharArray()) {
            c = (c << 1) & 0xff;
            for (int i = 0; i < 7; ++i) {
                if (first) {
                    first = false;
                    prevNum = c & 0x80;
                    c = (c << 1) & 0xff;
                    count = 1;
                    continue;
                }
                int x = c & 0x80;
                c = (c << 1) & 0xff;
                if (x == prevNum) {
                    ++count;
                } else {
                    items.add(new SequenceItem(prevNum, count));
                    prevNum = x;
                    count = 1;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (SequenceItem item : items) {
            if (item.num == 0) {
                builder.append("00 ");
            } else {
                builder.append("0 ");
            }
            for (int i = 0; i < item.count; ++i) {
                builder.append("0");
            }
            builder.append(" ");
        }
        if (prevNum == 0) {
            builder.append("00 ");
        } else {
            builder.append("0 ");
        }
        for (int i = 0; i < count; ++i) {
            builder.append("0");
        }
        return builder.toString();
    }

}
