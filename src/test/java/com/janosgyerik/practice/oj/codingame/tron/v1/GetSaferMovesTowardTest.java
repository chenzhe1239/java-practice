package com.janosgyerik.practice.oj.codingame.tron.v1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class GetSaferMovesTowardTest extends AbstractPlayerTest {
    BasePlayer createPlayer() {
        return new OtherPlayer();
    }

    @Test
    public void test_player_direction() {
        BasePlayer player = createPlayer();
        for (Move move : Move.MOVES) {
            player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(MIDDLE, Position.plusMove(MIDDLE, move))});
            Assert.assertEquals(move, player.getLastMove());
        }
    }

    @Test
    public void test_other_player_direction() {
        BasePlayer player = createPlayer();
        for (Move move : Move.MOVES) {
            player.initPositionHistory(0, new PlayerInfo[]{
                    new PlayerInfo(CORNER),
                    new PlayerInfo(MIDDLE, Position.plusMove(MIDDLE, move))
            });
            BasePlayer otherPlayer = player.getAnotherPlayer();
            Assert.assertEquals(move, otherPlayer.getLastMove());
        }
    }

    @Test
    public void test_follow_to_right() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(
                        Position.plusMove(MIDDLE, Move.DOWN, Move.LEFT),
                        Position.plusMove(MIDDLE, Move.DOWN))
        });
        OtherPlayer otherPlayer = player.getAnotherPlayer();
        Assert.assertEquals(Move.RIGHT, otherPlayer.getLastMove());

        Assert.assertEquals(3, player.getSaferMoves().size());
        Set<Move> saferMovesToward = player.getSaferMovesToward(otherPlayer);
        Assert.assertEquals(1, saferMovesToward.size());
        Assert.assertEquals(Move.RIGHT, saferMovesToward.iterator().next());
    }

    @Test
    public void test_follow_to_left() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(
                        Position.plusMove(MIDDLE, Move.DOWN, Move.RIGHT),
                        Position.plusMove(MIDDLE, Move.DOWN))
        });
        OtherPlayer otherPlayer = player.getAnotherPlayer();
        Assert.assertEquals(Move.LEFT, otherPlayer.getLastMove());

        Assert.assertEquals(3, player.getSaferMoves().size());
        Set<Move> saferMovesToward = player.getSaferMovesToward(otherPlayer);
        Assert.assertEquals(1, saferMovesToward.size());
        Assert.assertEquals(Move.LEFT, saferMovesToward.iterator().next());
    }

    @Test
    public void test_follow_to_up() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(
                        Position.plusMove(MIDDLE, Move.RIGHT, Move.DOWN),
                        Position.plusMove(MIDDLE, Move.RIGHT))
        });
        OtherPlayer otherPlayer = player.getAnotherPlayer();
        Assert.assertEquals(Move.UP, otherPlayer.getLastMove());

        Assert.assertEquals(3, player.getSaferMoves().size());
        Set<Move> saferMovesToward = player.getSaferMovesToward(otherPlayer);
        Assert.assertEquals(1, saferMovesToward.size());
        Assert.assertEquals(Move.UP, saferMovesToward.iterator().next());
    }

    @Test
    public void test_follow_to_down() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(
                        Position.plusMove(MIDDLE, Move.RIGHT, Move.UP),
                        Position.plusMove(MIDDLE, Move.RIGHT))
        });
        OtherPlayer otherPlayer = player.getAnotherPlayer();
        Assert.assertEquals(Move.DOWN, otherPlayer.getLastMove());

        Assert.assertEquals(3, player.getSaferMoves().size());
        Set<Move> saferMovesToward = player.getSaferMovesToward(otherPlayer);
        Assert.assertEquals(1, saferMovesToward.size());
        Assert.assertEquals(Move.DOWN, saferMovesToward.iterator().next());
    }
}
