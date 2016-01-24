package com.janosgyerik.practice.oj.codingame.medium.ParanoidAndroid;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParanoidAndroidTest {
    @Test
    public void test_wait_if_direction_matches_exit_floor_0() {
        int exitFloor = 0;
        int exitPos = 5;

        Position clone = new Position(exitFloor, exitPos - 1);
        Direction cloneDirection = Direction.RIGHT;

        Game game = new GameBuilder()
                .setExitFloor(exitFloor)
                .setExitPos(exitPos)
                .createGame();

        assertEquals(Move.WAIT, game.nextMove(clone, cloneDirection));
    }

    @Test
    public void test_block_if_direction_opposite_exit_floor_0() {
        int exitFloor = 0;
        int exitPos = 5;

        Position clone = new Position(exitFloor, exitPos - 1);
        Direction cloneDirection = Direction.LEFT;

        Game game = new GameBuilder()
                .setExitFloor(exitFloor)
                .setExitPos(exitPos)
                .createGame();

        assertEquals(Move.BLOCK, game.nextMove(clone, cloneDirection));
    }

    @Test
    public void test_wait_if_direction_matches_exit_floor_1() {
        int exitFloor = 1;
        int exitPos = 5;

        Position clone = new Position(exitFloor, exitPos - 1);
        Direction cloneDirection = Direction.RIGHT;

        Game game = new GameBuilder()
                .setExitFloor(exitFloor)
                .setExitPos(exitPos)
                .setElevators(Collections.singletonList(new Position(0, 0)))
                .createGame();

        assertEquals(Move.WAIT, game.nextMove(clone, cloneDirection));
    }

    @Test
    public void test_block_if_direction_opposite_exit_floor_1() {
        int exitFloor = 1;
        int exitPos = 5;

        Position clone = new Position(exitFloor, exitPos - 1);
        Direction cloneDirection = Direction.LEFT;

        Game game = new GameBuilder()
                .setExitFloor(exitFloor)
                .setExitPos(exitPos)
                .setElevators(Collections.singletonList(new Position(0, 0)))
                .createGame();

        assertEquals(Move.BLOCK, game.nextMove(clone, cloneDirection));
    }

    @Test
    public void test_wait_if_direction_matches_elevator() {
        int elevatorFloor = 0;
        int elevatorPos = 5;

        List<Position> elevators = Collections.singletonList(new Position(elevatorFloor, elevatorPos));

        Position clone = new Position(elevatorFloor, elevatorPos - 1);
        Direction cloneDirection = Direction.RIGHT;

        Game game = new GameBuilder()
                .setElevators(elevators)
                .createGame();

        assertEquals(Move.WAIT, game.nextMove(clone, cloneDirection));
    }

    @Test
    public void test_block_if_direction_opposite_elevator() {
        int elevatorFloor = 0;
        int elevatorPos = 5;

        List<Position> elevators = Collections.singletonList(new Position(elevatorFloor, elevatorPos));

        Position clone = new Position(elevatorFloor, elevatorPos - 1);
        Direction cloneDirection = Direction.LEFT;

        Game game = new GameBuilder()
                .setElevators(elevators)
                .createGame();

        assertEquals(Move.BLOCK, game.nextMove(clone, cloneDirection));
    }

    @Test
    public void test_wait_if_wrong_direction_but_above_elevator() {
        int elevatorFloor = 1;
        int elevatorPos = 5;
        int clonePos = 3;

        List<Position> elevators = Arrays.asList(
                new Position(elevatorFloor - 1, clonePos),
                new Position(elevatorFloor, elevatorPos)
        );

        Position clone = new Position(elevatorFloor, clonePos);
        Direction cloneDirection = Direction.LEFT;

        Game game = new GameBuilder()
                .setElevators(elevators)
                .createGame();

        assertEquals(Move.WAIT, game.nextMove(clone, cloneDirection));
    }

    @Test
    public void test_wait_if_at_elevator_going_right() {
        int elevatorFloor = 0;
        int elevatorPos = 5;

        List<Position> elevators = Collections.singletonList(new Position(elevatorFloor, elevatorPos));

        Position clone = new Position(elevatorFloor, elevatorPos);
        Direction cloneDirection = Direction.RIGHT;

        Game game = new GameBuilder()
                .setElevators(elevators)
                .createGame();

        assertEquals(Move.WAIT, game.nextMove(clone, cloneDirection));
    }

    @Test
    public void test_wait_if_at_elevator_going_left() {
        int elevatorFloor = 0;
        int elevatorPos = 5;

        List<Position> elevators = Collections.singletonList(new Position(elevatorFloor, elevatorPos));

        Position clone = new Position(elevatorFloor, elevatorPos);
        Direction cloneDirection = Direction.LEFT;

        Game game = new GameBuilder()
                .setElevators(elevators)
                .createGame();

        assertEquals(Move.WAIT, game.nextMove(clone, cloneDirection));
    }

    @Test
    public void test_wait_if_no_direction() {
        Position clone = new Position(-1, -1);
        Direction cloneDirection = Direction.NONE;

        Game game = new GameBuilder().createGame();

        assertEquals(Move.WAIT, game.nextMove(clone, cloneDirection));
    }

    @Test
    public void test_order_elevators() {
        Position firstElevator = new Position(0, 14);
        List<Position> elevators = Arrays.asList(
                new Position(3, 18),
                new Position(4, 15),
                new Position(5, 12),
                new Position(1, 10),
                firstElevator,
                new Position(2, 6)
        );
        Game game = new GameBuilder().setElevators(elevators).createGame();
        assertEquals(firstElevator, game.elevators.get(0));
    }

    @Test
    public void test_move_string() {
        assertEquals("WAIT", Move.WAIT.toString());
    }

    @Test
    public void test_direction_from_string() {
        assertEquals(Direction.LEFT, Direction.valueOf("LEFT"));
    }
}
