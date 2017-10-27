package com.janosgyerik.practice.oj.codejam.codejam2017.template;

import com.janosgyerik.practice.oj.codejam.codejam2017.common.*;

import java.util.Scanner;

public class ProblemA implements Problem {
    @Override
    public Inputs inputs(Scanner scanner) {
        Inputs inputs = new Inputs();

        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            long n = scanner.nextLong();
            long k = scanner.nextLong();
            inputs.add(new ProblemAInput());
            // TODO
        }
        return inputs;
    }

    static class ProblemAInput implements Input {
        ProblemAInput() {

        }
    }

    @Override
    public Solver solver(Inputs inputs) {
        return new ProblemASolver();
    }

    static class ProblemASolver implements Solver {
        @Override
        public Answer solve(Input input0) {
            ProblemAInput input = (ProblemAInput) input0;

            // TODO
            return new ProblemAAnswer();
        }
    }

    static class ProblemAAnswer implements Answer {
        @Override
        public String value() {
            // TODO
            return null;
        }
    }

}
