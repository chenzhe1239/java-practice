package com.janosgyerik.practice.oj.codejam.codejam2017.common;

import java.util.Scanner;

public interface Problem {
    Inputs inputs(Scanner scanner);

    Solver solver(Inputs inputs);
}
