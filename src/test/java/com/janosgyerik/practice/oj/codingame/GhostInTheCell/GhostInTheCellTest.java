package com.janosgyerik.practice.oj.codingame.GhostInTheCell;

import com.janosgyerik.practice.oj.codingame.GhostInTheCell.Player.Commands;
import com.janosgyerik.practice.oj.codingame.GhostInTheCell.Player.Game;
import org.junit.Test;

import java.util.Scanner;

import static com.janosgyerik.practice.oj.codingame.GhostInTheCell.Player.attackDefeatableNeutrals;

public class GhostInTheCellTest {
    @Test
    public void test_attackEasiestNeutrals() {
        Commands commands = new Commands();
        Scanner scanner = new Scanner("3 2\n" +
                "0 1 1\n" +
                "0 2 3\n" +
                "2\n" +
                "0 FACTORY 1 5 3 0 0\n" +
                "1 FACTORY 0 4 0 0 0\n");
        Game game = new Game(scanner);
        game.parseRound();
        attackDefeatableNeutrals(commands, game.factories[0]);
        commands.print();
    }
}
