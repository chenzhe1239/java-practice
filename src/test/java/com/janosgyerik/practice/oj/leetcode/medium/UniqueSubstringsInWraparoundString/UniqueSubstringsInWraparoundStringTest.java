package com.janosgyerik.practice.oj.leetcode.medium.UniqueSubstringsInWraparoundString;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class UniqueSubstringsInWraparoundStringTest {
    private final Solution solution = new Solution();

    @Test
    public void test_() {
        List<String> sequences = solution.findSequences("zabahellothereidiotiwentoashoptobuysomeflowersandthen");
        System.out.println(sequences);
        Collections.sort(sequences);
        System.out.println(sequences);
        Collections.sort(sequences, (s1, s2) -> -Integer.compare(s1.length(), s2.length()));
        System.out.println(sequences);
    }

    @Test
    public void test_arrstream() {
        Integer[] arr = {1, 2, 3};
        System.out.println(Arrays.toString(Arrays.stream(arr).mapToInt(x -> x * 2).toArray()));
    }

    class IntervalIndex {

    }
}
