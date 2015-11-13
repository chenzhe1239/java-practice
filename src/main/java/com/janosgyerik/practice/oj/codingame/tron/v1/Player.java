package com.janosgyerik.practice.oj.codingame.tron.v1;

import java.util.*;

// TODO stop following other player if unreachable

// TODO consider potential hole if target player keeps going in the same direction

// TODO consider potential hole if all other players keep going in the same direction

// TODO if next move will cut off the other player, do it, example: y: (10,10)(8,6)

// TODO if dominating a space, don't diminish it, fill it well

class Player {

    private static IPlayer createPlayer() {
        return new CrazyIntercepterHoleAvoider();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        IPlayer me = null;

        while (true) {
            int n = in.nextInt();
            int p = in.nextInt();

            PlayerInfo[] playerInfos = new PlayerInfo[n];
            for (int i = 0; i < n; ++i) {
                int x0 = in.nextInt();
                int y0 = in.nextInt();
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                playerInfos[i] = new PlayerInfo(x0, y0, x1, y1);
            }

            if (me == null) {
                me = createPlayer();
                System.out.println(me.getFirstMove(p, playerInfos));
            } else {
                System.out.println(me.getNextMove(p, playerInfos));
            }
        }
    }
}

enum Move {
    LEFT, RIGHT, UP, DOWN,
    IMPOSSIBLE("DAAAAAAAAAMN!...");

    public static final Move[] MOVES = {LEFT, RIGHT, UP, DOWN};

    private final String message;

    Move() {
        message = null;
    }

    Move(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message != null ? message : name();
    }
}

class PlayerInfo {
    final int x0;
    final int y0;
    final int x1;
    final int y1;

    public PlayerInfo(int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public PlayerInfo(Position position) {
        x0 = x1 = position.x;
        y0 = y1 = position.y;
    }

    public PlayerInfo(Position position0, Position position) {
        x0 = position0.x;
        y0 = position0.y;
        x1 = position.x;
        y1 = position.y;
    }
}

interface IPlayer {
    Move getFirstMove(int p, PlayerInfo[] playerInfos);
    Move getNextMove(int p, PlayerInfo[] playerInfos);
}

class Position {
    final int x;
    final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Position plusMove(Position position, Move... moves) {
        Position newPosition = position;
        for (Move move : moves) {
            newPosition = plusMove(newPosition.x, newPosition.y, move);
        }
        return newPosition;
    }

    static Position plusMove(int x, int y, Move move) {
        switch (move) {
            case LEFT:
                return new Position(x - 1, y);
            case RIGHT:
                return new Position(x + 1, y);
            case UP:
                return new Position(x, y - 1);
            case DOWN:
                return new Position(x, y + 1);
        }
        throw new IllegalArgumentException();
    }

