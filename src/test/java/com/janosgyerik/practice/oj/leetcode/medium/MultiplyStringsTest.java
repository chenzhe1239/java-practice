package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MultiplyStringsTest {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        if (num1.equals("1")) {
            return num2;
        }
        if (num2.equals("1")) {
            return num1;
        }
        int[] digits1 = toDigits(num1);
        int[] digits2 = toDigits(num2);

        List<int[]> addOperands = new ArrayList<>(digits2.length);
        int shift = 1;
        int expectedDigitsCount = digits1.length + digits2.length;
        for (int digit : digits2) {
            addOperands.add(multiply(digits1, digit, shift++, expectedDigitsCount));
        }

        int[] sum = addOperands.get(0);
        for (int i = 1; i < addOperands.size(); ++i) {
            sum = add(sum, addOperands.get(i));
        }

        return digitsToString(sum);
    }

    String digitsToString(int[] digits) {
        StringBuilder builder = new StringBuilder(digits.length);
        int i = 0;
        while (i < digits.length && digits[i] == 0) {
            ++i;
        }
        for (; i < digits.length; ++i) {
            builder.append((char) (digits[i] + '0'));
        }
        return builder.toString();
    }

    int[] toDigits(String num) {
        int[] digits = new int[num.length()];
        for (int i = 0; i < num.length(); ++i) {
            digits[i] = num.charAt(i) - '0';
        }
        return digits;
    }

    int[] multiply(int[] digits, int digit, int shift, int expectedDigitsCount) {
        int[] result = new int[expectedDigitsCount];
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; --i) {
            int product = carry + digits[i] * digit;
            result[i + shift] = product % 10;
            carry = product / 10;
        }
        result[shift - 1] = carry;
        return result;
    }

    int[] add(int[] num1, int[] num2) {
        int result[] = new int[num1.length];
        int carry = 0;
        for (int i = num1.length - 1; i >= 0; --i) {
            int sum = carry + num1[i] + num2[i];
            result[i] = sum % 10;
            carry = sum / 10;
        }
        return result;
    }

    @Test
    public void test_1_123() {
        assertEquals("123", multiply("1", "123"));
    }

    @Test
    public void test_2_123() {
        assertEquals("246", multiply("2", "123"));
    }

    @Test
    public void test_12_123() {
        assertEquals("1476", multiply("12", "123"));
    }

    @Test
    public void test_9_99() {
        assertEquals("891", multiply("9", "99"));
    }

    @Test
    public void test_2097152_196608() {
        assertEquals("412316860416", multiply("2097152", "196608"));
    }

    @Test
    public void test_toDigits() {
        assertEquals("[1, 2, 3]", Arrays.toString(toDigits("123")));
    }

    @Test
    public void test_multiply_by_digit_shift1() {
        assertEquals("[0, 2, 4, 6]", Arrays.toString(multiply(new int[]{1, 2, 3}, 2, 1, 4)));
    }

    @Test
    public void test_multiply_by_digit_9_9_shift1() {
        assertEquals("[8, 1, 0]", Arrays.toString(multiply(new int[]{9}, 9, 1, 3)));
    }

    @Test
    public void test_multiply_by_digit_9_9_shift2() {
        assertEquals("[0, 8, 1]", Arrays.toString(multiply(new int[]{9}, 9, 2, 3)));
    }

    @Test
    public void test_multiply_by_digit_shift2() {
        assertEquals("[0, 0, 2, 4, 6]", Arrays.toString(multiply(new int[]{1, 2, 3}, 2, 2, 5)));
    }

    @Test
    public void test_add_810_081() {
        assertEquals("[8, 9, 1]", Arrays.toString(add(new int[]{8, 1, 0}, new int[]{0, 8, 1})));
    }
}
