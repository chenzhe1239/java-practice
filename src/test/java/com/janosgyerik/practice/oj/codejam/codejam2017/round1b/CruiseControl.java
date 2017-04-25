package com.janosgyerik.practice.oj.codejam.codejam2017.round1b;

import com.janosgyerik.practice.oj.codejam.codejam2017.common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CruiseControl implements Problem {
    @Override
    public Inputs inputs(Scanner scanner) {
        Inputs inputs = new Inputs();

        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            long dest = scanner.nextLong();
            long n = scanner.nextLong();
            List<Horse> horses = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                long start = scanner.nextLong();
                int speed = scanner.nextInt();
                horses.add(new Horse(start, speed));
            }
            inputs.add(new CruiseControlInput(dest, horses));
        }
        return inputs;
    }

    static class Horse {
        long start;
        double speed;

        Horse(long start, int speed) {
            this.start = start;
            this.speed = speed;
        }
    }

    static class CruiseControlInput implements Input {
        private final long dest;
        private final List<Horse> horses;

        CruiseControlInput(long dest, List<Horse> horses) {
            this.dest = dest;
            this.horses = horses;
        }
    }

    @Override
    public Solver solver(Inputs inputs) {
        return new CruiseControlSolver();
    }

    static class CruiseControlSolver implements Solver {
        @Override
        public Answer solve(Input input0) {
            CruiseControlInput input = (CruiseControlInput) input0;

            if (input.horses.isEmpty()) {
                return () -> "1";
            }

            List<Horse> horses = input.horses;
            horses.sort((a, b) -> -Long.compare(a.start, b.start));

            Horse current = horses.get(0);
            double hours = (input.dest - current.start) / current.speed;
            for (int i = 1; i < horses.size(); i++) {
                Horse other = horses.get(i);
                double otherHours = (input.dest - other.start) / other.speed;
                if (other.speed > current.speed) {
                    hours = Math.max(hours, otherHours);
                } else {
                    hours = otherHours;
                    current = other;
                }
            }

            final double answer = input.dest / hours;

            return () -> String.valueOf(answer);
        }
    }

}
