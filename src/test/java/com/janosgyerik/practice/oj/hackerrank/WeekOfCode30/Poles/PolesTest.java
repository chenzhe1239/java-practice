package com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.Poles;

import com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.Poles.Solution.Allocation;
import com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.Poles.Solution.Pole;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PolesTest {
    private List<Pole> poles(int... pairs) {
        List<Pole> poles = new ArrayList<>();
        for (int i = 0; i < pairs.length; i += 2) {
            poles.add(new Pole(i / 2, pairs[i], pairs[i + 1]));
        }
        return poles;
    }

    int compute(List<Pole> poles, int k) {
        return new Allocation(poles).computeMinCost(k);
    }

    private List<Pole> poles(Random random, int count) {
        List<Pole> poles = new ArrayList<>(count);
        int altitude = 123;
        for (int i = 0; i < count; i++) {
            altitude += 1 + random.nextInt(100);
            poles.add(new Pole(i, altitude, 1 + random.nextInt(100)));
        }
        return poles;
    }

    @Test
    public void test_example_1() {
        List<Pole> poles = poles(20, 1, 30, 1, 40, 1);
        assertThat(compute(poles, 1)).isEqualTo(30);
    }

    @Test
    public void test_example_2() {
        List<Pole> poles = poles(10, 15, 12, 17, 16, 18, 18, 13, 30, 10, 32, 1);
//        assertThat(compute(poles, 1)).isEqualTo(468);
        assertThat(compute(poles, 2)).isEqualTo(216);
    }

    @Test
    public void test_example_2_2() {
        List<Pole> poles = poles(10, 15, 12, 17, 16, 18, 18, 13, 30, 10, 32, 1);
        assertThat(compute(poles, 3)).isEqualTo(62);
    }

    @Test
    public void test_example_2_3() {
        List<Pole> poles = poles(10, 15, 12, 17, 16, 18, 18, 13, 30, 10, 32, 1);
        Collections.reverse(poles);
        int[] alt = poles.stream().mapToInt(p -> p.altitude).toArray();
        int[] wt = poles.stream().mapToInt(p -> p.weight).toArray();
        int k = 5;
        int[][] C = new int[k][alt.length];
        for (int i = 0; i < alt.length; i++) {
            C[0][i] = 0;
            for (int j = 0; j < i; j++) {
                C[0][i] += wt[j] * (alt[j] - alt[i]);
            }
        }
        for (int cc = 1; cc < k; cc++) {
            for (int j = cc; j < alt.length; j++) {
                int min = Integer.MAX_VALUE;
                for (int q = cc - 1; q < j; q++) {
                    int cost = C[cc - 1][q];
                    for (int qq = q + 1; qq < j; qq++) {
                        cost += wt[qq] * (alt[qq] - alt[j]);
                    }
                    min = Math.min(min, cost);
                }
                C[cc][j] = min;
            }
        }
    }

    @Test
    public void test_my_example_heavy_top() {
        List<Pole> poles = poles(10, 15, 12, 17, 16, 18, 18, 13, 30, 10);
        assertThat(compute(poles, 1)).isEqualTo(446);
        poles.add(new Pole(poles.size(), 32, 10000));
        assertThat(compute(poles, 2)).isEqualTo(446);
    }

    @Test
    public void test_my_example_1_slow() {
        List<Pole> poles = poles(new Random(1), 100);
        assertThat(compute(poles, 1)).isEqualTo(13222112);
        assertThat(compute(poles, 2)).isEqualTo(6237056);
        assertThat(compute(poles, 3)).isEqualTo(4034830);
        assertThat(compute(poles, 4)).isEqualTo(2868042);
    }

    @Test
    public void test_my_example_2_slower() {
        List<Pole> poles = poles(new Random(1), 50);
        assertThat(compute(poles, 1)).isEqualTo(3152791);
        assertThat(compute(poles, 2)).isEqualTo(1330845);
        assertThat(compute(poles, 3)).isEqualTo(930961);
        assertThat(compute(poles, 4)).isEqualTo(602868);
        assertThat(compute(poles, 5)).isEqualTo(484207);
    }

    @Test
    public void test_my_example_3_slow() {
        List<Pole> poles = poles(new Random(1), 15);
        assertThat(compute(poles, 1)).isEqualTo(321356);
        assertThat(compute(poles, 2)).isEqualTo(131636);
        assertThat(compute(poles, 3)).isEqualTo(63904);
        assertThat(compute(poles, 4)).isEqualTo(42422);
        assertThat(compute(poles, 5)).isEqualTo(31789);
        assertThat(compute(poles, 6)).isEqualTo(21244);
        assertThat(compute(poles, 7)).isEqualTo(12514);
        assertThat(compute(poles, 8)).isEqualTo(7354);
    }

    @Test
    public void test_my_example_3_v() {
        List<Pole> poles = poles(new Random(1), 15).subList(0, 6);
        assertThat(compute(poles, 2)).isEqualTo(7333);
    }

//    @Test
//    public void test_computeMinCost() {
//        List<Pole> poles = poles(10, 15, 12, 17, 16, 18, 18, 13, 30, 10, 32, 1);
//
//        poles.get(1).fix();
//        assertThat(new Allocation(poles).computeCost()).isEqualTo(350);
//        poles.get(1).unfix();
//
//        poles.get(2).fix();
//        assertThat(new Allocation(poles).computeCost()).isEqualTo(216);
//        poles.get(2).unfix();
//
//        poles.get(3).fix();
//        assertThat(new Allocation(poles).computeCost()).isEqualTo(276);
//        poles.get(3).unfix();
//
//        poles.get(4).fix();
//        assertThat(new Allocation(poles).computeCost()).isEqualTo(248);
//        poles.get(4).unfix();
//
//        poles.get(5).fix();
//        assertThat(new Allocation(poles).computeCost()).isEqualTo(446);
//        poles.get(5).unfix();
//    }
}
