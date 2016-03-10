package com.janosgyerik.practice.misc.geneva;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class AlgorithmicCrush {

    public static void main(String[] args) {
        System.out.println(new AlgorithmicCrush().solve(new Scanner(System.in)));
    }

    private static class Operation {
        final int start;
        final int end;
        final int value;

        private Operation(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }

    private static class Input {
        final int n;
        final int m;
        final List<Operation> operations;

        Input(int n, int m, List<Operation> operations) {
            this.n = n;
            this.m = m;
            this.operations = operations;
        }
    }

    private Input parseInput(Scanner scanner) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Operation> operations = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            int start = scanner.nextInt() - 1;
            int end = scanner.nextInt() - 1;
            int value = scanner.nextInt();
            operations.add(new Operation(start, end, value));
        }
        return new Input(n, m, operations);
    }

    protected long solve(Scanner scanner) {
        return findMaxValueAfterOperations(parseInput(scanner));
    }

    private long findMaxValueAfterOperations(Input input) {
        long[] arr = new long[input.n + 1];
        for (Operation operation : input.operations) {
            arr[operation.start] += operation.value;
            arr[operation.end + 1] -= operation.value;
        }

        long prefixSum = 0;
        long max = 0;
        for (long value : arr) {
            prefixSum += value;
            max = Math.max(max, prefixSum);
        }
        return max;
    }
}
