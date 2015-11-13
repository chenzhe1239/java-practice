package com.janosgyerik.misc.tictactoe;

import java.util.*;

public class Players {

    private Players() {
        // utility class, forbidden constructor
    }

    private static abstract class AbstractPlayer implements Player {

        private final Symbol symbol;

        private AbstractPlayer(Symbol symbol) {
            this.symbol = symbol;
        }

        @Override
        public Symbol getSymbol() {
            return symbol;
        }

        @Override
        public String toString() {
            return symbol.toString();
        }
    }

    public static Player random(Symbol symbol, Game game) {
        return new AbstractPlayer(symbol) {
            @Override
            public Move getNextMove(Move otherPlayerMove) {
                List<Move> moves = new ArrayList<>(game.getAvailableMoves());
                Collections.shuffle(moves, new Random(2));
                return moves.get(0);
            }
        };
    }

    private static class Board {
        private final Symbol[][] matrix = new Symbol[3][3];

        public Board() {
        }

        private Board(Board other) {
            for (int i = 0; i < matrix.length; ++i) {
                matrix[i] = other.matrix[i].clone();
            }
        }

        public Board apply(Symbol symbol, Move move) {
            if (move != null) {
                matrix[move.x][move.y] = symbol;
            }
            return this;
        }

        public Board copy() {
            return new Board(this);
        }

        public boolean isOver() {
            for (Symbol symbol : Symbol.values()) {
                for (int i = 0; i < matrix.length; ++i) {
                    if (symbolWinsRow(symbol, i)) {
                        return true;
                    }
                    if (symbolWinsCol(symbol, i)) {
                        return true;
                    }
                }
                if (matrix[0][0] == symbol && matrix[1][1] == symbol && matrix[2][2] == symbol) {
                    return true;
                }
                if (matrix[0][2] == symbol && matrix[1][1] == symbol && matrix[2][0] == symbol) {
                    return true;
                }
            }
            return false;
        }

        private boolean symbolWinsRow(Symbol symbol, int i) {
            for (int j = 0; j < matrix.length; ++j) {
                if (matrix[i][j] != symbol) {
                    return false;
                }
            }
            return true;
        }

        private boolean symbolWinsCol(Symbol symbol, int i) {
            for (int j = 0; j < matrix.length; ++j) {
                if (matrix[j][i] != symbol) {
                    return false;
                }
            }
            return true;
        }
    }

    public static Player killer(Symbol symbol, Game game) {
        Board board = new Board();
        Symbol otherSymbol = symbol == Symbol.O ? Symbol.X : Symbol.O;

        return new AbstractPlayer(symbol) {
            @Override
            public Move getNextMove(Move otherPlayerMove) {
                board.apply(otherSymbol, otherPlayerMove);

                Set<Move> availableMoves = game.getAvailableMoves();
                for (Move move : availableMoves) {
                    if (board.copy().apply(symbol, move).isOver()) {
                        return applyMove(move);
                    }
                }

                if (availableMoves.contains(Move.CENTER)) {
                    return applyMove(Move.CENTER);
                }

                for (Move move : availableMoves) {
                    if (board.copy().apply(otherSymbol, move).isOver()) {
                        return applyMove(move);
                    }
                }

                for (Move move : availableMoves) {
                    if (move.isCorner()) {
                        return applyMove(move);
                    }
                }

                return availableMoves.iterator().next();
            }

            private Move applyMove(Move move) {
                board.apply(symbol, move);
                return move;
            }
        };
    }
}
