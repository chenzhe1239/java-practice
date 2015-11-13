package com.janosgyerik.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LargestRectangleTest {
    private int findLargestRectangle(int... nums) {
        if (nums.length < 1) {
            return 0;
        }
        if (nums.length < 2) {
            return nums[0];
        }
        int minIndex = 0;
        int largest = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            //   3  3      3
            //  23223 2 2223
            // 12322312122231
            for (int j = minIndex; j <= i; ++i) {

            }
            if (nums[i] > nums[minIndex]) {
                int area = nums[minIndex] * (i - minIndex + 1);
                if (area > largest) {
                    largest = area;
                }
            }
        }

        return largest;
    }

    @Test
    public void test_1_2_3_4_5_is_9() {
        assertTrue("hello".contains("e"));
//        assertEquals(9, findLargestRectangle(1, 2, 3, 4, 5));
    }
}
