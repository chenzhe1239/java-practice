package com.janosgyerik.practice.oj.codingame.tron.v1;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

public class BasePlayerTest extends AbstractPlayerTest {

    protected BasePlayer createPlayer() {
        return new Crazy();
    }

    @Test
    public void can_play_alone_at_least_one_round() {
        getFirstMove(createPlayer(), MIDDLE);
        getFirstMove(createPlayer(), HORIZONTAL_EDGE_CENTER);
        getFirstMove(createPlayer(), CORNER);
        getFirstMove(createPlayer(), NEAR_CORNER);
    }

    private Move getFirstMove(BasePlayer player, Position position) {
        Move move = player.getFirstMove(0, new PlayerInfo[]{new PlayerInfo(position)});
        Assert.assertNotNull(move);
        return move;
    }

    private Move getNextMove(BasePlayer player, Move lastMove) {
        int newx = player.getX();
        int newy = player.getY();
        switch (lastMove) {
            case LEFT:
                --newx;
                break;
            case RIGHT:
                ++newx;
                break;
            case UP:
                --newy;
                break;
            case DOWN:
                ++newy;
                break;
        }
        return player.getNextMove(0, new PlayerInfo[]{new PlayerInfo(new Position(newx, newy))});
    }

    private void play(int rounds, Position position) {
        BasePlayer player = createPlayer();
        Move move = getFirstMove(player, position);
        int count = 0;
        do {
            move = getNextMove(player, move);
            ++count;
        } while (move != null);
        Assert.assertTrue(count >= rounds);
    }

    private void play(int repeat, int rounds, Position position) {
        for (int i = 0; i < repeat; ++i) {
            play(rounds, position);
        }
    }

    @Ignore
    @Test
    public void test_can_play_at_least_8_rounds_from_MIDDLE() {
        play(10, 8, MIDDLE);
    }

    @Ignore
    @Test
    public void test_can_play_at_least_5_rounds_from_side() {
        play(50, 5, HORIZONTAL_EDGE_CENTER);
    }

    @Ignore
    @Test
    public void test_can_play_at_least_3_rounds_from_near_corner() {
        play(100, 3, NEAR_CORNER);
    }

    @Ignore
    @Test(expected = AssertionError.class)
    public void test_cannot_always_play_100_rounds_from_near_corner() {
        play(10, 100, NEAR_CORNER);
    }

    @Test
    public void test_can_move_to_4_directions_from_MIDDLE() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(MIDDLE)});
        Assert.assertEquals(4, player.getPossibleMoves().size());
    }

    @Test
    public void test_can_move_to_3_directions_from_edge() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(HORIZONTAL_EDGE_CENTER)});
        Assert.assertEquals(3, player.getPossibleMoves().size());
    }

    @Test
    public void test_can_move_to_2_directions_from_corner() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(CORNER)});
        Assert.assertEquals(2, player.getPossibleMoves().size());
    }

    @Test
    public void test_cannot_move_back_where_came_from() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(MIDDLE)});
        Assert.assertEquals(4, player.getPossibleMoves().size());

        player.updatePositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE.x, MIDDLE.y, MIDDLE.x + 1, MIDDLE.y)
        });
        Assert.assertEquals(3, player.getPossibleMoves().size());

        player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(MIDDLE)});
        player.updatePositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE.x, MIDDLE.y, MIDDLE.x - 1, MIDDLE.y)
        });
        Assert.assertEquals(3, player.getPossibleMoves().size());

        player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(MIDDLE)});
        player.updatePositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE.x, MIDDLE.y, MIDDLE.x, MIDDLE.y + 1)
        });
        Assert.assertEquals(3, player.getPossibleMoves().size());

        player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(MIDDLE)});
        player.updatePositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE.x, MIDDLE.y, MIDDLE.x, MIDDLE.y - 1)
        });
        Assert.assertEquals(3, player.getPossibleMoves().size());

        player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(MIDDLE)});
        player.updatePositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE.x, MIDDLE.y, MIDDLE.x + 1, MIDDLE.y + 1)
        });
        Assert.assertEquals(4, player.getPossibleMoves().size());
    }

    @Test
    public void test_blocked_by_another_player() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(MIDDLE)});
        Assert.assertEquals(4, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(new Position(MIDDLE.x + 1, MIDDLE.y))
        });
        Assert.assertEquals(3, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(new Position(MIDDLE.x + 1, MIDDLE.y)),
                new PlayerInfo(new Position(MIDDLE.x - 1, MIDDLE.y))
        });
        Assert.assertEquals(2, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(new Position(MIDDLE.x + 1, MIDDLE.y)),
                new PlayerInfo(new Position(MIDDLE.x - 1, MIDDLE.y)),
                new PlayerInfo(new Position(MIDDLE.x, MIDDLE.y + 1))
        });
        Assert.assertEquals(1, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(new Position(MIDDLE.x + 1, MIDDLE.y)),
                new PlayerInfo(new Position(MIDDLE.x - 1, MIDDLE.y)),
                new PlayerInfo(new Position(MIDDLE.x, MIDDLE.y + 1)),
                new PlayerInfo(new Position(MIDDLE.x, MIDDLE.y - 1))
        });
        Set<Move> moves = player.getPossibleMoves();
        Assert.assertEquals(1, moves.size());
        Assert.assertEquals(Move.IMPOSSIBLE, moves.iterator().next());
    }

    @Ignore  // broken
    @Test
    public void test_ignore_lost_players() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(MIDDLE)});
        Assert.assertEquals(4, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(0, 0, MIDDLE.x + 1, MIDDLE.y)
        });
        Assert.assertEquals(3, player.getPossibleMoves().size());

        player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(MIDDLE),
                new PlayerInfo(-1, -1, MIDDLE.x + 1, MIDDLE.y)
        });
        Assert.assertEquals(4, player.getPossibleMoves().size());
    }
}
