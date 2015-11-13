package com.janosgyerik.practice.oj.codingame.medium.cabling;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class NetworkCablingTest {
    private long calculateOptimalLength(int[] x, int[] y) {
        if (x.length < 2) {
            return 0;
        }
        int minx = x[0];
        int maxx = x[0];
        int miny = y[0];
        int maxy = y[0];
        int minsum = 0;
        int above = 0;
        int below = 0;
        int lined = 0;
        for (int i = 1; i < x.length; ++i) {
            minx = Math.min(minx, x[i]);
            maxx = Math.max(maxx, x[i]);
            miny = Math.min(miny, y[i]);
            maxy = Math.max(maxy, y[i]);
            if (y[i] > 0) {
                ++above;
            } else if (y[i] < 0) {
                ++below;
            } else {
                ++lined;
            }
            minsum += Math.abs(y[i]);
        }
        // count pivot distance
        if (minsum - above < minsum + below) {
        }
//        while (above ) {
//
//        }
        for (int j = 0; j < x.length; ++j) {
        }
        // knowing the number of houses on either side of the line gives the solution in a quite straight way
        // int z1 = number of houses above
        // int z2 = number of houses below
        // shift up changes the distance: -z1 + z2
        // shift down: z1 - z2

        int mainpart = maxx - minx;
        int len = mainpart + minsum;
        return len;
    }

    private int calculateOptimalLengthNaive(int[] x, int[] y) {
        if (x.length < 2) {
            return 0;
        }
        int minx = x[0];
        int maxx = x[0];
        int miny = y[0];
        int maxy = y[0];
        for (int i = 1; i < x.length; ++i) {
            minx = Math.min(minx, x[i]);
            maxx = Math.max(maxx, x[i]);
            miny = Math.min(miny, y[i]);
            maxy = Math.max(maxy, y[i]);
        }
        int minsum = 0;
        for (int j = 0; j < x.length; ++j) {
            minsum += Math.abs(miny - y[j]);
        }
        for (int mainy = miny + 1; mainy <= maxy; ++mainy) {
            int sum = 0;
            for (int j = 0; j < x.length; ++j) {
                sum += Math.abs(mainy - y[j]);
            }
            minsum = Math.min(minsum, sum);
        }
        // count pivot distance
        // knowing the number of houses on either side of the line gives the solution in a quite straight way
        // int z1 = number of houses above
        // int z2 = number of houses below
        // shift up changes the distance: -z1 + z2
        // shift down: z1 - z2

        int mainpart = maxx - minx;
        int len = mainpart + minsum;
        return len;
    }

    private int calculateOptimalLength2(int[] x, int[] y) {
        if (x.length < 2) {
            return 0;
        }
        int xmin = x[0];
        int xmax = x[0];
        int ysum = 0;
        for (int i = 0; i < x.length; ++i) {
            xmin = Math.min(xmin, x[i]);
            xmax = Math.max(xmax, x[i]);
            ysum += y[i];
        }
        int yavg = ysum / x.length;
        int minsum1 = 0;
        int minsum2 = 0;
        int minsum3 = 0;
        for (int i = 0; i < x.length; ++i) {
            minsum1 += Math.abs(y[i] - yavg);
            minsum2 += Math.abs(y[i] - yavg - 1);
            minsum3 += Math.abs(y[i] - yavg + 1);
        }

        int mainpart = xmax - xmin;
        int len = mainpart + Math.min(minsum1, Math.min(minsum2, minsum3));
        System.out.println(yavg + " " + len);
        return len;
    }

    @Test
    public void testNoHouses() {
        Assert.assertEquals(0, calculateOptimalLength(new int[]{}, new int[]{}));
    }

    @Test
    public void testOneHouse() {
        Assert.assertEquals(0, calculateOptimalLength(new int[]{0}, new int[]{0}));
    }

    @Test
    public void testAllLinedUp() {
        Assert.assertEquals(1, calculateOptimalLength(new int[]{0, 1}, new int[]{0, 0}));
        Assert.assertEquals(2, calculateOptimalLength(new int[]{0, 1, 2}, new int[]{0, 0, 0}));
        Assert.assertEquals(3, calculateOptimalLength(new int[]{0, 1, 2, 3}, new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testTwoLinedUpPlusOne() {
        Assert.assertEquals(4, calculateOptimalLength(new int[]{0, 1, 2}, new int[]{0, 2, 2}));
    }

    @Test
    public void testThreeAlongDiagonal() {
        Assert.assertEquals(4, calculateOptimalLength(new int[]{0, 1, 2}, new int[]{0, 1, 2}));
    }
    @Test
    public void testOfficialExample5() {
        Assert.assertEquals(18, calculateOptimalLength(new int[]{-5, -9, 3}, new int[]{-3, 2, -4}));
    }
//    @Test
    public void testOfficialExample6() {
        Assert.assertEquals(6066790161L, calculateOptimalLength(
                new int[]{-28189131, 102460950,938059973,-334087877,842560881,-416604701,19715507,846505116},
                new int[]{593661218,1038903636,-816049599,-290840615,-116496866,690825290,470868309,-694479954}));
    }
}
