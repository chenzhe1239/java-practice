package com.janosgyerik.practice.oj.codingame.skynet;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Bike2Test {

    // X                1   1
    // X 1              2   2
    // X 22 1           3   4
    // X 333 22 1       4   7
    // X 4444 333 22 1  5   11  sum 1..n = 1 3 6 10 15 21 28 36 = n * ( n + 1 ) / 2 = given S, n = ? < (n + 1)^2 / 2
    //                                     1 2 3 4  5  6  7  8  = 5 -> 15, 6 -> 21  = n * n / 2 + n / 2
    private static Map<Integer, Integer> speedToLanding = new HashMap<Integer, Integer>();

    private int computeMaxSpeedForLanding(int landing) {
        int speed;
        for (speed = 1; speed < Math.sqrt(2 * landing) + 1; ++speed) {
            int landingNeeded = speed * (speed - 1) / 2 + 1;
            //speedToLanding.put(speed, landingNeeded);
            if (landingNeeded > landing) {
                --speed;
                break;
            }
            if (landingNeeded == landing) {
                break;
            }
        }
        return speed;
    }

    @Test
    public void testComputeMaxSpeed() {
        Assert.assertEquals("if landing=1", 1, computeMaxSpeedForLanding(1));
        Assert.assertEquals("if landing=2", 2, computeMaxSpeedForLanding(2));
        Assert.assertEquals("if landing=3", 2, computeMaxSpeedForLanding(3));
        Assert.assertEquals(3, computeMaxSpeedForLanding(4));
        Assert.assertEquals(3, computeMaxSpeedForLanding(5));
        Assert.assertEquals(3, computeMaxSpeedForLanding(6));
        Assert.assertEquals(4, computeMaxSpeedForLanding(7));
    }

    // test 1 input: road = 16, gap = 2
    // 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 GG GG
    //  1  2  2  3  3  3  x  x  x  y  y  y  z  z  z
    // test 2 input: road = 11, gap = 3
    // 0  1  2 3  4 5 6  7 8 9 10  G G G
    // 0+ 1+ 2 2+ 3 3 3+ 4 4 4  4+
    private String computeAction(int road, int gap, int landing, int speed, int x) {
        if (x > road) {
            return "SLOW";
        }
        int minSpeed = gap + 1;
        if (speed < minSpeed) {
            return "SPEED";
        }
        if (x + speed >= road + gap) {
            return "JUMP";
        }
        int maxSpeed = computeMaxSpeedForLanding(landing);
        if (speed > maxSpeed) {
            return "SLOW";
        }
        if (x + speed + 1 < road && speed < maxSpeed) {
            return "SPEED";
        }
        if (x + speed >= road) {
            return "SLOW";
        }
        return "WAIT";
    }

    private static final char ROAD = '.';
    private static final char GAP = '0';

    private static class GapInfo {
        private final int start;
        private final int size;
        private final int lane;

        public GapInfo(int start, int size, int lane) {
            this.start = start;
            this.size = size;
            this.lane = lane;
        }
    }

    private static String computeAction(int minToSurvive, char[][] road, int speed, int[] allX, int[] y, int[] states) {
        int x = getSurvivedX(allX, states);
        GapInfo gapInfo = nextMostSignificantGap(road, x);
        int minSpeed = gapInfo.size + 1;
        if (speed < minSpeed || x > gapInfo.start) {
            return "SPEED";
        }
        if (x + speed >= gapInfo.start + gapInfo.size) {
            return "JUMP";
        }
        if (shouldSlowDown(x, speed, gapInfo)) {
            return "SLOW";
        }
//        int maxSpeed = computeMaxSpeedForLanding(landing);
//        if (speed > maxSpeed) {
//            return "SLOW";
//        }
//        if (x + speed + 1 < gapInfo.start && speed < maxSpeed) {
        if (x + speed + 1 < gapInfo.start) {
            return "SPEED";
        }
//        if (x + speed >= road) {
//            return "SLOW";
//        }
        return "WAIT";
    }

    // if we reduce until min speed and survive the gap, then we should
    private static boolean shouldSlowDown(int x, int speed, GapInfo gapInfo) {
        int i = x;
        for (; i < gapInfo.start && speed > gapInfo.size + 1; --speed, i += speed);
        return i < gapInfo.start && i + speed >= gapInfo.start + gapInfo.size;
    }

    @Test
    public void testShouldSlowDown() {
        Assert.assertTrue(shouldSlowDown(0, 8, new GapInfo(14, 5, 0)));
    }

    private static GapInfo nextMostSignificantGap(char[][] road, int x) {
        for (int i = x + 1; i < road[0].length; ++i) {
            for (int lane = 0; lane < road.length; ++lane) {
                if (road[lane][i] == GAP) {
                    return new GapInfo(i, gapSize(road, lane, i), lane);
                }
            }
        }
        return new GapInfo(0, 0, 0);
    }

    private static int gapSize(char[][] road, int lane, int start) {
        int length = 1;
        for (int i = start + length; i < road[lane].length && road[lane][i] == GAP; ++i, ++length);
        return length;
    }

    @Test
    public void testGapSize() {
        Assert.assertEquals(2, gapSize(new char[][]{ new char[]{ ROAD, GAP, GAP, ROAD }}, 0, 1));
        Assert.assertEquals(2, gapSize(new char[][]{ new char[]{ ROAD, GAP, GAP }}, 0, 1));
        Assert.assertEquals(2, gapSize(new char[][]{ new char[]{ GAP, GAP }}, 0, 0));
        Assert.assertEquals(1, gapSize(new char[][]{ new char[]{ GAP, ROAD, GAP }}, 0, 0));
        Assert.assertEquals(1, gapSize(new char[][]{ new char[]{ GAP, ROAD, GAP }}, 0, 2));
        Assert.assertEquals(2, gapSize(new char[][]{ new char[]{ GAP, ROAD, GAP, GAP }}, 0, 2));
    }

    private static int getSurvivedX(int[] x, int[] states) {
        for (int i = 0; i < states.length; ++i) {
            if (states[i] == 1) {
                return x[i];
            }
        }
        return 0;
    }
}
