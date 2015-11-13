package com.janosgyerik.misc.tictactoe;

import java.util.*;

public class Board {

    private final Map<Move, Player> usedMoves = new HashMap<>();
    private final Set<Move> availableMoves = new HashSet<>(Arrays.asList(Move.values()));

    private State state = new State(false, null);

    public void print() {
        System.out.printf("%s|%s|%s%n", getSymbol(Move.TOPLEFT), getSymbol(Move.TOP), getSymbol(Move.TOPRIGHT));
        System.out.println("-+-+-");
        System.out.printf("%s|%s|%s%n", getSymbol(Move.LEFT), getSymbol(Move.CENTER), getSymbol(Move.RIGHT));
        System.out.println("-+-+-");
        System.out.printf("%s|%s|%s%n", getSymbol(Move.BOTTOMLEFT), getSymbol(Move.BOTTOM), getSymbol(Move.BOTTOMRIGHT));
        System.out.println();
    }

    private String getSymbol(Move move) {
        Player player = usedMoves.get(move);
        return player == null ? " " : player.getSymbol().toString();
    }

    private static class PlayerStats {
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diag1 = 0;
        int diag2 = 0;
    }

    private Map<Player, PlayerStats> stats = new HashMap<>();

    public void applyMove(Player player, Move move) {
        usedMoves.put(move, player);
        availableMoves.remove(move);
        updatePlayerStats(player, move);
        updateState();
    }

    private void updateState() {
        if (availableMoves.isEmpty()) {
            state = new State(true, null);
        }
    }

    private void updatePlayerStats(Player player, Move move) {
        PlayerStats playerStats = stats.get(player);
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }

        if (playerStats.rows[move.x] == 2) {
            setWinner(player);
        }
        playerStats.rows[move.x]++;

        if (playerStats.cols[move.y] == 2) {
            setWinner(player);
        }
        playerStats.cols[move.y]++;

        if (move.x == move.y) {
            if (playerStats.diag1 == 2) {
                setWinner(player);
            }
            playerStats.diag1++;
        }

        if (move.x == 2 - move.y) {
            if (playerStats.diag2 == 2) {
                setWinner(player);
            }
            playerStats.diag2++;
        }

        stats.put(player, playerStats);
    }

    private void setWinner(Player player) {
        state = new State(true, player);
    }

    public Set<Move> getAvailableMoves() {
        return availableMoves;
    }

    public boolean isValidMove(Move move) {
        return availableMoves.contains(move);
    }

    public State getState() {
        return state;
    }

    public static class State {
        public final boolean over;
        public final Player winner;

        private State(boolean over, Player winner) {
            this.over = over;
            this.winner = winner;
        }
    }
}
