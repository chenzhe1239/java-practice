package com.janosgyerik.practice.oj.codingame.tron.v2;

import org.junit.Assert;
import org.junit.Test;

public class RectangleGridTest {
    private RectangleGrid createSampleGrid() {
        return new RectangleGrid(30, 20);
    }

    @Test
    public void testContainsPosition() {
        RectangleGrid grid = createSampleGrid();
        Position position = grid.getAnyPosition();
        Assert.assertTrue(grid.containsPosition(position));
        Assert.assertFalse(grid.containsPosition(new Position(-1, -1)));
    }

    @Test
    public void testApplyMoves() {
        RectangleGrid grid = createSampleGrid();
        Position position = grid.getAnyPosition();
        Assert.assertTrue(grid.isAvailablePosition(position));
        grid.addPosition(1, position);
        Assert.assertFalse(grid.isAvailablePosition(position));
    }

    @Test
    public void testPossibleMovesFromAnyPosition() {
        RectangleGrid grid = createSampleGrid();
        Position position = grid.getAnyPosition();
        grid.addPosition(1, position);
        Assert.assertTrue(grid.getPossibleMovesFrom(position).size() > 0);
    }

    @Test
    public void testFourPossibleMovesFromMiddle() {
        RectangleGrid grid = createGridFromString(new String[]{
                "   ",
                " 9 ",
                "   "
        });
        Position position = grid.getFirstPosition(9);
        Assert.assertEquals(4, grid.getPossibleMovesFrom(position).size());
    }

    @Test
    public void testLimitedMovesWhenBlockedByOthers() {
        RectangleGrid grid = createGridFromString(new String[]{
                "   ",
                " 91",
                "   "
        });
        Position position = grid.getFirstPosition(9);
        Assert.assertEquals(3, grid.getPossibleMovesFrom(position).size());
    }

    @Test
    public void testLimitedMovesWhenCorneredByOthers() {
        RectangleGrid grid = createGridFromString(new String[]{
                "  1",
                " 91",
                " 11"
        });
        Position position = grid.getFirstPosition(9);
        Assert.assertEquals(2, grid.getPossibleMovesFrom(position).size());
    }

