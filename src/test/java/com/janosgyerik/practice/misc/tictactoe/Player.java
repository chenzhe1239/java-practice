package com.janosgyerik.practice.misc.tictactoe;

public interface Player {

    Symbol getSymbol();

    Move getNextMove(Move otherPlayerMove);
}
