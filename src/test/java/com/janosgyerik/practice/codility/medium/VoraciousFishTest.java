package com.janosgyerik.practice.codility.medium;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class VoraciousFishTest {
    enum Direction {
        UPSTREAM,
        DOWNSTREAM
    }

    class Fish {
        private final int size;
        private final Direction direction;

        public Fish(int size, int direction) {
            this.size = size;
            this.direction = direction == Direction.UPSTREAM.ordinal() ? Direction.UPSTREAM : Direction.DOWNSTREAM;
        }
    }

    public Stack<Fish> fishStayAlive(int[] sizes, int[] directions) {
        Stack<Fish> survivors = new Stack<Fish>();
        for (int i = 0; i < sizes.length; ++i) {
            Fish fish = new Fish(sizes[i], directions[i]);
            playOff(survivors, fish);
        }
        return survivors;
    }

    public Stack<Fish> fishStayAlive(List<Fish> list) {
        Stack<Fish> survivors = new Stack<Fish>();
        for (Fish fish : list) {
            playOff(survivors, fish);
        }
        return survivors;
    }

    private void playOff(Stack<Fish> survivors, Fish fish) {
        while (true) {
            if (survivors.isEmpty()) {
                survivors.push(fish);
                break;
            }
            Fish top = survivors.peek();
            if (top.direction == Direction.UPSTREAM) {
                survivors.push(fish);
                break;
            }
            if (top.direction == fish.direction) {
                survivors.push(fish);
                break;
            }
            if (top.size > fish.size) {
                break;
            }
            survivors.pop();
        }
    }

    public int numberOfFishStayAlive(int[] sizes, int[] directions) {
        return fishStayAlive(sizes, directions).size();
    }

    public int countAlive(boolean[] alive) {
        int count = 0;
        for (boolean isAlive : alive) {
            if (isAlive) ++count;
        }
        return count;
    }

    private void assertSizesAreUnique(int[] sizes) {
        Set<Integer> sizesSet = new HashSet<Integer>();
        for (int size : sizes) {
            assertFalse(sizesSet.contains(size));
            sizesSet.add(size);
        }
    }

    @Test
    public void testAllFishUpstream() {
        List<Fish> list = Arrays.asList(new Fish(3, 1), new Fish(4, 1));
        assertEquals(list.size(), fishStayAlive(list).size());
    }

    @Test
    public void testAllFishDownstream() {
        List<Fish> list = Arrays.asList(new Fish(3, 0), new Fish(4, 0));
        assertEquals(list.size(), fishStayAlive(list).size());
    }

    @Test
    public void testBigFishEatsSmall() {
        Fish big = new Fish(Integer.MAX_VALUE, 1);
        assertEquals(1, fishStayAlive(Arrays.asList(big, new Fish(4, 0))).size());
        assertEquals(1, fishStayAlive(Arrays.asList(big, new Fish(4, 0), new Fish(5, 0))).size());
        assertEquals(1, fishStayAlive(Arrays.asList(big, new Fish(4, 0), new Fish(5, 0), new Fish(Integer.MAX_VALUE - 1, 0))).size());
    }

    @Test
    public void testTwoBigFishSurvive() {
        assertEquals(2, fishStayAlive(Arrays.asList(new Fish(14, 1), new Fish(15, 1), new Fish(3, 0))).size());
        assertEquals(2, fishStayAlive(Arrays.asList(new Fish(14, 1), new Fish(15, 1), new Fish(10, 0), new Fish(3, 0))).size());
        assertEquals(2, fishStayAlive(Arrays.asList(new Fish(14, 1), new Fish(15, 1), new Fish(10, 0), new Fish(11, 0), new Fish(3, 0))).size());
        assertEquals(2, fishStayAlive(Arrays.asList(new Fish(4, 0), new Fish(3, 1), new Fish(2, 0), new Fish(1, 0), new Fish(5, 0))).size());
    }
}
