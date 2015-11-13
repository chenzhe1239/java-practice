package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FractionRecurringDecimalTest {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder builder = new StringBuilder();
        if (numerator != 0 && numerator < 0 ^ denominator < 0) {
            builder.append("-");
        }

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        builder.append(num / den);
        long remainder = num % den;

        if (remainder == 0) {
            return builder.toString();
        }
        builder.append(".");

        Map<Long, Integer> map = new HashMap<>();
        int index = builder.length();
        while (remainder != 0) {
            map.put(remainder, index++);

            remainder *= 10;
            builder.append(remainder / den);
            remainder %= den;

            Integer remainderIndex = map.get(remainder);
            if (remainderIndex != null) {
                builder.insert(remainderIndex, "(");
                builder.append(")");
                break;
            }
        }

        return builder.toString();
    }

    public String fractionToDecimal_lazy(int numerator, int denominator) {
        if (numerator % denominator == 0) {
            return "" + numerator / denominator;
        }
        BigDecimal value = BigDecimal.valueOf(numerator).divide(BigDecimal.valueOf(denominator), 35, BigDecimal.ROUND_FLOOR);
        return String.valueOf(value.toPlainString())
                .replaceAll("0+$", "")
                .replaceAll("(\\d+)(\\1)+.*", "($1)")
                .replaceAll("\\((\\d+?)(\\1)+\\)", "($1)");
    }

    @Test
    public void test_1_2() {
        assertEquals("0.5", fractionToDecimal(1, 2));
    }

    @Test
    public void test_2_1() {
        assertEquals("2", fractionToDecimal(2, 1));
    }

    @Test
    public void test_2_3() {
        assertEquals("0.(6)", fractionToDecimal(2, 3));
    }

    @Test
    public void test_1_90() {
        assertEquals("0.0(1)", fractionToDecimal(1, 90));
    }

    @Test
    public void test_1_99() {
        assertEquals("0.(01)", fractionToDecimal(1, 99));
    }

    @Test
    public void test_1_333() {
        assertEquals("0.(003)", fractionToDecimal(1, 333));
    }

    @Test
    public void test_1_17() {
        assertEquals("0.(0588235294117647)", fractionToDecimal(1, 17));
    }

    @Test
    public void test_1_214748364() {
        String expected = "0.00(000000465661289042462740251655654056577585848337359161441621040707904997124914069194026549138227660723878669455195477065427143370461252966751355553982241280310754777158628319049732085502639731402098131932683780538602845887105337854867197032523144157689601770377165713821223802198558308923834223016478952081795603341592860749337303449725)";
        assertEquals(expected, fractionToDecimal(1, 214748364));
    }

    @Test
    public void test_7_minus12() {
        assertEquals("-0.58(3)", fractionToDecimal(7, -12));
    }

    @Test
    public void test_0_minus12() {
        assertEquals("0", fractionToDecimal(0, -12));
    }

    @Test
    public void test_minus50_8() {
        assertEquals("-6.25", fractionToDecimal(-50, 8));
    }

    @Test
    public void test_m1_m2147483648() {
        assertEquals("0.0000000004656612873077392578125", fractionToDecimal(-1, -2147483648));
    }
}
