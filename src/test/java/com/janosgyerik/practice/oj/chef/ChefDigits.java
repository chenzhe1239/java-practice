package com.janosgyerik.practice.oj.chef;

import org.junit.Assert;
import org.junit.Test;

public class ChefDigits {
    private int doCalculation(int[] digits, int index) {
        int sum = 0;
        for (int i = 0; i < index - 1; ++i) {
            sum += Math.abs(digits[index - 1] - digits[i]);
        }
        return sum;
    }

    @Test
    public void testExamples() {
        int[] digits = new int[] {0, 3, 2, 4, 1, 5, 2, 3, 9, 7};
        Assert.assertEquals(0, doCalculation(digits, 1));
        Assert.assertEquals(7, doCalculation(digits, 4));
        Assert.assertEquals(9, doCalculation(digits, 7));
    }
}
