package com.janosgyerik.practice.oj.codingame.medium.heat;

import java.util.Scanner;

class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        in.nextLine();
        int N = in.nextInt(); // maximum number of turns before game over.
        in.nextLine();
        int X0 = in.nextInt();
        int Y0 = in.nextInt();
        in.nextLine();

        BatmanState state = new BatmanState(X0, Y0);
        PossibilityWindow window = new PossibilityWindow(0, 0, W - 1, H - 1);

        // game loop
        while (true) {
            String BOMB_DIR = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            in.nextLine();

            Direction direction = Direction.valueOf(BOMB_DIR);
            window.narrowByDirection(state.x, state.y, direction);
            int nextX = (window.minX + window.maxX) / 2;
            int nextY = (window.minY + window.maxY) / 2;
//            state = state.transition(window.);
            state = state.transition(nextX, nextY);

            System.out.println(state.x + " " + state.y); // the location of the next window Batman should jump to.
        }
    }
}

enum Direction {
    U(0, -1),
    UR(1, -1),
    R(1, 0),
    DR(1, 1),
    D(0, 1),
    DL(-1, 1),
    L(-1, 0),
    UL(-1, -1);

    final int dx;
    final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}

class BatmanState {
    final int x;
    final int y;

    BatmanState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    BatmanState transition(Direction direction) {
        return new BatmanState(x + direction.dx, y + direction.dy);
    }

    public BatmanState transition(int nextX, int nextY) {
        return new BatmanState(nextX, nextY);
    }
}

class PossibilityWindow {
    int minX;
    int minY;
    int maxX;
    int maxY;

    PossibilityWindow(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void narrowToX(int x) {
        minX = maxX = x;
    }

    public void narrowToY(int y) {
        minY = maxY = y;
    }

    public void narrowByDirection(int x, int y, Direction direction) {
        switch (direction) {
            case U:
                narrowToX(x);
                maxY = y + direction.dy;
                break;
            case D:
                narrowToX(x);
                minY = y + direction.dy;
                break;
            case L:
                narrowToY(y);
                maxX = x + direction.dx;
                break;
            case R:
                narrowToY(y);
                minX = x + direction.dx;
                break;
            case DL:
                maxX = x + direction.dx;
                minY = y + direction.dy;
                break;
            case DR:
                minX = x + direction.dx;
                minY = y + direction.dy;
                break;
            case UL:
                maxX = x + direction.dx;
                maxY = y + direction.dy;
                break;
            case UR:
                minX = x + direction.dx;
                maxY = y + direction.dy;
                break;
        }
    }
}