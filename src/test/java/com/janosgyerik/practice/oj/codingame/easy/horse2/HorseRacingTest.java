package com.janosgyerik.practice.oj.codingame.easy.horse2;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class HorseRacingTest {
    @Test
    public void testDifferenceZero() {
        assertEquals(0, solution(1, 1, 1));
        assertEquals(0, solution(1, 1, 11));
    }

    @Test
    public void testDifferenceOne() {
        assertEquals(1, solution(1, 2, 3));
    }

    @Test
    public void testDifferenceTwo() {
        assertEquals(2, solution(10, 20, 15, 12));
    }

    @Test
    public void testDifferenceAtEnd() {
        assertEquals(2, solution(10, 20, 15, 22));
    }

    @Test
    public void testDifferenceInSorted() {
        assertEquals(1, solution(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void testDifferenceInShuffled() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6);
        Collections.shuffle(ints);
        assertEquals(1, solution(ints));
    }

    private int solution(List<Integer> ints) {
        int[] arr = new int[ints.size()];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = ints.get(i);
        }
        return solution(arr);
    }

    private int solution(int... ints) {
        return HorseRacing.findSmallestDifference(ints);
    }
}
