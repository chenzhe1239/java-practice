package com.janosgyerik.practice.oj.answers.ReachANumber;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setMaxLengthForSingleLineDescription;

public class Solution {
    public int reachNumber1(int target) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        int depth = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                int value = queue.poll();

                if (value == target) {
                    return depth - 1;
                }

                queue.add(value + depth);
                queue.add(value - depth);
            }
            depth++;
        }
        return -1;
    }

    public int reachNumber(int target) {
        class Holder {
            private final int value;
            private final int direction;
            private final int turns;

            Holder(int value, int direction, int turns) {
                this.value = value;
                this.direction = direction;
                this.turns = turns;
            }

            public Holder sameDirection(int depth) {
                return new Holder(value + direction * depth, direction, turns);
            }

            public boolean canTurn() {
                return turns < 3;
            }

            public Holder turn(int depth) {
                return new Holder(value - direction * depth, -direction, turns + 1);
            }
        }

        Queue<Holder> queue = new LinkedList<>();
        queue.add(new Holder(0, 1, 0));

        int depth = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                Holder value = queue.poll();

                if (value.value == target) {
                    return depth - 1;
                }

                queue.add(value.sameDirection(depth));
                if (value.canTurn()) {
                    queue.add(value.turn(depth));
                }
            }
            depth++;
        }
        return -1;
    }

    @Test
    public void test_target_3() {
        assertThat(reachNumber(3)).isEqualTo(2);
    }

    @Test
    public void test_target_2() {
        assertThat(reachNumber(2)).isEqualTo(3);
    }

    @Test
    public void test_target_4() {
        assertThat(reachNumber(4)).isEqualTo(3);
    }

    @Test
    public void test_target_5() {
        assertThat(reachNumber(5)).isEqualTo(5);
    }

    @Test
    public void test_target_7() {
        assertThat(reachNumber(7)).isEqualTo(5);
    }

    @Test
    public void test_target_8() {
        assertThat(reachNumber(8)).isEqualTo(4);
    }

    @Test
    public void test_target_9() {
        assertThat(reachNumber(9)).isEqualTo(5);
    }

    @Test
    public void test_target_11() {
        assertThat(reachNumber(11)).isEqualTo(5);
    }

    @Test
    public void test_target_12() {
        assertThat(reachNumber(12)).isEqualTo(7);
    }

    @Test
    public void test_counts() {
        int prev = 0;
        for (int i = 1; i < 10; i++) {
            int current = reachNumber(i);
//            System.out.printf("%d %d %d\n", i, current, current - prev);
            prev = current;
        }
    }

    @Test
    public void test_212() {
        assertThat(reachNumber(212)).isEqualTo(23);
    }

    @Test
    public void test_10_000() {
        assertThat(reachNumber(10_000)).isEqualTo(143);
    }
}