    public static Move getMove(Position pos0, Position pos) {
        if (pos0.x < pos.x) {
            return Move.RIGHT;
        }
        if (pos0.x > pos.x) {
            return Move.LEFT;
        }
        if (pos0.y < pos.y) {
            return Move.DOWN;
        }
        if (pos0.y > pos.y) {
            return Move.UP;
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position other = (Position) obj;
            return x == other.x && y == other.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}

abstract class BasePlayer implements IPlayer {
    static final int MIN_X = 0;
    static final int MAX_X = 29;
    static final int MIN_Y = 0;
    static final int MAX_Y = 19;
    static final int MID_X = (MAX_X - MIN_X) / 2;
    static final int MID_Y = (MAX_Y - MIN_Y) / 2;

    private static final char USED = '1';
    static final char FREE = '0';
    static final char REACHABLE = ' ';
    static final char TARGET = 'x';

    final Map<Integer, OtherPlayer> otherPlayers = new HashMap<Integer, OtherPlayer>();
    final Set<BasePlayer> players = new HashSet<BasePlayer>();
    private final Set<Position> visitedPositions = new HashSet<Position>();

    private int x;
    private int y;
    private Move lastMove;

    private void initOtherPlayers(int p, PlayerInfo[] playerInfos) {
        for (int i = 0; i < playerInfos.length; ++i) {
            if (i != p) {
                OtherPlayer other = new OtherPlayer();
                other.initPositionHistory(0, new PlayerInfo[]{playerInfos[i]});
                otherPlayers.put(i, other);
                players.add(other);
            }
        }
    }

    void initPositionHistory(int p, PlayerInfo[] playerInfos) {
        PlayerInfo playerInfo = playerInfos[p];
        x = playerInfo.x1;
        y = playerInfo.y1;

        Position pos0 = new Position(playerInfo.x0, playerInfo.y0);
        visitedPositions.add(pos0);

        Position pos = new Position(playerInfo.x1, playerInfo.y1);
        visitedPositions.add(pos);

        lastMove = Position.getMove(pos0, pos);

        players.add(this);
        initOtherPlayers(p, playerInfos);
    }

    private void updateOtherPlayers(int p, PlayerInfo[] playerInfos) {
        for (int i = 0; i < playerInfos.length; ++i) {
            if (i != p) {
                PlayerInfo playerInfo = playerInfos[i];
                OtherPlayer player = otherPlayers.get(i);
                player.updateLastMove(Position.getMove(player.getPosition(), new Position(playerInfo.x1, playerInfo.y1)));
                player.updatePositionHistory(0, new PlayerInfo[]{playerInfo});
            }
        }
    }

    void updatePositionHistory(int p, PlayerInfo[] playerInfos) {
        PlayerInfo playerInfo = playerInfos[p];
        x = playerInfo.x1;
        y = playerInfo.y1;

        Position pos = new Position(x, y);
        visitedPositions.add(pos);

        updateOtherPlayers(p, playerInfos);
    }

    public Set<Move> getPossibleMoves() {
        Set<Move> possibleMoves = new HashSet<Move>();
        for (Move move : Move.MOVES) {
            if (canMove(move)) {
                possibleMoves.add(move);
            }
        }
        if (possibleMoves.isEmpty()) {
            return Collections.singleton(Move.IMPOSSIBLE);
        }
        return possibleMoves;
    }

    public int countPossibleMoves(Move afterMove) {
        int count = 0;
        Position position = getPositionAfterMove(afterMove);
        for (Move move : Move.MOVES) {
            if (isValidAndAvailablePosition(Position.plusMove(position, move))) {
                ++count;
            }
        }
        return count;
    }

    public boolean canMove(Move targetMove) {
        return isValidAndAvailablePosition(getPositionAfterMove(targetMove));
    }

    public Position getPositionAfterMove(Move move) {
        return Position.plusMove(x, y, move);
    }

    public Move[] getMovesArray(Set<Move> moves) {
        return moves.toArray(new Move[moves.size()]);
    }

    public Move getRandomMove(Set<Move> moves) {
        Move[] movesArray = getMovesArray(moves);
        return movesArray[(int) (Math.random() * movesArray.length)];
    }

    public Move getRandomMove() {
        return getRandomMove(getPossibleMoves());
    }

    protected boolean isValidAndAvailablePosition(Position position) {
        return isValidPosition(position) && isAvailablePosition(position);
    }

    protected boolean isValidPosition(Position position) {
        return isValidPosition(position.x, position.y);
    }

    protected boolean isValidPosition(int x, int y) {
        return x >= MIN_X && x <= MAX_X && y >= MIN_Y && y <= MAX_Y;
    }

    protected boolean isAvailablePosition(Position position) {
        for (BasePlayer player : players) {
            if (player.ownsPosition(position)) {
                return false;
            }
        }
        return true;
    }

    protected boolean ownsPosition(Position position) {
        return isAlive() && visitedPositions.contains(position);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Move getLastMove() {
        return lastMove;
    }

    protected Move setAndReturnLastMove(Move move) {
        return lastMove = move;
    }

    protected void updateLastMove(Move move) {
        lastMove = move;
    }

    public Position getPosition() {
        return new Position(x, y);
    }

    public boolean isAlive() {
        return x != -1;
    }

    private int countHoleSizeFromMove(Move move) {
        return countHoleSize(initGrid(), getPositionAfterMove(move));
    }

    private int countHoleSize(char[][] grid, Position position) {
        return countHoleSize(grid, position.x, position.y);
    }

    int countHoleSize(char[][] grid, int x, int y) {
        if (!isValidPosition(x, y)) {
            return 0;
        }
        if (grid[x][y] == FREE) {
            grid[x][y] = REACHABLE;
            return 1
                    + countHoleSize(grid, x + 1, y)
                    + countHoleSize(grid, x - 1, y)
                    + countHoleSize(grid, x, y + 1)
                    + countHoleSize(grid, x, y - 1);
        }
        return 0;
    }

    protected char[][] initGrid() {
        char[][] grid = new char[MAX_X + 1][MAX_Y + 1];
        for (int x = 0; x <= MAX_X; ++x) {
            for (int y = 0; y <= MAX_Y; ++y) {
                grid[x][y] = FREE;
                Position position = new Position(x, y);
                for (BasePlayer player : players) {
                    if (player.ownsPosition(position)) {
                        grid[x][y] = USED;
                        break;
                    }
                }
            }
        }
        return grid;
    }

    Set<Move> getSaferMoves() {
        Set<Move> possibleMoves = getPossibleMoves();
        if (possibleMoves.size() < 2) {
            return possibleMoves;
        }
        TreeMap<Integer, Set<Move>> holeSizeToMoves = new TreeMap<Integer, Set<Move>>();
        for (Move move : possibleMoves) {
            int holeSize = countHoleSizeFromMove(move);
            Set<Move> moves = holeSizeToMoves.get(holeSize);
            if (moves == null) {
                moves = new HashSet<Move>();
                holeSizeToMoves.put(holeSize, moves);
            }
            moves.add(move);
        }
        return holeSizeToMoves.lastEntry().getValue();
    }

    Set<Move> getEvenSaferMoves() {
        Set<Move> saferMoves = getSaferMoves();
        if (saferMoves.size() < 2) {
            return saferMoves;
        }
        Set<Move> evenSaferMoves = new HashSet<Move>();
        for (Move move : saferMoves) {
            if (countPossibleMoves(move) > 1) {
                evenSaferMoves.add(move);
            }
        }
        return !evenSaferMoves.isEmpty() ? evenSaferMoves : saferMoves;
    }

    Set<Move> getSaferMovesToward(OtherPlayer otherPlayer) {
        Set<Move> saferMoves = getEvenSaferMoves();
        if (saferMoves.size() < 2) {
            return saferMoves;
        }
        TreeMap<Integer, Set<Move>> distanceToMove = new TreeMap<Integer, Set<Move>>();
        for (Move move : saferMoves) {
            int distance = distanceFromPlayerAfterMove(otherPlayer, move);
            Set<Move> moves = distanceToMove.get(distance);
            if (moves == null) {
                moves = new HashSet<Move>();
                distanceToMove.put(distance, moves);
            }
            moves.add(move);
        }
        Set<Move> saferMovesToward = distanceToMove.firstEntry().getValue();
        return saferMovesToward.contains(otherPlayer.getLastMove())
                ? Collections.singleton(otherPlayer.getLastMove())
                : saferMovesToward;
    }

    private int distanceFromPlayerAfterMove(OtherPlayer otherPlayer, Move move) {
        return distanceFromPlayer(otherPlayer, getPositionAfterMove(move));
    }

    private int distanceFromPlayer(OtherPlayer otherPlayer, Position position) {
        int dx = position.x - otherPlayer.getX();
        int dy = position.y - otherPlayer.getY();
        return dx * dx + dy * dy;
    }

    OtherPlayer getAnotherPlayer() {
        return otherPlayers.values().iterator().next();
    }
}

final class OtherPlayer extends BasePlayer {
    @Override
    public Move getFirstMove(int p, PlayerInfo[] playerInfos) {
        throw new UnsupportedOperationException();
    }
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        throw new UnsupportedOperationException();
    }
}

abstract class CrazyStarter extends BasePlayer {
    @Override
    public Move getFirstMove(int p, PlayerInfo[] playerInfos) {
        initPositionHistory(p, playerInfos);
        return setAndReturnLastMove(getRandomMove());
    }
}

abstract class AggressiveStarter extends BasePlayer {
    @Override
    public Move getFirstMove(int p, PlayerInfo[] playerInfos) {
        initPositionHistory(p, playerInfos);
        return setAndReturnLastMove(getRandomMove(getSaferMovesToward(getAnotherPlayer())));
    }
}

class Crazy extends CrazyStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        return getRandomMove();
    }
}

class CrazyStraight extends CrazyStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        if (canMove(getLastMove())) {
            return getLastMove();
        }
        return setAndReturnLastMove(getRandomMove());
    }
}

