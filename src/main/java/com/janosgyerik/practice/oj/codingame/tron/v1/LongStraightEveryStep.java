package com.janosgyerik.practice.oj.codingame.tron.v1;

class LongStraightEveryStep extends CrazyLongStraight {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        return setAndReturnLastMove(getLongestStraightMove());
    }
}
