package com.janosgyerik.practice.oj.gcj.y2017.qual;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class OversizedPancakeFlipper {
    static class Solver1 implements Solver {
        public Solver1(Inputs inputs) {
            // not used in this problem
        }

        static class PossibleStartIterator implements Iterator<Integer> {
            private final BitSet bits;
            int pos;
            private final int length;

            public PossibleStartIterator(BitSet bits, int length) {
                this.bits = bits;
                pos = bits.nextClearBit(0);
                this.length = length;
            }

            @Override
            public boolean hasNext() {
                return pos < length;
            }

            @Override
            public Integer next() {
                int ret = pos;
                pos = bits.nextClearBit(pos + 1);
                return ret;
            }
        }

        @Override
        public Answer solve(Input input) {
            Info first = Info.create(input);
            if (first.done()) return new Answer(0);

            Set<BitSet> seen = new HashSet<>();

            Queue<Info> queue = new LinkedList<>();
            queue.add(first);

            while (!queue.isEmpty()) {
                Info info = queue.poll();
                if (!seen.add(info.bits)) continue;

                PossibleStartIterator it = new PossibleStartIterator(info.bits, info.length);
                while (it.hasNext()) {
                    int start = it.next();
                    Info copy = info.copy();
                    if (start + input.size < info.length) {
                        copy.bits.flip(start, start + input.size);
                    } else {
                        copy.bits.flip(info.length - input.size, info.length);
                    }

                    if (copy.done()) {
                        return new Answer(copy.count);
                    }
                    queue.add(copy);
                }
            }
            return new Answer(-1);
        }
    }

    static String convert(BitSet bits, int nbits) {
        return IntStream
                .range(0, nbits)
                .mapToObj(i -> bits.get(i) ? '1' : '0')
                .collect(
                        () -> new StringBuilder(nbits),
                        StringBuilder::append,
                        StringBuilder::append
                )
                .toString();
    }

    static class Info {
        private final BitSet bits;
        private final int length;
        public int count = 0;

        public Info(BitSet bits, int length) {
            this.bits = bits;
            this.length = length;
        }

        public static Info create(Input input) {
            BitSet bits = bits(input.pancakes);
            return new Info(bits, input.pancakes.length());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Info info = (Info) o;

            if (length != info.length) return false;
            return bits != null ? bits.equals(info.bits) : info.bits == null;
        }

        @Override
        public int hashCode() {
            return bits.hashCode();
        }

        public boolean done() {
            return bits.cardinality() == length;
        }

        public Info copy() {
            BitSet bitsCopy = new BitSet(length);
            bitsCopy.or(bits);

            Info copy = new Info(bitsCopy, length);
            copy.count = count + 1;
            return copy;
        }
    }

    static int simplify(BitSet bits, int length, int size) {
        int zeros = 0;
        int replacements = 0;
        for (int i = 0; i < length; i++) {
            if (!bits.get(i)) {
                zeros++;
                if (zeros == size) {
                    bits.set(i - size + 1, i + 1);
                    zeros = 0;
                    replacements++;
                }
            } else {
                zeros = 0;
            }
        }
        return replacements;
    }

    static BitSet bits(String s) {
        BitSet bits = new BitSet(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                bits.set(i);
            }
        }
        return bits;
    }

    static class Answer {
        final int flips;

        Answer(int flips) {
            this.flips = flips;
        }

        @Override
        public String toString() {
            return flips >= 0 ? String.valueOf(flips) : "IMPOSSIBLE";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Answer answer = (Answer) o;

            return flips == answer.flips;
        }

        @Override
        public int hashCode() {
            return flips;
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
            scanner.nextLine();
            List<Input> inputs = new ArrayList<>(t);
            for (int i = 0; i < t; i++) {
                inputs.add(new Input(scanner.next(), scanner.nextInt()));
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
        final String pancakes;
        final int size;

        Input(String pancakes, int size) {
            this.pancakes = pancakes;
            this.size = size;
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
        Solver solver = new Solver1(inputs);

        try (FileWriter writer = new FileWriter("/tmp/output.txt")) {
            writer.write(new MultiSolver(solver).solve(inputs));
        }
    }
}
