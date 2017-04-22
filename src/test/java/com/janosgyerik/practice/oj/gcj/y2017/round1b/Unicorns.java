package com.janosgyerik.practice.oj.gcj.y2017.round1b;

import com.janosgyerik.practice.oj.gcj.y2017.common.*;

import java.util.*;
import java.util.stream.Collectors;

public class Unicorns implements Problem {
    @Override
    public Inputs inputs(Scanner scanner) {
        Inputs inputs = new Inputs();

        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int stalls = scanner.nextInt();
            int red = scanner.nextInt();
            int orange = scanner.nextInt();
            int yellow = scanner.nextInt();
            int green = scanner.nextInt();
            int blue = scanner.nextInt();
            int violet = scanner.nextInt();
            inputs.add(new UnicornsInput(stalls, red, orange, yellow, green, blue, violet));
        }
        return inputs;
    }

    static class UnicornsInput implements Input {
        private final int stalls;
        private final int red;
        private final int orange;
        private final int yellow;
        private final int green;
        private final int blue;
        private final int violet;

        public UnicornsInput(int stalls, int red, int orange, int yellow, int green, int blue, int violet) {
            this.stalls = stalls;
            this.red = red;
            this.orange = orange;
            this.yellow = yellow;
            this.green = green;
            this.blue = blue;
            this.violet = violet;
        }
    }

    @Override
    public Solver solver(Inputs inputs) {
        return new UnicornsSolver();
    }

    static class UnicornsSolver implements Solver {
        @Override
        public Answer solve(Input input0) {
            UnicornsInput input = (UnicornsInput) input0;

            Counts counts = new Counts();
            counts.put(Hair.RED, input.red);
            counts.put(Hair.BLUE, input.blue);
            counts.put(Hair.YELLOW, input.yellow);
            counts.put(Hair.GREEN, input.green);
            counts.put(Hair.ORANGE, input.orange);
            counts.put(Hair.VIOLET, input.violet);

            Tracker tracker = new Tracker(counts, input.stalls);
            if (tracker.canPlace()) {
                return tracker.sb::toString;
            }
            return () -> "IMPOSSIBLE";
        }
    }

    static class Tracker {
        private final Counts counts;
        private final int total;
        private final StringBuilder sb = new StringBuilder();
        private Hair first = null;
        private Hair last = null;
        private Map<Hair, Collection<Hair>> validNeighbors = new HashMap<>();

        Tracker(Counts counts, int total) {
            this.counts = counts;
            this.total = total;

            validNeighbors.put(Hair.RED, Arrays.asList(Hair.BLUE, Hair.GREEN, Hair.YELLOW));
            validNeighbors.put(Hair.BLUE, Arrays.asList(Hair.RED, Hair.ORANGE, Hair.YELLOW));
            validNeighbors.put(Hair.YELLOW, Arrays.asList(Hair.RED, Hair.BLUE, Hair.VIOLET));
            validNeighbors.put(Hair.GREEN, Collections.singleton(Hair.RED));
            validNeighbors.put(Hair.ORANGE, Collections.singleton(Hair.BLUE));
            validNeighbors.put(Hair.VIOLET, Collections.singleton(Hair.YELLOW));
        }

        boolean canPlace() {
            if (counts.isEmpty()) {
                return true;
            }

            Collection<Hair> possible = getPossibleHairs();
            if (possible.isEmpty()) {
                return false;
            }

            for (Hair hair : possible) {
                counts.dec(hair);
                last = hair;
                if (sb.length() == 0) {
                    first = hair;
                }
                sb.append(hair.label());
                if (canPlace()) {
                    return true;
                }
                sb.setLength(sb.length() - 1);
                counts.inc(hair);
            }
            return false;
        }

        public Collection<Hair> getPossibleHairs() {
            if (first == null) {
                return new ArrayList<>(counts.keys());
            }
            Collection<Hair> possible = validNeighbors.get(last).stream().filter(counts::contains).collect(Collectors.toList());

            if (sb.length() + 1 == total) {
                possible.retainAll(validNeighbors.get(first));
            }
            return possible;
        }
    }

    enum Hair {
        RED,
        BLUE,
        YELLOW,
        ORANGE,
        VIOLET,
        GREEN;

        public char label() {
            return name().charAt(0);
        }
    }

    static class Counts {
        Map<Hair, Integer> map = new HashMap<>();

        void put(Hair hair, int count) {
            if (count > 0) map.put(hair, count);
        }

        public boolean isEmpty() {
            return map.isEmpty();
        }

        public Collection<Hair> keys() {
            return map.keySet();
        }

        public void dec(Hair hair) {
            map.put(hair, map.get(hair) - 1);
            if (map.get(hair) == 0) {
                map.remove(hair);
            }
        }

        public void inc(Hair hair) {
            if (!map.containsKey(hair)) {
                map.put(hair, 1);
            } else {
                map.put(hair, map.get(hair) + 1);
            }
        }

        public boolean contains(Hair hair) {
            return map.containsKey(hair);
        }
    }
}
