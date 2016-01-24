package com.janosgyerik.practice.oj.codingame.medium.ParanoidAndroid;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators

        List<Position> elevators = new ArrayList<>(nbElevators);
        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            elevators.add(new Position(elevatorFloor, elevatorPos));
        }

        Game game = new GameBuilder()
                .setElevators(elevators)
                .setExitFloor(exitFloor)
                .setExitPos(exitPos)
                .createGame();
//        System.err.println(elevators);

        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT
//            System.err.printf("%s %s %s\n", cloneFloor, clonePos, direction);

            Position clone = new Position(cloneFloor, clonePos);
            Direction cloneDirection = Direction.valueOf(direction);
            System.out.println(game.nextMove(clone, cloneDirection));
        }
    }
}

enum Move {
    WAIT,
    BLOCK
}

enum Direction {
    LEFT,
    RIGHT,
    NONE
}

class Position {
    final int floor;
    final int pos;

    Position(int floor, int pos) {
        this.floor = floor;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", floor, pos);
    }
}

class Game {
    final int nbFloors;
    final int width;
    final int nbRounds;
    final int exitFloor;
    final int exitPos;
    final int nbTotalClones;
    final int nbAdditionalElevators;
    final List<Position> elevators;

    Game(int nbFloors, int width, int nbRounds, int exitFloor, int exitPos, int nbTotalClones,
         int nbAdditionalElevators, List<Position> elevators) {
        this.nbFloors = nbFloors;
        this.width = width;
        this.nbRounds = nbRounds;
        this.exitFloor = exitFloor;
        this.exitPos = exitPos;
        this.nbTotalClones = nbTotalClones;
        this.nbAdditionalElevators = nbAdditionalElevators;
        this.elevators = elevators;
    }

    public Move nextMove(Position clone, Direction cloneDirection) {
        if (isNoDirection(cloneDirection) || isAboveElevator(clone)) {
            return Move.WAIT;
        }

        int targetPos = determineTargetPos(clone);

        return isRightDirection(clone.pos, targetPos, cloneDirection) ? Move.WAIT : Move.BLOCK;
    }

    private boolean isRightDirection(int pos, int targetPos, Direction direction) {
        return pos == targetPos
                || (pos < targetPos ? direction == Direction.RIGHT : direction == Direction.LEFT);
    }

    private int determineTargetPos(Position clone) {
        if (clone.floor == elevators.size()) {
            return exitPos;
        }
        return elevators.get(clone.floor).pos;
    }

    private boolean isNoDirection(Direction cloneDirection) {
        return cloneDirection == Direction.NONE;
    }

    private boolean isAboveElevator(Position clone) {
        return clone.floor > 0 && clone.pos == elevators.get(clone.floor - 1).pos;
    }

    @Override
    public String toString() {
        return "Game{" +
                "nbFloors=" + nbFloors +
                ", width=" + width +
                ", nbRounds=" + nbRounds +
                ", exitFloor=" + exitFloor +
                ", exitPos=" + exitPos +
                ", nbTotalClones=" + nbTotalClones +
                ", nbAdditionalElevators=" + nbAdditionalElevators +
                ", elevators=" + elevators +
                '}';
    }
}

class GameBuilder {
    private int nbFloors;
    private int width;
    private int nbRounds;
    private int exitFloor;
    private int exitPos;
    private int nbTotalClones;
    private int nbAdditionalElevators;
    private List<Position> elevators = Collections.emptyList();

    public GameBuilder setNbFloors(int nbFloors) {
        this.nbFloors = nbFloors;
        return this;
    }

    public GameBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public GameBuilder setNbRounds(int nbRounds) {
        this.nbRounds = nbRounds;
        return this;
    }

    public GameBuilder setExitFloor(int exitFloor) {
        this.exitFloor = exitFloor;
        return this;
    }

    public GameBuilder setExitPos(int exitPos) {
        this.exitPos = exitPos;
        return this;
    }

    public GameBuilder setNbTotalClones(int nbTotalClones) {
        this.nbTotalClones = nbTotalClones;
        return this;
    }

    public GameBuilder setNbAdditionalElevators(int nbAdditionalElevators) {
        this.nbAdditionalElevators = nbAdditionalElevators;
        return this;
    }

    public GameBuilder setElevators(List<Position> elevators) {
        this.elevators = new ArrayList<>(elevators);
        Collections.sort(this.elevators, (o1, o2) -> Integer.compare(o1.floor, o2.floor));
        return this;
    }

    public Game createGame() {
        return new Game(nbFloors, width, nbRounds, exitFloor, exitPos, nbTotalClones, nbAdditionalElevators, elevators);
    }
}
