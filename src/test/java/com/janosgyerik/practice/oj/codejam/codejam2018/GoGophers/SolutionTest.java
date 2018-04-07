package com.janosgyerik.practice.oj.codejam.codejam2018.GoGophers;

import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SolutionTest {
    @Test
    public void test_solver_1() {
        Solution.Solver solver = new Solution.Solver();
        solver.init(20);
        solver.summary();

        solver.prepare(2, 3);
        solver.prepare(3, 2);
        solver.prepare(4, 2);
        solver.summary();

        System.out.println(solver.candidates.poll());
    }

    @Test
    public void test_solver_10() {
        Solution.Solver solver = new Solution.Solver();
        solver.init(10);
        solver.summary();

        solver.prepare(1, 3);
        solver.prepare(2, 2);
        solver.prepare(3, 2);
        solver.prepare(4, 2);
        solver.prepare(1, 1);
        solver.prepare(3, 1);
        solver.prepare(4, 1);
        solver.prepare(2, 3);
        solver.prepare(2, 1);
        solver.summary();
        System.out.println(solver.candidates.size());

        System.out.println(solver.candidates.poll());
    }
}
