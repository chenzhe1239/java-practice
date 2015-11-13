package com.janosgyerik.practice.oj.codingame.tron.v1;

import java.util.Set;
import java.util.TreeMap;

class CrazyLongStraight extends BasePlayer {
    @Override
    public Move getFirstMove(int p, PlayerInfo[] playerInfos) {
        initPositionHistory(p, playerInfos);
        return setAndReturnLastMove(getLongestStraightMove());
    }

    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        if (canMove(getLastMove())) {
            return getLastMove();
        }
        return setAndReturnLastMove(getLongestStraightMove());
    }

    protected Move getLongestStraightMove() {
        TreeMap<Integer, Move> movesByStraightLength = new TreeMap<Integer, Move>();
        Set<Move> moves = getPossibleMoves();
        if (moves.size() > 1) {
            for (Move move : Move.MOVES) {
                movesByStraightLength.put(countStraightLength(move), move);
            }
        } else {
            movesByStraightLength.put(1, moves.iterator().next());
        }
        return movesByStraightLength.lastEntry().getValue();
    }

    private int countStraightLength(Move move) {
        int xm = 0, ym = 0;
        switch (move) {
            case LEFT:
                xm = -1;
                break;
            case RIGHT:
                xm = 1;
                break;
            case DOWN:
                ym = 1;
                break;
            case UP:
                ym = -1;
                break;
        }
        int i = 1;
        while (isValidAndAvailablePosition(new Position(getX() + xm * i, getY() + ym * i))) {
            ++i;
        }
        return i - 1;
    }
}