    @Test
    public void testLimitedMovesWhenCorneredByMultipleOthers() {
        RectangleGrid grid = createGridFromString(new String[]{
                "  1",
                "291",
                "211"
        });
        Position position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());
    }

    @Test
    public void testOnePossibleMove() {
        RectangleGrid grid;
        Position position;

        grid = createGridFromString(new String[]{
                "9 ",
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());

        grid = createGridFromString(new String[]{
                " 9",
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());

        grid = createGridFromString(new String[]{
                " ",
                "9",
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());

        grid = createGridFromString(new String[]{
                "9",
                " "
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());

        grid = createGridFromString(new String[]{
                "191",
                "2 2"
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());
    }

    @Test
    public void testRemovePlayer() {
        RectangleGrid grid = createGridFromString(new String[]{
                "  1",
                "291",
        });
        Assert.assertEquals(2, grid.getAvailablePositions().size());

        grid.removePlayer(2);
        Assert.assertEquals(3, grid.getAvailablePositions().size());

        grid.removePlayer(1);
        Assert.assertEquals(5, grid.getAvailablePositions().size());

        grid.removePlayer(9);
        Assert.assertEquals(6, grid.getAvailablePositions().size());

        grid.removePlayer(19);
    }

    @Test
    public void testCountReachablePositionsFrom() {
        RectangleGrid grid;
        Position position;

        grid = createGridFromString(new String[]{
                "9 "
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(0, grid.countReachablePositionsFrom(position));
        grid.removePlayer(9);
        Assert.assertEquals(2, grid.countReachablePositionsFrom(position));
    }

    @Test
    public void testGetDistanceStraight() {
        RectangleGrid grid = createGridFromString(new String[]{
                "9 87"
        });
        Position from = grid.getFirstPosition(9);
        Position to = grid.getFirstPosition(7);
        Assert.assertEquals(-1, grid.getDistance(from, to));
        grid.removePlayer(8);
        Assert.assertEquals(3, grid.getDistance(from, to));
    }

    @Test
    public void testGetDistanceNotStraight() {
        RectangleGrid grid = createGridFromString(new String[]{
                "  6 ",
                "9 87",
                "  6 ",
        });
        Position from = grid.getFirstPosition(9);
        Position to = grid.getFirstPosition(7);
        Assert.assertEquals(-1, grid.getDistance(from, to));
        grid.removePlayer(6);
        Assert.assertEquals(5, grid.getDistance(from, to));
        grid.removePlayer(8);
        Assert.assertEquals(3, grid.getDistance(from, to));
    }

    @Test
    public void testGetDistanceStraightManyWays() {
        RectangleGrid grid = createGridFromString(new String[]{
                "      ",
                " 9 87 ",
                "      ",
        });
        Position from = grid.getFirstPosition(9);
        Position to = grid.getFirstPosition(7);
        Assert.assertEquals(5, grid.getDistance(from, to));
    }

    @Test
    public void testGetDistanceLabyrinth() {
        RectangleGrid grid = createGridFromString(new String[]{
                "   16   ",
                "91   17 ",
                "  1   1 ",
                "  1     ",
        });
        Position from = grid.getFirstPosition(9);
        Position to = grid.getFirstPosition(7);
        Assert.assertEquals(14, grid.getDistance(from, to));
        grid.removePlayer(6);
        Assert.assertEquals(10, grid.getDistance(from, to));
    }

    @Test
    public void testGetDistanceLargeGrid() {
        String line = "                                        ";
        String[] lines = new String[50];
        for (int i = 0; i < lines.length; ++i) {
            lines[i] = line;
        }
        lines[0] = lines[0].replaceFirst(" ", "9");
        lines[lines.length-1] = lines[lines.length-1].replaceFirst(" ", "8");
        RectangleGrid grid = createGridFromString(lines);
        Position from = grid.getFirstPosition(9);
        Position to = grid.getFirstPosition(8);
        Assert.assertEquals(lines.length - 1, grid.getDistance(from, to));
    }

    @Test
    public void testAvoidImminentTrap() {
        RectangleGrid grid = createGridFromString(new String[]{
                " 8     ",
                "22 9111",
        });
        Position from = grid.getFirstPosition(9);
        Position target = grid.getFirstPosition(8);
        Assert.assertEquals(1, grid.getSaferMovesFrom(from, target).size());
        Assert.assertEquals(Move.UP, grid.getSaferMovesFrom(from, target).iterator().next());
        grid.removePlayer(2);
        Assert.assertEquals(2, grid.getSaferMovesFrom(from, target).size());
    }

    @Test
    public void testKillerMoves() {
        RectangleGrid grid = createGridFromString(new String[]{
                "228     ",
                "11119   ",
                "337     ",
        });
        Position from = grid.getFirstPosition(9);
        Position target1 = grid.getFirstPosition(8);
        Position target2 = grid.getFirstPosition(7);
        Assert.assertEquals(1, grid.getKillerMoves(from, target1).size());
        Assert.assertEquals(Move.UP, grid.getKillerMoves(from, target1).iterator().next());
        Assert.assertEquals(1, grid.getKillerMoves(from, target2).size());
        Assert.assertEquals(Move.DOWN, grid.getKillerMoves(from, target2).iterator().next());
    }

    private RectangleGrid createGridFromString(String[] strings) {
        int width = strings[0].length();
        int height = strings.length;
        RectangleGrid grid = new RectangleGrid(width, height);
        for (int i = 0; i < strings.length; ++i) {
            String line = strings[i];
            for (int j = 0; j < line.length(); ++j) {
                if (line.charAt(j) != ' ') {
                    grid.addPosition(line.charAt(j) - '0', new Position(j, i));
                }
            }
        }
        return grid;
    }
}
