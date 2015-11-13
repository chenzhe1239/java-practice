package com.janosgyerik.practice.oj.codingame.medium.heat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void testMoveRight() {
        BatmanState state = new BatmanState(2, 5);
        assertEquals(state.x + 1, state.transition(Direction.R).x);
        assertEquals(state.y, state.transition(Direction.R).y);
    }

    @Test
    public void testMoveDL() {
        BatmanState state = new BatmanState(2, 5);
        assertEquals(state.x - 1, state.transition(Direction.DL).x);
        assertEquals(state.y + 1, state.transition(Direction.DL).y);
    }

    @Test
    public void testPossibilityWindowByRight() {
        PossibilityWindow window = new PossibilityWindow(0, 0, 9, 9);
        window.narrowByDirection(2, 2, Direction.R);
        assertEquals(2, window.minY);
        assertEquals(2, window.maxY);
        assertEquals(3, window.minX);
        assertEquals(9, window.maxX);
    }

    @Test
    public void testPossibilityWindowByLeft() {
        PossibilityWindow window = new PossibilityWindow(0, 0, 9, 9);
        window.narrowByDirection(2, 2, Direction.L);
        assertEquals(2, window.minY);
        assertEquals(2, window.maxY);
        assertEquals(0, window.minX);
        assertEquals(1, window.maxX);
    }

    @Test
    public void testPossibilityWindowByUp() {
        PossibilityWindow window = new PossibilityWindow(0, 0, 9, 9);
        window.narrowByDirection(2, 2, Direction.U);
        assertEquals(0, window.minY);
        assertEquals(1, window.maxY);
        assertEquals(2, window.minX);
        assertEquals(2, window.maxX);
    }

    @Test
    public void testPossibilityWindowByDown() {
        PossibilityWindow window = new PossibilityWindow(0, 0, 9, 9);
        window.narrowByDirection(2, 2, Direction.D);
        assertEquals(3, window.minY);
        assertEquals(9, window.maxY);
        assertEquals(2, window.minX);
        assertEquals(2, window.maxX);
    }

    @Test
    public void testPossibilityWindowByUpRight() {
        PossibilityWindow window = new PossibilityWindow(0, 0, 9, 9);
        window.narrowByDirection(2, 2, Direction.UR);
        assertEquals(0, window.minY);
        assertEquals(1, window.maxY);
        assertEquals(3, window.minX);
        assertEquals(9, window.maxX);
    }
}
