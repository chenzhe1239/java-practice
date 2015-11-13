package com.janosgyerik.practice.oj.codingame.skynet;

import java.util.Scanner;

class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // the length of the road before the gap.
        in.nextLine();
        int G = in.nextInt(); // the length of the gap.
        in.nextLine();
        int L = in.nextInt(); // the length of the landing platform.
        in.nextLine();

        // game loop
        while (true) {
            int S = in.nextInt(); // the motorbike's speed.
            in.nextLine();
            int X = in.nextInt(); // the position on the road of the motorbike.
            in.nextLine();

            String action = Bike.computeAction(R, G, L, S, X);
            System.err.println(String.format(
                    "%d %d %d %d %d %s",
                    R, G, L, S, X, action
            ));

            System.out.println(action); // A single line containing one of 4 keywords: SPEED, SLOW, JUMP, WAIT.
        }
    }
}

enum Move {
    SPEED("SPEED"),
    SLOW("SLOW"),
    JUMP("JUMP"),
    WAIT("WAIT");

    final String label;

    Move(String label) {
        this.label = label;
    }
}

class GameState {
    final int road;
    final int gap;
    final int landing;
    final int speed;
    final int pos;

    GameState(int road, int gap, int landing, int speed, int pos) {
        this.road = road;
        this.gap = gap;
        this.landing = landing;
        this.speed = speed;
        this.pos = pos;
    }

    GameState transition(Move move) {
        final int newSpeed;
        final int newPos;
        switch (move) {
            case SPEED:
                newSpeed = speed + 1;
                newPos = pos + newSpeed;
                break;
            case SLOW:
                newSpeed = speed - 1;
                newPos = pos + newSpeed;
                break;
            default:
                newSpeed = speed;
                newPos = pos + speed;
        }
        if (newPos >= road && pos < road && move != Move.JUMP) {
            return new LostState();
        }
        GameState newState = new GameState(road, gap, landing, newSpeed, newPos);
        if (newSpeed <= 0 && !newState.isWin()) {
            return new LostState();
        }
        return newState;
    }

    boolean isWin() {
        return speed == 0 && pos >= road + gap && pos < road + gap + landing;
    }

    boolean isLose() {
        return pos >= road && pos < road + gap || pos >= road + gap + landing;
    }

    public boolean canReachVictory() {
        if (isWin()) {
            return true;
        } else if (isLose()) {
            return false;
        }
        for (Move move : Move.values()) {
            if (transition(move).canReachVictory()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%d %d %d %d %d", road, gap, landing, speed, pos);
    }
}

class LostState extends GameState {
    LostState() {
        super(-1, -1, -1, -1, -1);
    }

    @Override
    boolean isLose() {
        return true;
    }

    @Override
    boolean isWin() {
        return false;
    }

    @Override
    public boolean canReachVictory() {
        return false;
    }
}

class Bike {
    static String computeAction(int road, int gap, int landing, int speed, int pos) {
        GameState start = new GameState(road, gap, landing, speed, pos);
        for (Move move : Move.values()) {
            if (start.transition(move).canReachVictory()) {
                return move.label;
            }
        }
        return "Huh?";
    }
}