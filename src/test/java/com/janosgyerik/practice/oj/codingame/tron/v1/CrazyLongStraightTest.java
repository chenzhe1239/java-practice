package com.janosgyerik.practice.oj.codingame.tron.v1;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CrazyLongStraightTest extends BasePlayerTest {
    @Override
    protected BasePlayer createPlayer() {
        return new CrazyLongStraight();
    }

    @Ignore  // broken
    @Test
    public void test_choose_longer_straight_direction() {
        BasePlayer player = createPlayer();
        Assert.assertEquals(Move.LEFT, player.getFirstMove(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(new Position(MIDDLE.x + 3, MIDDLE.y))
        }));
        Assert.assertEquals(Move.RIGHT, player.getFirstMove(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(new Position(MIDDLE.x - 3, MIDDLE.y))
        }));
        Assert.assertEquals(Move.UP, player.getFirstMove(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(new Position(MIDDLE.x - 3, MIDDLE.y)),
                new PlayerInfo(new Position(MIDDLE.x + 3, MIDDLE.y)),
                new PlayerInfo(new Position(MIDDLE.x, MIDDLE.y + 3)),
        }));
        Assert.assertEquals(Move.DOWN, player.getFirstMove(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(new Position(MIDDLE.x - 3, MIDDLE.y)),
                new PlayerInfo(new Position(MIDDLE.x + 3, MIDDLE.y)),
                new PlayerInfo(new Position(MIDDLE.x, MIDDLE.y - 3)),
        }));
    }
}
