package com.janosgyerik.practice.codility.practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GroceryQueueTest {
    int minCustomersBefore(String data) {
        int count = 0;
        int len = data.length();
        int result = 0;
        for (int i = 0; i < len; ++i) {
            char c = data.charAt(i);
            switch (c) {
                case '1':
                    ++count;
                    result = Math.max(count, result);
                    break;
                case '0':
                    --count;
            }
        }
        return result;
    }

    @Test
    public void test_111() {
        assertEquals(3, minCustomersBefore("111"));
    }

    @Test
    public void test_0111() {
        assertEquals(2, minCustomersBefore("0111"));
    }

    @Test
    public void test_01011() {
        assertEquals(1, minCustomersBefore("01011"));
    }

    @Test
    public void test_0101100011111010111111() {
        assertEquals(8, minCustomersBefore("0101100011111010111111"));
    }

    @Test
    public void test_000() {
        assertEquals(0, minCustomersBefore("000"));
    }

    @Test
    public void test_111000() {
        assertEquals(3, minCustomersBefore("111000"));
    }
}
