package com.janosgyerik.misc.tictactoe;

public class TicTacToe {
    public static void main(String[] args) {
        Game game = new Game();
        Player first = Players.killer(Symbol.X, game);
        Player second = Players.killer(Symbol.O, game);
        game.setPlayers(first, second);
        game.play();
    }
}
