package com.janosgyerik.practice.oj.leetcode.medium.SuperUglyNumber;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    private static class Entry {
        private final long value;
        private final int primeIndex;

        private Entry(long value, int primeIndex) {
            this.value = value;
            this.primeIndex = primeIndex;
        }
    }

    Comparator<Entry> entryComparator = (o1, o2) -> Long.compare(o1.value, o2.value);

    public int nthSuperUglyNumber(int n, int... primes) {
        assert primes.length > 0;

        PriorityQueue<Entry> heap = new PriorityQueue<>(entryComparator);
        heap.add(new Entry(primes[0], 0));

        long value = 1;
        for (int k = 1; k < n; ++k) {
            Entry entry = heap.poll();
            value = entry.value;
            heap.add(new Entry(value * primes[entry.primeIndex], entry.primeIndex));
            if (entry.primeIndex + 1 < primes.length) {
                long nextValue = value / primes[entry.primeIndex] * primes[entry.primeIndex + 1];
                heap.add(new Entry(nextValue, entry.primeIndex + 1));
            }
        }

        return Long.valueOf(value).intValue();
    }
}
