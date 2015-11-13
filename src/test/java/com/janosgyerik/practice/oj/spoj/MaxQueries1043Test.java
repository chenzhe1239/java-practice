package com.janosgyerik.practice.oj.spoj;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class MaxQueries1043Test {
    private static class Range implements Comparable<Range> {
        private final int start;
        private final int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("[%s,%s]", start, end);
        }

        @Override
        public int compareTo(Range o) {
            int cmp = Integer.compare(start, o.start);
            if (cmp != 0) {
                return cmp;
            }
            return Integer.compare(end, o.end);
        }
    }

    private static class InputData {
        private final int[] arr;
        private final Range[] queries;

        public InputData(int[] arr, Range[] queries) {
            this.arr = arr;
            this.queries = queries;
        }

        static InputData fromScanner(Scanner scanner) {
            int numCount = scanner.nextInt();

            int[] arr = new int[numCount];
            for (int i = 0; i < numCount; ++i) {
                int num = scanner.nextInt();
                arr[i] = num;
            }

            int queryCount = scanner.nextInt();

            Range[] queries = new Range[queryCount];
            for (int i = 0; i < queryCount; ++i) {
                int start = scanner.nextInt() - 1;
                int end = scanner.nextInt() - 1;
                queries[i] = new Range(start, end);
            }

            return new InputData(arr, queries);
        }
    }

    public static void main(String[] args) {
        System.out.println(solveComplete(new Scanner(System.in)));
    }

    private static String solveComplete(Scanner scanner) {
        InputData data = InputData.fromScanner(scanner);

        Map<Range, Integer> maxSums = buildMaxSums(data.arr);

        StringBuilder builder = new StringBuilder();
        for (Range range : data.queries) {
            builder.append(maxSums.get(range));
        }
        return builder.toString();
    }

    public static Map<Range, Integer> buildMaxSums(int[] arr) {
        Map<Range, Integer> map = new TreeMap<>();
        for (int i = 0; i < arr.length; ++i) {
            int maxSumEndingAt = 0;
            int maxSumSoFar = 0;
            for (int j = i; j < arr.length; ++j) {
                maxSumEndingAt += arr[j];
                if (maxSumEndingAt < 0) {
                    maxSumEndingAt = 0;
                }
                maxSumSoFar = Math.max(maxSumEndingAt, maxSumSoFar);
                map.put(new Range(i, j), maxSumSoFar);
            }
        }
        return map;
    }

    @Test
    public void testMaxSumsMap_1_2_3() {
        assertEquals("{[0,0]=1, [0,1]=3, [0,2]=6, [1,1]=2, [1,2]=5, [2,2]=3}",
                buildMaxSums(new int[]{1, 2, 3}).toString());
    }

    @Test
    public void testMaxSumsMap_1_m2_3() {
        assertEquals("{[0,0]=1, [0,1]=0, [0,2]=3, [1,1]=0, [1,2]=3, [2,2]=3}",
                buildMaxSums(new int[]{1, -2, 3}).toString());
    }

    @Test
    public void testMaxSumsMap_m2_1_m3_4_m1_2_1_m5_4() {
        assertEquals("{" +
                        "[0,0]=0, [0,1]=1, [0,2]=0, [0,3]=4, [0,4]=3, [0,5]=5, [0,6]=6, [0,7]=1, [0,8]=5, " +
                        "[1,1]=1, [1,2]=0, [1,3]=4, [1,4]=3, [1,5]=5, [1,6]=6, [1,7]=1, [1,8]=5, " +
                        "[2,2]=0, [2,3]=4, [2,4]=3, [2,5]=5, [2,6]=6, [2,7]=1, [2,8]=5, " +
                        "[3,3]=4, [3,4]=3, [3,5]=5, [3,6]=6, [3,7]=1, [3,8]=5, " +
                        "[4,4]=0, [4,5]=2, [4,6]=3, [4,7]=0, [4,8]=4, " +
                        "[5,5]=2, [5,6]=3, [5,7]=0, [5,8]=4, " +
                        "[6,6]=1, [6,7]=0, [6,8]=4, [7,7]=0, [7,8]=4, [8,8]=4}",
                buildMaxSums(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}).toString());
    }

    private Scanner getSimpleExample() {
        return new Scanner("3 \n" +
                "-1 2 3\n" +
                "1\n" +
                "1 2");
    }

    @Test
    public void testInputParsing() {
        InputData data = InputData.fromScanner(getSimpleExample());
        assertEquals("[-1, 2, 3]", Arrays.toString(data.arr));
        assertEquals(1, data.queries.length);
        assertEquals(0, data.queries[0].start);
        assertEquals(1, data.queries[0].end);
    }

    @Test
    public void testCompleteExample1() {
        assertEquals("2", solveComplete(new Scanner("3 \n" +
                "-1 2 3\n" +
                "1\n" +
                "1 2")));
    }

    @Test
    public void testCompleteExample2() {
        assertEquals("29", solveComplete(new Scanner("9 \n" +
                "9 -10 3 0 3 12 10 1 0 \n" +
                "1 \n" +
                "1 9")));
    }

    @Test
    public void testCompleteExample3() {
        assertEquals("2", solveComplete(new Scanner("6 \n" +
                "-1 1 -1 -1 2 3 \n" +
                "1 \n" +
                "2 5")));
    }

    @Test
    public void testCompleteExample4() {
        assertEquals("7", solveComplete(new Scanner("8 \n" +
                "-1 5 -3 5 -1 -1 -1 4 \n" +
                "1 \n" +
                "2 4")));
    }

    @Test
    public void testCompleteExample5() {
        System.out.println(buildMaxSums(new int[]{-200, 3, 4, -200, 6, 2, 4, -200, 5, 6}).toString());
        assertEquals("12", solveComplete(new Scanner("10 \n" +
                "-200 3 4 -200 6 2 4 -200 5 6 \n" +
                "1 \n" +
                "1 10")));
    }
}
