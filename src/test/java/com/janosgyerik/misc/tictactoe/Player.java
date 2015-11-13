package com.janosgyerik.misc.tictactoe;

public interface Player {

    Symbol getSymbol();

    Move getNextMove(Move otherPlayerMove);
}
