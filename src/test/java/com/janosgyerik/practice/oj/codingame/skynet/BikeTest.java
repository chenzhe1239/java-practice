package com.janosgyerik.practice.oj.codingame.skynet;

import org.junit.Test;

import static org.junit.Assert.*;

public class BikeTest {

    private GameState applyMoves(GameState start, Move... moves) {
        return applyMoves(false, start, moves);
    }

    private GameState applyMoves(boolean debug, GameState start, Move... moves) {
        GameState state = start;
        for (Move move : moves) {
            if (debug) {
                System.out.println(state + " -> " + move);
            }
            state = state.transition(move);
            assertFalse(state.isLose());
        }
        return state;
    }

    @Test
    public void testLoseIfFailToJump() {
        GameState start = new GameState(11, 2, 20, 0, 0);
        GameState state = applyMoves(start, Move.SPEED, Move.SPEED, Move.SPEED, Move.SPEED);
        assertFalse(state.isLose());
        assertTrue(state.transition(Move.SPEED).isLose());
        assertTrue(state.transition(Move.WAIT).isLose());
        assertTrue(state.transition(Move.SLOW).isLose());
        assertFalse(state.transition(Move.JUMP).isLose());
    }

    @Test
    public void testTransitionsToVictory() {
        GameState start = new GameState(7, 2, 20, 0, 0);
        GameState state = applyMoves(start,
                Move.SPEED, Move.SPEED, Move.SPEED,
                Move.JUMP,
                Move.SLOW, Move.SLOW, Move.SLOW);
        assertTrue(state.isWin());
    }

    @Test
    public void testCanReachVictory() {
        assertTrue(new GameState(10, 2, 20, 0, 0).canReachVictory());
    }

    @Test
    public void testCannotReachVictory() {
        assertFalse(new GameState(10, 3, 2, 0, 0).canReachVictory());
        assertFalse(new GameState(0, 1, 1, 0, 0).canReachVictory());
        assertFalse(new GameState(3, 2, 5, 0, 0).canReachVictory());
    }

    @Test
    public void testIsWin() {
        assertTrue(new GameState(12, 2, 1, 0, 14).isWin());
        assertFalse(new GameState(12, 2, 1, 1, 15).isWin());
        assertFalse(new GameState(12, 2, 1, 0, 16).isWin());
        assertFalse(new GameState(12, 3, 1, 0, 14).isWin());
        assertFalse(new GameState(12, 3, 1, 0, 0).isWin());

        assertFalse(new GameState(12, 3, 1, 0, 12 + 3 - 1).isWin());
        assertTrue(new GameState(12, 3, 1, 0, 12 + 3).isWin());
        assertFalse(new GameState(12, 3, 1, 0, 12 + 3 + 1).isWin());
    }

    @Test
    public void testIsLose() {
        assertTrue(new GameState(12, 2, 1, 0, 12 + 1).isLose());
        assertTrue(new GameState(12, 2, 1, 0, 12 + 3).isLose());
        assertFalse(new GameState(12, 2, 1, 0, 12 + 2).isLose());
        assertFalse(new GameState(12, 2, 1, 0, 12 - 1).isLose());
    }

    @Test
    public void testTransition() {
        int speed = 2;
        int pos = 5;
        GameState start = new GameState(12, 3, 4, speed, pos);

        assertEquals(speed - 1, start.transition(Move.SLOW).speed);
        assertEquals(speed, start.transition(Move.WAIT).speed);
        assertEquals(speed, start.transition(Move.JUMP).speed);
        assertEquals(speed + 1, start.transition(Move.SPEED).speed);

        assertEquals(pos + speed - 1, start.transition(Move.SLOW).pos);
        assertEquals(pos + speed, start.transition(Move.WAIT).pos);
        assertEquals(pos + speed, start.transition(Move.JUMP).pos);
        assertEquals(pos + speed + 1, start.transition(Move.SPEED).pos);
    }


    private static String computeAction2(int road, int gap, int landing, int speed, int pos) {
        final String action;
        int maxSpeed = maxSpeedToSlowDownWithinDistance(landing);
        int minSpeed = gap + 1;
        if (pos >= road + gap) {
            action = "SLOW";
        } else if (pos + speed >= road + gap && speed <= maxSpeed) {
            action = "JUMP";
            //        } else if (isTooFast(road, gap, landing, speed, pos)) {
            //            action = "SLOW";
            //        } else if (isCurrentSpeedGood(road, gap, landing, speed, pos)) {
            //            action = "WAIT";
        } else {
            action = "SPEED";
        }
        return action;
    }

    class PossibleLanding {
        final int speed;
        final int jumpPosition;

        PossibleLanding(int speed, int jumpPosition) {
            this.speed = speed;
            this.jumpPosition = jumpPosition;
        }
    }


    private static boolean isSafeToSpeed(int road, int gap, int landing, int speed, int pos) {
        // SPEED* (WAIT | SLOW)* JUMP SLOW*
        // given landing length and speed, calculate required jump position
        // given jump position and speed, calculate if we should SPEED / SLOW / WAIT
        // - calculate if too fast: should SLOW
        // - calculate if no change will succeed: WAIT
        // - otherwise SPEED
        int distanceToGap = road - pos;
        int maxSpeed = maxSpeedToSlowDownWithinDistance(landing);
        int distanceNeeded = minDistanceNeededToSlowDown(speed + 1, maxSpeed);
        return distanceNeeded < distanceToGap - speed;
    }

    private static int maxSpeedToSlowDownWithinDistance(int distance) {
        int i = (int) Math.sqrt(distance);
        while (distance >= sumOfNums(i)) {
            ++i;
        }
        return i - 1;
    }

    private static int minDistanceNeededToSlowDown(int currentSpeed, int targetSpeed) {
        return sumOfNums(currentSpeed) - sumOfNums(targetSpeed);
    }

    private static int sumOfNums(int N) {
        return N * (N + 1) / 2;
    }

    @Test
    public void testMaxSpeedToSlowDownWithinDistance() {
        assertEquals(6, maxSpeedToSlowDownWithinDistance(21));
        assertEquals(6, maxSpeedToSlowDownWithinDistance(22));
        assertEquals(6, maxSpeedToSlowDownWithinDistance(23));
        assertEquals(6, maxSpeedToSlowDownWithinDistance(24));
        assertEquals(6, maxSpeedToSlowDownWithinDistance(25));
        assertEquals(6, maxSpeedToSlowDownWithinDistance(26));
        assertEquals(6, maxSpeedToSlowDownWithinDistance(27));
        assertEquals(7, maxSpeedToSlowDownWithinDistance(28));
    }

    @Test
    public void testMinDistanceNeededToSlowDown() {
        assertEquals(7, minDistanceNeededToSlowDown(7, 6));
        assertEquals(0, minDistanceNeededToSlowDown(6, 6));
        assertEquals(-6, minDistanceNeededToSlowDown(5, 6));
    }

    @Test
    public void testIsSafeToSpeed() {
        //        assertTrue(isSafeToSpeed(4, 7, 3));
    }

}
