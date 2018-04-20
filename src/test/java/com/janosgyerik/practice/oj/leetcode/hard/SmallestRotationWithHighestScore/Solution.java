package com.janosgyerik.practice.oj.leetcode.hard.SmallestRotationWithHighestScore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
    public int bestRotation(int[] A) {
        Comparator<? super RotationEvent> comparator = (r1, r2) -> {
            int cmp = Integer.compare(r1.rotation, r2.rotation);
            if (cmp != 0) return cmp;
            return Boolean.compare(r1.start, r2.start);
        };
        PriorityQueue<RotationEvent> events = new PriorityQueue<>(comparator);
        IntStream.range(0, A.length)
            .forEach(pos -> addEvents(events, A.length, A[pos], pos));

        int max = Integer.MIN_VALUE;
        int count = 0;
        int rotation = Integer.MAX_VALUE;
        while (!events.isEmpty()) {
            RotationEvent event = events.poll();
            if (event.start) {
                count++;
                if (count > max) {
                    max = count;
                    rotation = event.rotation;
                } else if (count == max) {
                    rotation = Math.min(rotation, event.rotation);
                }
            } else if (event.rotation > 0) {
                count--;
            }
        }
        return rotation;
    }

    private void addEvents(PriorityQueue<RotationEvent> events, int length, int value, int pos) {
//        System.out.println(value);
        // possible rotations until no longer producing value
        if (pos - value >= 0) {
//            System.out.printf("%s -> %s\n", 0, pos - value + 1);
            events.add(new RotationEvent(true, 0));
            events.add(new RotationEvent(false, pos - value + 1));
        }
        // possible rotations from the end
        if (Math.min(length - pos - 1, length - value) > 0) {
//            System.out.printf("%s -> %s\n", pos + 1, pos + 1 + Math.min(length - pos - 1, length - value));
            events.add(new RotationEvent(true, pos + 1));
            events.add(new RotationEvent(false, pos + 1 + Math.min(length - pos - 1, length - value)));
        }
    }

    static class RotationEvent {
        public final boolean start;
        public final int rotation;

        RotationEvent(boolean start, int rotation) {
            this.start = start;
            this.rotation = rotation;
        }

        @Override
        public String toString() {
            return "RotationEvent{" +
                "start=" + start +
                ", rotation=" + rotation +
                '}';
        }
    }

    public int bestRotation3(int[] A) {
        int[] counts = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int rot : goodRotations(A, i)) {
                counts[rot]++;
            }
        }

        return indexOfMax(counts);
    }

    private List<Integer> goodRotations(int[] a, int pos) {
        List<Integer> list = new ArrayList<>();

        // possible rotations until no longer producing value
        for (int i = 0; i <= pos - a[pos]; i++) {
            list.add(i);
        }
        // possible rotations from the end
        for (int i = 0; i < Math.min(a.length - pos - 1, a.length - a[pos]); i++) {
            list.add(pos + 1 + i);
        }
        return list;
    }

    private int indexOfMax(int[] counts) {
        return IntStream.range(0, counts.length)
            .boxed()
            .max(Comparator.comparingInt(v -> counts[v]))
            .get();
    }

    public int bestRotation0(int[] A) {
        return IntStream.range(0, A.length)
            .mapToObj(i -> new RotatedArray(A, i))
            .max(Comparator.comparingInt(a -> a.score))
            .get()
            .rotation;
    }

    static class RotatedArray {
        private final int rotation;
        private final int score;

        RotatedArray(int[] arr, int rotation) {
            this.rotation = rotation;
            this.score = IntStream.range(0, arr.length)
                .map(i -> get(arr, i, rotation) <= i ? 1 : 0)
                .sum();
        }

        private static int get(int[] arr, int i, int rot) {
            int index = (i + rot) % arr.length;
            return arr[index];
        }
    }

    public int bestRotation2(int[] A) {
        List<Interval> intervals = IntStream.range(0, A.length)
            .boxed()
            .flatMap(i -> intervals(i, A[i]))
            .sorted((o1, o2) -> {
                int cmp = Integer.compare(o1.start, o2.start);
                if (cmp != 0) return cmp;
                return -Integer.compare(o1.end, o2.end);
            })
            .collect(Collectors.toList());

        return IntStream.range(0, A.length)
            .mapToObj(r -> computeScore(intervals, r))
            .max(Comparator.comparingInt(s -> s.score))
            .get()
            .rotation;
    }

    private Score computeScore(List<Interval> intervals, int rot) {
        return null;
    }

    private Stream<Interval> intervals(int pos, int value) {
        // sometimes 1, sometimes 2...
        return Stream.of();
    }
}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Score {
    int rotation;
    int score;
}