class CrazyHoleAvoider extends CrazyStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        Set<Move> saferMoves = getSaferMoves();
        return setAndReturnLastMove(getRandomMove(saferMoves));
    }
}

class CrazyStraightHoleAvoider extends CrazyStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        Set<Move> saferMoves = getSaferMoves();
        if (saferMoves.contains(getLastMove())) {
            return getLastMove();
        }
        return setAndReturnLastMove(saferMoves.iterator().next());
    }
}

class CrazyAggressiveStraightHoleAvoider extends AggressiveStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        Set<Move> saferMoves = getSaferMoves();
        if (saferMoves.contains(getLastMove())) {
            return getLastMove();
        }
        return setAndReturnLastMove(saferMoves.iterator().next());
    }
}

class CrazyAggressiveHoleAvoider extends AggressiveStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        return setAndReturnLastMove(getRandomMove(getSaferMovesToward(getAnotherPlayer())));
    }
}

class CrazyIntercepterHoleAvoider extends AggressiveStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        OtherPlayer reachablePlayer = getReachablePlayer();
        if (reachablePlayer != null) {
            return setAndReturnLastMove(getRandomMove(getSaferMovesToward(reachablePlayer)));
        }
        Set<Move> saferMoves = getSaferMoves();
        if (saferMoves.contains(getLastMove())) {
            return getLastMove();
        }
        return setAndReturnLastMove(saferMoves.iterator().next());
    }

    OtherPlayer getReachablePlayer() {
        for (OtherPlayer otherPlayer : otherPlayers.values()) {
            if (isReachable(otherPlayer)) {
                return otherPlayer;
            }
        }
        return null;
    }

    private boolean isReachable(OtherPlayer otherPlayer) {
        return isReachable(otherPlayer.getPosition());
    }

    private boolean isReachable(Position target) {
        char[][] grid = initGrid();
        grid[target.x][target.y] = TARGET;
        return isReachable(grid, getPosition());
    }

    private boolean isReachable(char[][] grid, Position position) {
        return isReachable(grid, position.x, position.y);
    }

    boolean isReachable(char[][] grid, int x, int y) {
        if (!isValidPosition(x, y)) {
            return false;
        }
        if (grid[x][y] == TARGET) {
            return true;
        }
        if (grid[x][y] == FREE) {
            grid[x][y] = REACHABLE;
            return isReachable(grid, x + 1, y)
                   || isReachable(grid, x - 1, y)
                   || isReachable(grid, x, y + 1)
                   || isReachable(grid, x, y - 1);
        }
        return false;
    }
}