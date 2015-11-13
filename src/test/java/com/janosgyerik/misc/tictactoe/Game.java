package com.janosgyerik.misc.tictactoe;

import java.util.Arrays;
import java.util.Set;

public class Game {

    private final Board board = new Board();
    private Player[] players;
    private Player[] players2;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Game game = (Game) o;

        if (board != null ? !board.equals(game.board) : game.board != null) {
            return false;
        }
        if (!Arrays.equals(players, game.players)) {
            return false;
        }
        if (!Arrays.equals(players2, game.players2)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = board != null ? board.hashCode() : 0;
        result = 31 * result + (players != null ? Arrays.hashCode(players) : 0);
        result = 31 * result + (players2 != null ? Arrays.hashCode(players2) : 0);
        return result;
    }

    public void setPlayers(Player first, Player second) {
        assert first != second;
        assert players == null;
        players = new Player[]{first, second};
    }

    public void play() {
        int round = 0;
        Move otherPlayerMove = null;
        while (true) {
            Player player = players[round++ % players.length];
            Move move = player.getNextMove(otherPlayerMove);
            if (!board.isValidMove(move)) {
                throw new IllegalStateException(String.format("Player %s tried an invalid move: %s", player, move));
            }
            board.applyMove(player, player.getNextMove(otherPlayerMove));
            board.print();

            Board.State state = board.getState();
            if (state.over) {
                System.out.printf("The winner is: %s%n", state.winner);
                break;
            }
            otherPlayerMove = move;
        }
    }

    public Set<Move> getAvailableMoves() {
        return board.getAvailableMoves();
    }
}
