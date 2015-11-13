package com.janosgyerik.practice.oj.codingame.medium.worstloss;

import org.junit.Assert;
import org.junit.Test;

public class WorstLossTest {
    private int calculateWorstLossNaive(int[] curve) {
        int biggest = 0;
        for (int i = 0; i < curve.length - 1; ++i) {
            for (int j = i + 1; j < curve.length; ++j) {
                if (curve[j] < curve[i]) {
                    if (curve[j] - curve[i] < biggest) {
                        biggest = Math.min(biggest, curve[j] - curve[i]);
                    }
                }
            }
        }
        return biggest;
    }

    private int calculateWorstLoss(int[] curve) {
        return Solution.solution(curve);
    }

    @Test
    public void testExampleMixedMiddle() {
        Assert.assertEquals(-3, calculateWorstLoss(new int[]{3, 2, 4, 2, 1, 5}));
    }

    @Test
    public void testExampleDecreasing() {
        Assert.assertEquals(-4, calculateWorstLoss(new int[]{5, 3, 4, 2, 3, 1}));
    }

    @Test
    public void testExampleMonotonIncreasing() {
        Assert.assertEquals(0, calculateWorstLoss(new int[]{1, 2, 4, 4, 5}));
    }

    @Test
    public void testShortSequences() {
        Assert.assertEquals(0, calculateWorstLoss(new int[]{1}));
        Assert.assertEquals(0, calculateWorstLoss(new int[]{1, 1}));
        Assert.assertEquals(0, calculateWorstLoss(new int[]{1, 2}));
        Assert.assertEquals(0, calculateWorstLoss(new int[]{1, 2, 3}));
        Assert.assertEquals(-1, calculateWorstLoss(new int[]{3, 2}));
        Assert.assertEquals(0, calculateWorstLoss(new int[]{2, 3}));
        Assert.assertEquals(-1, calculateWorstLoss(new int[]{2, 3, 2}));
        Assert.assertEquals(-1, calculateWorstLoss(new int[]{2, 3, 2, 4}));
        Assert.assertEquals(-1, calculateWorstLoss(new int[]{2, 3, 2, 4, 5}));
    }

    @Test
    public void testMoreExamples() {
        // case: A < C, B > D
        Assert.assertEquals(-3, calculateWorstLoss(new int[]{3, 2, 4, 2, 1, 5}));
        // case: A > C, B > D
        Assert.assertEquals(-4, calculateWorstLoss(new int[]{5, 2, 4, 2, 1, 5}));
        // case: A < C, B < D
        Assert.assertEquals(-3, calculateWorstLoss(new int[]{3, 1, 5, 3, 2, 5}));
        // case: A > C, B < D
        Assert.assertEquals(-4, calculateWorstLoss(new int[]{5, 1, 4, 2, 2, 5}));
    }

    @Test
    public void testLargeSet() {
        int[] values = new int[]{360206605 ,753529295 ,289276846 ,389601008 ,956209493 ,759816072 ,21292839 ,95253218 ,761349009 ,529261257 ,227451482 ,107216718 ,257358645 ,655524566 ,143516595 ,864350585 ,811621355 ,676849354 ,1011422165 ,187552695 ,979999435 ,631537455 ,270847578 ,593263592 ,57503202 ,1007228823 ,161483104 ,26251389 ,755812096 ,456180052 ,45877475 ,695186707 ,731072228 ,1037375540 ,690948844 ,193283995 ,559521199 ,388395606 ,23226767 ,423372563 ,691363237 ,526490632 ,443269046 ,805276883 ,335091016 ,472240313 ,343698878 ,781685086 ,104846778 ,636989946 ,914074342 ,702973394 ,103586418 ,180948368 ,863314059 ,515234527 ,582506305 ,761877395 ,470342708 ,1003740751 ,49332647 ,1015000480 ,384382320 ,435103774 ,326515322 ,724416539 ,377492611 ,1039212583 ,552111320 ,617148031 ,249485027 ,547788120 ,689473389 ,273214398 ,821223044 ,1037823873 ,303023369 ,472494003 ,107784439 ,155325607 ,832588165 ,916662949 ,27794260 ,755608304 ,58910709 ,390750165 ,939687932 ,850653211 ,1030076209 ,109346846 ,573932319 ,11726731 ,452667817 ,1035562412 ,425085728 ,514224094 ,544124296 ,855189942 ,952932796 ,249528598
        };
        Assert.assertEquals(-1027485852, calculateWorstLoss(values));
        Assert.assertEquals(-1027485852, calculateWorstLossNaive(values));
    }

    @Test
    public void testAnother() {
        Assert.assertEquals(-4, calculateWorstLoss(new int[]{4, 3, 5, 2, 1, 6}));
    }
}
