package com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.RangeModularQueries;

import com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.RangeModularQueries.Solution.BruteForceSolver;
import com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.RangeModularQueries.Solution.Query;
import com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.RangeModularQueries.Solution.SimpleCachingSolver;
import com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.RangeModularQueries.Solution.Solver;
import org.junit.Test;
import sun.awt.image.IntegerComponentRaster;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class RangeModularQueriesTest {
    static class Input {
        private final int[] a;
        private final List<Query> queries;

        public Input(int[] a, List<Query> queries) {
            this.a = a;
            this.queries = queries;
        }

        static Input fromString(String s) {
            Scanner in = new Scanner(s);
            int n = in.nextInt();
            int q = in.nextInt();
            int[] a = new int[n];
            for (int a_i = 0; a_i < n; a_i++) {
                a[a_i] = in.nextInt();
            }

            List<Query> queries = new ArrayList<>(q);
            for (int a0 = 0; a0 < q; a0++) {
                int left = in.nextInt();
                int right = in.nextInt();
                int x = in.nextInt();
                int y = in.nextInt();
                queries.add(new Query(left, right, x, y));
            }

            return new Input(a, queries);
        }
    }

    interface SolverFactory {
        Solver create(Input input);
    }

    //    SolverFactory sf = input -> new BruteForceSolver(input.a);
    SolverFactory sf = input -> new SimpleCachingSolver(input.a, input.queries);

    private int[] solve(String s) {
        Input input = Input.fromString(s);
        return solve(input);
    }

    private int[] solve(Input input) {
        Solver solver = sf.create(input);
        return input.queries.stream().map(solver::solve).mapToInt(Integer::valueOf).toArray();
    }

    Input gen(Random random, int numCount, int queryCount) {
        int[] nums = IntStream.range(0, numCount).map(i -> random.nextInt(40000)).toArray();
        List<Query> queries = IntStream.range(0, queryCount).mapToObj(i -> {
            int left = random.nextInt(numCount - 1);
            int right = left + random.nextInt(numCount - left);
            int x = 1 + random.nextInt(10);
            int y = random.nextInt(x);
            return new Query(left, right, x, y);
        }).collect(Collectors.toList());
        return new Input(nums, queries);
    }

    @Test
    public void test_example_1() {
        String s = "5 3\n" +
                "250 501 5000 5 4\n" +
                "0 4 5 0\n" +
                "0 4 10 0\n" +
                "0 4 3 2\n";
        assertThat(solve(s)).containsExactly(3, 2, 2);
    }

    @Test
    public void test_gen_40000_1000() {
        Input input = gen(new Random(1), 40000, 10000);
        int[] expected = loadExpectedIntArray();
        assertThat(solve(input)).containsExactly(expected);
    }

    @Test
    public void test_gen_40000_2000() {
        Input input = gen(new Random(2), 40000, 20000);
        int[] expected = loadExpectedIntArray();
        assertThat(solve(input)).containsExactly(expected);
    }

    private int[] loadExpectedIntArray() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String propName = "expected." + stackTrace[2].getMethodName();
        final Properties properties = new Properties();
        String basepath = "src/test/java/com/janosgyerik/practice/oj/hackerrank/WeekOfCode30/RangeModularQueries";
        String path = basepath + "/expected.properties";
        try (FileInputStream stream = new FileInputStream(path)) {
            properties.load(stream);
            return Stream.of(properties.getProperty(propName).split(",\\s*")).mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("could not load expected.properties");
        }
    }
}
