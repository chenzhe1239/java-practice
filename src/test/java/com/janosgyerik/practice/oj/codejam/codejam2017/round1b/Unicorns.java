package com.janosgyerik.practice.oj.codejam.codejam2017.round1b;

import com.janosgyerik.practice.oj.codejam.codejam2017.common.*;
import com.sun.org.apache.regexp.internal.RE;
import sun.jvm.hotspot.utilities.AssertionFailure;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

            Answer impossible = () -> "IMPOSSIBLE";

            int red = input.red;
            int blue = input.blue;
            int yellow = input.yellow;

            if (input.violet > 0) {
                if (input.yellow < input.violet + 1) {
                    return impossible;
                }
                yellow -= input.violet;
            }

            if (input.orange > 0) {
                if (input.blue < input.orange + 1) {
                    return impossible;
                }
                blue -= input.orange;
            }

            if (input.green > 0) {
                if (input.red < input.green + 1) {
                    return impossible;
                }
                red -= input.green;
            }

            if (red + blue < yellow || red + yellow < blue || blue + yellow < red) {
                return impossible;
            }

            StringBuilder sb = new StringBuilder();

            Tracker tracker = new Tracker(red, blue, yellow);
            while (tracker.hasNext()) {
                sb.append(tracker.next());
            }

            if (input.green > 0) {
                StringBuilder sb2 = new StringBuilder(input.green * 2);
                for (int i = 0; i < input.green; i++) {
                    sb2.append("BY");
                }
                int index = sb.indexOf("B");
                if (index >= 0) {
                    sb.insert(index, sb2);
                } else {
                    return impossible;
                }
            }

            if (input.orange > 0) {
                StringBuilder sb2 = new StringBuilder(input.orange * 2);
                for (int i = 0; i < input.orange; i++) {
                    sb2.append("RY");
                }
                int index = sb.indexOf("R");
                if (index >= 0) {
                    sb.insert(index, sb2);
                } else {
                    return impossible;
                }
            }

            if (input.violet > 0) {
                StringBuilder sb2 = new StringBuilder(input.violet * 2);
                for (int i = 0; i < input.violet; i++) {
                    sb2.append("RB");
                }
                int index = sb.indexOf("R");
                if (index >= 0) {
                    sb.insert(index, sb2);
                } else {
                    return impossible;
                }
            }

            if (sb.charAt(0) == sb.charAt(sb.length() - 1)) {
                char tmp = sb.charAt(sb.length() - 1);
                sb.setLength(sb.length() - 1);
                for (int i = 1; i < sb.length(); i++) {
                    char t1 = sb.charAt(i - 1);
                    char t2 = sb.charAt(i);
                    if (tmp != t1 && tmp != t2) {
                        sb.insert(i, tmp);
                        break;
                    }
                }
            }

            return sb::toString;
        }
    }

    static class Tracker implements Iterator<Character> {
        private char prev = '\0';

        private final int[] counts;
        private final int[] last = {0, 0, 0};
        private final char[] colors = {'R', 'B', 'Y'};
        private int pos = 0;

        private final int RED = 0;
        private final int BLUE = 1;
        private final int YELLOW = 2;

        public Tracker(int red, int blue, int yellow) {
            this.counts = new int[]{ red, blue, yellow };
        }

        @Override
        public boolean hasNext() {
            return IntStream.of(counts).anyMatch(x -> x > 0);
        }

        @Override
        public Character next() {
            pos++;
            char next = choose(prev);
            prev = next;
            return next;
        }

        private char choose(char prev) {
            switch (prev) {
                case 'R':
                    return choose(BLUE, YELLOW);
                case 'B':
                    return choose(RED, YELLOW);
                case 'Y':
                    return choose(RED, BLUE);
            }
            if (counts[RED] >= counts[BLUE] && counts[RED] >= counts[YELLOW]) {
                return choose(RED);
            }
            if (counts[BLUE] >= counts[RED] && counts[BLUE] >= counts[YELLOW]) {
                return choose(BLUE);
            }
            return choose(YELLOW);
        }

        private char choose(int a, int b) {
            if (counts[b] == 0 || counts[a] > counts[b]) {
                return choose(a);
            } else if (counts[a] == 0 || counts[b] > counts[a]) {
                return choose(b);
            }
            if (last[a] < last[b]) return choose(a);
            return choose(b);
        }

        private char choose(int c) {
            counts[c]--;
            last[c] = pos;
            return colors[c];
        }
    }

    @Override
    public void validate(Input input0, Answer answer) {
        UnicornsInput input = (UnicornsInput) input0;

        if (answer.value().equals("IMPOSSIBLE")) {
            return;
        }

        String stalls = answer.value().substring(answer.value().indexOf(':') + 1);
        if (input.stalls != stalls.length()) {
            throw new AssertionFailure(String.format("Got %d stalls, expected %d", stalls.length(), input.stalls));
        }

        for (int i = 0; i < stalls.length(); i++) {
            validate(stalls, i);
        }
    }

    private void validate(String stalls, int index) {
        char prev = stalls.charAt(index);
        char next = stalls.charAt(index == stalls.length() - 1 ? 0 : index + 1);
        if (!isValid(prev, next)) {
            throw new IllegalStateException(String.format("got %s after %s at pos %d/%d of %s", next, prev, index, stalls.length(), stalls));
        }
    }

    private boolean isValid(char prev, char next) {
        switch (prev) {
            case 'O':
                return next == 'B';
            case 'V':
                return next == 'Y';
            case 'G':
                return next == 'R';
            case 'R':
                return "BYG".indexOf(next) >= 0;
            case 'B':
                return "RYO".indexOf(next) >= 0;
            case 'Y':
                return "BRV".indexOf(next) >= 0;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Runner runner = Runner.create(new Unicorns());
        runner.run("B-large-practice.in");
    }
}
