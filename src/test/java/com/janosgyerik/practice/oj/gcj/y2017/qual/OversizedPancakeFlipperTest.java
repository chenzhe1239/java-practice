package com.janosgyerik.practice.oj.gcj.y2017.qual;

import com.janosgyerik.practice.oj.gcj.y2017.qual.OversizedPancakeFlipper.Inputs;
import com.janosgyerik.practice.oj.gcj.y2017.qual.OversizedPancakeFlipper.MultiSolver;
import com.janosgyerik.practice.oj.gcj.y2017.qual.OversizedPancakeFlipper.Solver1.PossibleStartIterator;
import org.junit.*;

import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

import static com.janosgyerik.practice.oj.gcj.y2017.qual.OversizedPancakeFlipper.bits;
import static com.janosgyerik.practice.oj.gcj.y2017.qual.OversizedPancakeFlipper.simplify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class OversizedPancakeFlipperTest {
    OversizedPancakeFlipper.Solver solver = new OversizedPancakeFlipper.Solver1(null);

    String solve(String pancakes, int size) {
        return solver.solve(new OversizedPancakeFlipper.Input(pancakes, size)).toString();
    }

    @Test
    public void test_example_1() {
        assertThat(solve("---+-++-", 3)).isEqualTo("3");
    }

    @Test
    public void test_example_2() {
        assertThat(solve("+++++", 4)).isEqualTo("0");
    }

    @Test
    public void test_example_3() {
        assertThat(solve("-+-+-", 4)).isEqualTo("IMPOSSIBLE");
    }

    @Test
    public void test_bits() {
        BitSet bits = bits("-+-+-");
        assertThat(bits.toString()).isEqualTo("{1, 3}");
    }

    @Test
    public void test_simplify() {
        BitSet bits = bits("-+---");
        assertThat(simplify(bits, 5, 3)).isEqualTo(1);
        assertThat(bits.toString()).isEqualTo("{1, 2, 3, 4}");
    }

    @Test
    public void test_iterator() {
        BitSet bits = bits("++++-++-");
        PossibleStartIterator it = new PossibleStartIterator(bits, 3);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }


    @Test
    public void test_example() {
        String input = "3\n" +
                "---+-++- 3\n" +
                "+++++ 4\n" +
                "-+-+- 4";

        String expected = "Case #1: 3\n" +
                "Case #2: 0\n" +
                "Case #3: IMPOSSIBLE\n";

        MultiSolver msolver = new MultiSolver(solver);
        Inputs inputs = Inputs.parse(new Scanner(input));
        assertThat(msolver.solve(inputs)).isEqualTo(expected);
    }

    //@Test
    public void test_larger_data() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random(1);
        for (int i = 0; i < 1000; i++) {
            sb.append(random.nextBoolean() ? '+' : '-');
        }
        assertThat(solve(sb.toString(), 500)).isEqualTo("IMPOSSIBLE");
    }

}
