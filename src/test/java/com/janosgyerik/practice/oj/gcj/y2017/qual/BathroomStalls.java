package com.janosgyerik.practice.oj.gcj.y2017.qual;

import com.janosgyerik.practice.oj.gcj.y2017.common.*;

import java.util.*;

public class BathroomStalls implements Problem {
    @Override
    public Inputs inputs(Scanner scanner) {
        Inputs inputs = new Inputs();

        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            long n = scanner.nextLong();
            long k = scanner.nextLong();
            inputs.add(new BathroomStallsInput(n, k));
        }
        return inputs;
    }

    @Override
    public Solver solver(Inputs inputs) {
        return new SkippingSolver();
    }

    static class BathroomStallsAnswer implements Answer {
        final long max;
        final long min;

        BathroomStallsAnswer(long max, long min) {
            this.max = max;
            this.min = min;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BathroomStallsAnswer answer = (BathroomStallsAnswer) o;

            if (max != answer.max) return false;
            return min == answer.min;
        }

        @Override
        public int hashCode() {
            int result = (int) (max ^ (max >>> 32));
            result = 31 * result + (int) (min ^ (min >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return max + " " + min;
        }

        @Override
        public String value() {
            return this.toString();
        }
    }

    static class SkippingSolver implements Solver {
        @Override
        public Answer solve(Input input0) {
            BathroomStallsInput input = (BathroomStallsInput) input0;

            long N = input.n;
            long K = input.k;

            PriorityQueue<Long> gaps = new PriorityQueue<>(Comparator.reverseOrder());
            Map<Long, Integer> counts = new HashMap<>();

            gaps.add(N);
            counts.put(N, 1);
            long max = N;
            long min = N;
            for (int i = 0; i < K; ) {
                long gap = gaps.poll();

                long half = gap / 2;
                max = half;
                if (max == 0) {
                    min = 0;
                    break;
                }
                min = gap - half - 1;

                int count = counts.remove(gap);
                i += count;

                Integer gmax = counts.get(max);
                if (gmax != null) {
                    counts.put(max, gmax + count);
                } else {
                    counts.put(max, count);
                    gaps.add(max);
                }

                Integer gmin = counts.get(min);
                if (gmin != null) {
                    counts.put(min, gmin + count);
                } else {
                    counts.put(min, count);
                    gaps.add(min);
                }
            }
            return new BathroomStallsAnswer(max, min);
        }
    }

    static class BathroomStallsInput implements Input {
        final long n;
        final long k;

        BathroomStallsInput(long n, long k) {
            this.n = n;
            this.k = k;
        }
    }
}
