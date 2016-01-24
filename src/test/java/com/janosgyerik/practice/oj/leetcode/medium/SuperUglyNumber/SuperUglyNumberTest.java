package com.janosgyerik.practice.oj.leetcode.medium.SuperUglyNumber;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SuperUglyNumberTest {

    private final Solution solution = new Solution();

    private static final int[] primes = {2, 3, 5};

    @Test
    public void test_4th() {
        assertEquals(4, solution.nthSuperUglyNumber(4, primes));
    }

    @Test
    public void test_7th() {
        assertEquals(8, solution.nthSuperUglyNumber(7, primes));
    }

    @Test
    public void test_10th() {
        assertEquals(12, solution.nthSuperUglyNumber(10, primes));
    }

    @Test
    public void test_100th() {
        assertEquals(1536, solution.nthSuperUglyNumber(100, primes));
    }

    @Test
    public void test_1407th() {
        assertEquals(536870912, solution.nthSuperUglyNumber(1407, primes));
    }

    @Test
    public void test_1600th() {
        assertEquals(1399680000, solution.nthSuperUglyNumber(1600, primes));
    }

    @Test
    public void test_large_input() {
        assertEquals(15132,
                solution.nthSuperUglyNumber(4000, 2, 3, 5, 13, 19, 29, 31, 41, 43, 53, 59, 73, 83, 89, 97, 103, 107,
                        109, 127, 137, 139, 149, 163, 173, 179, 193, 197, 199, 211, 223, 227, 229, 239, 241, 251, 257,
                        263, 269, 271, 281, 317, 331, 337, 347, 353, 359, 367, 373, 379, 389, 397, 409, 419, 421, 433,
                        449, 457, 461, 463, 479, 487, 509, 521, 523, 541, 547, 563, 569, 577, 593, 599, 601, 613, 619,
                        631, 641, 659, 673, 683, 701, 709, 719, 733, 739, 743, 757, 761, 769, 773, 809, 811, 829, 857,
                        859, 881, 919, 947, 953, 967, 971));
    }
}
