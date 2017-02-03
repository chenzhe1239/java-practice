package com.janosgyerik.practice.oj.leetcode.hard.PerfectRectangle;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public boolean isRectangleCover(int[][] rectangles_) {
        List<Rectangle> rectangles = Stream.of(rectangles_).map(Rectangle::new).collect(Collectors.toList());
        return areaAddsUp(rectangles_) && !overlap(rectangles);
    }

    boolean areaAddsUp(int[][] rectangles) {
        int xleft = Integer.MAX_VALUE;
        int xbottom = Integer.MAX_VALUE;
        int xright = Integer.MIN_VALUE;
        int xtop = Integer.MIN_VALUE;
        int area = 0;
        for (int[] rect : rectangles) {
            int left = rect[0];
            int bottom = rect[1];
            int right = rect[2];
            int top = rect[3];
            area += (right - left) * (top - bottom);
            xleft = Math.min(xleft, left);
            xright = Math.max(xright, right);
            xbottom = Math.min(xbottom, bottom);
            xtop = Math.max(xtop, top);
        }
        return area == (xright - xleft) * (xtop - xbottom);
    }

    private boolean overlap(List<Rectangle> rectangles) {
        rectangles.sort(Comparator.comparingInt(r -> r.left));
        while (rectangles.size() > 1) {
            Rectangle pivot = rectangles.remove(rectangles.size() - 1);
            for (int i = rectangles.size() - 1; i >= 0; i--) {
                Rectangle other = rectangles.get(i);
                if (other.right < pivot.left) break;
                if (overlap(pivot, other)) {
                    return true;
                }
            }
        }
        return false;
    }

    static class Rectangle {
        final int bottom, left, top, right;

        Rectangle(int... r) {
            left = r[0];
            bottom = r[1];
            right = r[2];
            top = r[3];
        }
    }

    boolean overlap(Rectangle r1, Rectangle r2) {
        if (r1.left >= r2.right) return false;
        //if (r1.right <= r2.left) return false;
        if (r1.top <= r2.bottom) return false;
        if (r1.bottom >= r2.top) return false;
        return true;
    }
}
