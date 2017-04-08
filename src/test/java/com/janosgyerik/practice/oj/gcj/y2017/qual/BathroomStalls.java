package com.janosgyerik.practice.oj.gcj.y2017.qual;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class BathroomStalls {
    static class Answer {
        final int max;
        final int min;

        Answer(int max, int min) {
            this.max = max;
            this.min = min;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Answer answer = (Answer) o;

            if (max != answer.max) return false;
            return min == answer.min;
        }

        @Override
        public int hashCode() {
            int result = max;
            result = 31 * result + min;
            return result;
        }

        @Override
        public String toString() {
            return max + " " + min;
        }
    }

    static class SkippingSolver implements Solver {
        public SkippingSolver(Inputs inputs) {
            // not used in this problem
        }

        @Override
        public Answer solve(Input input) {
            int N = input.n;
            int K = input.k;

            PriorityQueue<Integer> gaps = new PriorityQueue<>(Comparator.reverseOrder());
            Map<Integer, Integer> counts = new HashMap<>();

            gaps.add(N);
            counts.put(N, 1);
            int max = N;
            int min = N;
            for (int i = 0; i < K; ) {
                int gap = gaps.poll();

                int half = gap / 2;
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
            return new BathroomStalls.Answer(max, min);
        }
    }

    static class Inputs {
        private final List<Input> inputs;

        public Inputs(List<Input> inputs) {
            this.inputs = inputs;
        }

        public static Inputs parse(String path) throws IOException {
            return parse(new Scanner(Paths.get(path)));
        }

        public static Inputs parse(Scanner scanner) {
            int t = scanner.nextInt();
            List<Input> inputs = new ArrayList<>(t);
            for (int i = 0; i < t; i++) {
                int n = scanner.nextInt();
                int k = scanner.nextInt();
                inputs.add(new Input(n, k));
            }
            return new Inputs(inputs);
        }

        public int count() {
            return inputs.size();
        }

        public Input get(int casenum) {
            return inputs.get(casenum - 1);
        }
    }

    static class Input {
        final int n;
        final int k;

        Input(int n, int k) {
            this.n = n;
            this.k = k;
        }
    }

    interface Solver {
        Answer solve(Input input);
    }

    static class MultiSolver {
        private final Solver solver;

        MultiSolver(Solver solver) {
            this.solver = solver;
        }

        String solve(Inputs inputs) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= inputs.count(); i++) {
                Answer answer = solver.solve(inputs.get(i));
                sb.append("Case #").append(i).append(": ").append(answer).append("\n");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        Inputs inputs = Inputs.parse("/tmp/input.txt");
        Solver solver = new SkippingSolver(inputs);

        try (FileWriter writer = new FileWriter("/tmp/output.txt")) {
            writer.write(new MultiSolver(solver).solve(inputs));
        }
    }
}
