package com.janosgyerik.practice.oj.adventofcode.y2016.day1;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class NoTimeForATaxicabTest {
    enum Direction {
        North(0, 1),
        East(1, 0),
        South(0, -1),
        West(-1, 0);

        private final int dx;
        private final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    static class CircularArray<T> {
        private final T[] values;
        private int index = 0;

        CircularArray(T[] values) {
            this.values = values;
        }

        T current() {
            return values[index];
        }

        void right() {
            if (index == values.length - 1) {
                index = 0;
            } else {
                index++;
            }
        }

        void left() {
            if (index == 0) {
                index = values.length - 1;
            } else {
                index--;
            }
        }
    }

    static class Walker {
        private int x = 0;
        private int y = 0;

        private CircularArray<Direction> direction = new CircularArray<>(Direction.values());

        public Walker turnAndWalk(String... instructions) {
            for (String instruction : instructions) {
                turn(instruction.charAt(0));
                walk(Integer.parseInt(instruction.substring(1)));
            }
            return this;
        }

        private void turn(char c) {
            switch (c) {
                case 'L':
                    direction.left();
                    break;
                case 'R':
                    direction.right();
                    break;
                default:
                    throw new IllegalStateException("invalid direction: " + c);
            }
        }

        private void walk(int steps) {
            x += direction.current().dx * steps;
            y += direction.current().dy * steps;
        }

        int distance() {
            return Math.abs(x) + Math.abs(y);
        }
    }

    @Test
    public void should_get_2_for_turn_right_and_walk_2() {
        assertThat(new Walker().turnAndWalk("R2").distance()).isEqualTo(2);
    }

    @Test
    public void should_get_4_for_R2_R2() {
        assertThat(new Walker().turnAndWalk("R2").turnAndWalk("R2").distance()).isEqualTo(4);
    }

    @Test
    public void should_get_2_for_R2_R2_R2() {
        assertThat(new Walker().turnAndWalk("R2").turnAndWalk("R2").turnAndWalk("R2").distance()).isEqualTo(2);
    }

    @Test
    public void should_get_0_for_R2_R2_R2_R2() {
        assertThat(new Walker()
                .turnAndWalk("R2")
                .turnAndWalk("R2")
                .turnAndWalk("R2")
                .turnAndWalk("R2")
                .distance()).isEqualTo(0);
    }

    @Test
    public void should_get_0_for_L2_L2_L2_L2() {
        assertThat(new Walker()
                .turnAndWalk("L2")
                .turnAndWalk("L2")
                .turnAndWalk("L2")
                .turnAndWalk("L2")
                .distance()).isEqualTo(0);
    }

    String input = "R1, L3, R5, R5, R5, L4, R5, R1, R2, L1, L1, R5, R1, L3, L5, L2, R4, L1, R4, R5, L3, R5, L1, R3, L5, R1, L2, R1, L5, L1, R1, R4, R1, L1, L3, R3, R5, L3, R4, L4, R5, L5, L1, L2, R4, R3, R3, L185, R3, R4, L5, L4, R48, R1, R2, L1, R1, L4, L4, R77, R5, L2, R192, R2, R5, L4, L5, L3, R2, L4, R1, L5, R5, R4, R1, R2, L3, R4, R4, L2, L4, L3, R5, R4, L2, L1, L3, R1, R5, R5, R2, L5, L2, L3, L4, R2, R1, L4, L1, R1, R5, R3, R3, R4, L1, L4, R1, L2, R3, L3, L2, L1, L2, L2, L1, L2, R3, R1, L4, R1, L1, L4, R1, L2, L5, R3, L5, L2, L2, L3, R1, L4, R1, R1, R2, L1, L4, L4, R2, R2, R2, R2, R5, R1, L1, L4, L5, R2, R4, L3, L5, R2, R3, L4, L1, R2, R3, R5, L2, L3, R3, R1, R3";

    @Test
    public void should_find_bunny_hq_at_298() {
        assertThat(new Walker().turnAndWalk(input.split(", ")).distance()).isEqualTo(298);
    }

    @Test
    public void should_find_first_location_visited_twice_at_158() {
        assertThat(distanceToFirstVisitedTwice(input)).isEqualTo(158);
    }

    public int location(int x, int y) {
        // super-lazy serialization of two numbers to one
        return (x << 10) + y;
    }

    private int distanceToFirstVisitedTwice(String input) {
        Set<Integer> visited = new HashSet<>();

        Walker walker = new Walker();
        visited.add(0);
        for (String instruction : instructions(input)) {
            walker.turn(instruction.charAt(0));
            for (int i = 0; i < Integer.parseInt(instruction.substring(1)); i++) {
                walker.walk(1);
                if (!visited.add(location(walker.x, walker.y))) {
                    return walker.distance();
                }
            }
        }
        throw new IllegalStateException("should have found destination");
    }

    private String[] instructions(String input) {
        return input.split(", ");
    }
}
