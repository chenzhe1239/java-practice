package com.janosgyerik.misc.tictactoe;

public enum Move {
    TOPLEFT(0, 0),
    TOP(0, 1),
    TOPRIGHT(0, 2),
    LEFT(1, 0),
    CENTER(1, 1),
    RIGHT(1, 2),
    BOTTOMLEFT(2, 0),
    BOTTOM(2, 1),
    BOTTOMRIGHT(2, 2)
    ;

    final int x;
    final int y;

    Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public boolean isCorner() {
        return x != 1 && (x == y || x == 2 - y);
    }
}
