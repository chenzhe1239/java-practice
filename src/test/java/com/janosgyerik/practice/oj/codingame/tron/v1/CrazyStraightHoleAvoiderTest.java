package com.janosgyerik.practice.oj.codingame.tron.v1;

import org.junit.Assert;
import org.junit.Test;

public class CrazyStraightHoleAvoiderTest extends BasePlayerTest {
    @Override
    protected BasePlayer createPlayer() {
        return new CrazyStraightHoleAvoider();
    }

    private BasePlayer forceFirstMove(Move firstMove) {
        BasePlayer player = createPlayer();
        Move move;
        do {
            move = player.getFirstMove(0, new PlayerInfo[]{new PlayerInfo(MIDDLE)});
        } while (move != firstMove);
        return player;
    }

    @Test
    public void test_keep_left_if_no_obstacles() {
        Move move = Move.LEFT;
        BasePlayer player = forceFirstMove(move);
        Assert.assertEquals(move, player.getNextMove(0, new PlayerInfo[]{new PlayerInfo(MIDDLE.x, MIDDLE.y, MIDDLE.x - 1, MIDDLE.y)}));
    }

    @Test
    public void test_keep_right_if_no_obstacles() {
        Move move = Move.RIGHT;
        BasePlayer player = forceFirstMove(move);
        Assert.assertEquals(move, player.getNextMove(0, new PlayerInfo[]{new PlayerInfo(MIDDLE.x, MIDDLE.y, MIDDLE.x + 1, MIDDLE.y)}));
    }

    @Test
    public void test_keep_down_if_no_obstacles() {
        Move move = Move.DOWN;
        BasePlayer player = forceFirstMove(move);
        Assert.assertEquals(move, player.getNextMove(0, new PlayerInfo[]{new PlayerInfo(MIDDLE.x, MIDDLE.y, MIDDLE.x, MIDDLE.y + 1)}));
    }

    @Test
    public void test_keep_up_if_no_obstacles() {
        Move move = Move.UP;
        BasePlayer player = forceFirstMove(move);
        Assert.assertEquals(move, player.getNextMove(0, new PlayerInfo[]{new PlayerInfo(MIDDLE.x, MIDDLE.y, MIDDLE.x, MIDDLE.y - 1)}));
    }
}
