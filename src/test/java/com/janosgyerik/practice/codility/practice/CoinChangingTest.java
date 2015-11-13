package com.janosgyerik.practice.codility.practice;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class CoinChangingTest {
    Map<Integer, Integer> getMinCoins(SortedSet<Integer> coins, int targetAmount) {
        return getMinCoins(coins, targetAmount, new CoinCounter()).toMap();
    }

    static final Comparator<CoinCounter> coinCounterComparator = new Comparator<CoinCounter>() {
        @Override
        public int compare(CoinCounter o1, CoinCounter o2) {
            return Integer.compare(o1.getCoinCount(), o2.getCoinCount());
        }
    };

    CoinCounter getMinCoins(SortedSet<Integer> coins, int targetAmount, CoinCounter counter) {
        if (targetAmount == 0) {
            return counter;
        }

        List<CoinCounter> counters = new ArrayList<>(coins.size());
        for (int coin : coins) {
            if (coin <= targetAmount) {
                CoinCounter newCounter = getMinCoins(coins, targetAmount - coin, counter.add(coin));
                if (!newCounter.isEmpty()) {
                    counters.add(newCounter);
                }
            }
        }
        if (counters.isEmpty()) {
            return new CoinCounter();
        }
        Collections.sort(counters, coinCounterComparator);
        return counters.get(0);
    }

    static class CoinCounter {
        Map<Integer, Integer> map;

        public CoinCounter() {
            this.map = new HashMap<>();
        }

        public CoinCounter(CoinCounter other) {
            this.map = new HashMap<>(other.map);
        }

        public Map<Integer, Integer> toMap() {
            return map;
        }

        public int getCoinCount() {
            int totalCount = 0;
            for (int count : map.values()) {
                totalCount += count;
            }
            return totalCount;
        }

        public CoinCounter add(int coin) {
            CoinCounter copy = new CoinCounter(this);
            copy.addCoin(coin);
            return copy;
        }

        private void addCoin(int coin) {
            Integer currentCount = map.get(coin);
            if (currentCount == null) {
                currentCount = 0;
            }
            map.put(coin, ++currentCount);
        }

        public boolean isEmpty() {
            return map.isEmpty();
        }
    }

    @Test
    public void test_1_3_4_x_6() {
        Map<Integer, Integer> expected = new CoinCounter().add(3).add(3).toMap();
        assertEquals(expected, getMinCoins(getCoins(1, 3, 4), 6));
    }

    @Test
    public void test_1_3_4_x_7() {
        Map<Integer, Integer> expected = new CoinCounter().add(4).add(3).toMap();
        assertEquals(expected, getMinCoins(getCoins(1, 3, 4), 7));
    }

    @Test
    public void test_1_3_4_x_8() {
        Map<Integer, Integer> expected = new CoinCounter().add(4).add(4).toMap();
        assertEquals(expected, getMinCoins(getCoins(1, 3, 4), 8));
    }

    @Test
    public void test_2_3_4_x_8() {
        Map<Integer, Integer> expected = new CoinCounter().add(4).add(4).toMap();
        assertEquals(expected, getMinCoins(getCoins(2, 3, 4), 8));
    }

    @Test
    public void test_2_3_x_8() {
        Map<Integer, Integer> expected = new CoinCounter().add(2).add(3).add(3).toMap();
        assertEquals(expected, getMinCoins(getCoins(2, 3), 8));
    }

    private SortedSet<Integer> getCoins(int... denominations) {
        SortedSet<Integer> coins = new TreeSet<>();
        for (int coin : denominations) {
            coins.add(coin);
        }
        return coins;
    }
}
