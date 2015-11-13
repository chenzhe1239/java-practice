package com.janosgyerik.practice.oj.codingame.tron.v2;

import java.util.*;

// TODO no need to recalculate reachability if unreachable until player is removed

// TODO prefer moves that reduce the freedom of the other player
// MEMO consider round +1 too

// TODO avoid moves that give the other player the opportunity to reduce freedom
// MEMO in addition, when close to the enemy, prefer the same direction as enemy.lastMove

// TODO consider potential hole if target player keeps going in the same direction

// TODO consider potential hole if all other players keep going in the same direction

// TODO if dominating a space, don't diminish it, fill it well
// MEMO if no reachable players (keep in mind though a dead player might open a wall)
// MEMO fill well by following a wall in a "safe" direction

// TODO which reachable player is best to attack? the weak or the strong?

// TODO don't walk into a tunnel whose end is longer then the enemy can reach

// TODO don't create an island that nobody can access

class Player {

    static final int LOST_PLAYER_X = -1;

    private static IPlayer createPlayer() {
        return new CarefulAggressiveInterceptor2();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        Grid grid = new RectangleGrid(30, 20);
        IPlayer player = createPlayer();

        while (true) {
            int n = in.nextInt();
            int index = in.nextInt();

            Position me = null;
            Set<Position> others = new HashSet<Position>();

            for (int i = 0; i < n; ++i) {
                int x0 = in.nextInt();
                int y0 = in.nextInt();
                int x1 = in.nextInt();
                int y1 = in.nextInt();

                if (x0 == LOST_PLAYER_X) {
                    grid.removePlayer(i);
                } else {
                    Position position = new Position(x1, y1);
                    grid.addPosition(i, new Position(x0, y0));
                    grid.addPosition(i, position);

                    if (i == index) {
                        me = position;
                    } else {
                        others.add(position);
                    }
                }
            }
            System.out.println(player.getNextMove(me, others, grid));
        }
    }
}

enum Move {
    LEFT, RIGHT, UP, DOWN,
    IMPOSSIBLE("DAAAAAAAAAMN!...");

    public static final Move[] MOVES = {LEFT, RIGHT, UP, DOWN};

    private final String message;

    Move() {
        message = name();
    }

    Move(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

interface IPlayer {
    Move getNextMove(Position me, Set<Position> others, Grid grid);
}

class Position {
    final int x;
    final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position plusMove(Move move) {
        switch (move) {
            case LEFT:
                return new Position(x - 1, y);
            case RIGHT:
                return new Position(x + 1, y);
            case UP:
                return new Position(x, y - 1);
            case DOWN:
                return new Position(x, y + 1);
            default:
                throw new IllegalArgumentException();
        }
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

interface Grid {
    boolean containsPosition(Position position);

    boolean isAvailablePosition(Position position);

    boolean isReachablePosition(Position from, Position to);

    int countReachablePositionsFrom(Position position);

    Set<Move> getPossibleMovesFrom(Position position);

    /**
     * "Safer" moves would result in more reachable positions
     * compared to other less safe moves.
     * @param position to start from
     * @return a non-empty Set of safer moves
     */
    Set<Move> getSaferMovesFrom(Position position);

    Set<Move> getSaferMovesFrom(Position position, Position target);

    Set<Move> getKillerMoves(Position position, Position target);

    void applyMove(IPlayer player, Move move);

    void applyMoveUntilBlocked(IPlayer player, Move move);

    Position getAnyPosition();

    void addPosition(int playerIndex, Position position);

    void removePlayer(int index);

    Grid copy();

    Position getFirstPosition(int playerIndex);

    Set<Position> getAvailablePositions();

    Set<Move> getMovesToward(Position from, Position to);

    Position getClosestReachableTarget(Position me, Set<Position> others);

    int getDistance(Position from, Position to);

    void removePosition(Position position);
}

class RectangleGrid implements Grid {

    private final int width;
    private final int height;

    private final Map<Position, Integer> positions;

    public RectangleGrid(int width, int height) {
        this.width = width;
        this.height = height;
        this.positions = new HashMap<Position, Integer>();
    }

    private RectangleGrid(RectangleGrid other) {
        this.width = other.width;
        this.height = other.height;
        this.positions = new HashMap<Position, Integer>(other.positions);
    }

    @Override
    public RectangleGrid copy() {
        return new RectangleGrid(this);
    }

    @Override
    public Position getFirstPosition(int playerIndex) {
        for (Map.Entry<Position, Integer> entry : positions.entrySet()) {
            if (entry.getValue().equals(playerIndex)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Set<Position> getAvailablePositions() {
        Set<Position> available = new HashSet<Position>();
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                Position position = new Position(i, j);
                if (isAvailablePosition(position)) {
                    available.add(position);
                }
            }
        }
        return available;
    }

    @Override
    public Set<Move> getMovesToward(Position from, Position to) {
        int distance = getDistance(from, to);
        if (distance == 0) {
            return Collections.emptySet();
        }
        Set<Move> toward = new HashSet<Move>();
        for (Move move : getPossibleMovesFrom(from)) {
            Position next = from.plusMove(move);
            int d = getDistance(next, to);
            if (d > 0 && d < distance) {
                toward.add(move);
            }
        }
        return toward;
    }

    @Override
    public Position getClosestReachableTarget(Position from, Set<Position> others) {
        return getClosestReachableTarget(copy(), from, others);
    }

    private Position getClosestReachableTarget(Grid grid, Position from, Set<Position> targets) {
        Set<Position> flood = new HashSet<Position>();
        flood.add(from);
        while (!flood.isEmpty()) {
            Set<Position> flood0 = flood;
            flood = new HashSet<Position>();
            for (Position position : flood0) {
                for (Move move : Move.MOVES) {
                    Position next = position.plusMove(move);
                    if (targets.contains(next)) {
                        return next;
                    }
                    if (grid.containsPosition(next) && grid.isAvailablePosition(next)) {
                        flood.add(next);
                    }
                    grid.addPosition(Integer.MAX_VALUE, next);
                }
            }
        }
        return null;
    }

    @Override
    public int getDistance(Position from, Position to) {
        return getDistance(copy(), from, to);
    }

    private int getDistance(Grid grid, Position from, Position to) {
        Set<Position> flood = new HashSet<Position>();
        flood.add(from);
        int distance = 0;
        while (!flood.isEmpty()) {
            ++distance;
            Set<Position> flood0 = flood;
            flood = new HashSet<Position>();
            for (Position position : flood0) {
                for (Move move : Move.MOVES) {
                    Position next = position.plusMove(move);
                    if (next.equals(to)) {
                        return distance;
                    }
                    if (grid.containsPosition(next) && grid.isAvailablePosition(next)) {
                        flood.add(next);
                    }
                    grid.addPosition(Integer.MAX_VALUE, next);
                }
            }
        }
        return -1;
    }

    @Override
    public boolean containsPosition(Position position) {
        return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
    }

    @Override
    public boolean isAvailablePosition(Position position) {
        return ! positions.containsKey(position);
    }

    @Override
    public boolean isReachablePosition(Position from, Position to) {
        return false;
    }

    @Override
    public int countReachablePositionsFrom(Position position) {
        return countReachablePositionsFrom(copy(), position);
    }

    private int countReachablePositionsFrom(Grid grid, Position position) {
        if (!grid.containsPosition(position) || !grid.isAvailablePosition(position)) {
            return 0;
        }
        grid.addPosition(Integer.MAX_VALUE, position);
        int count = 1;
        for (Move move : Move.MOVES) {
            Position next = position.plusMove(move);
            count += countReachablePositionsFrom(grid, next);
        }
        return count;
    }

    @Override
    public Set<Move> getPossibleMovesFrom(Position position) {
        Set<Move> possibleMoves = new HashSet<Move>();
        for (Move move : Move.MOVES) {
            Position newPosition = position.plusMove(move);
            if (containsPosition(newPosition) && isAvailablePosition(newPosition)) {
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }

    @Override
    public Set<Move> getSaferMovesFrom(Position position) {
        SortedMap<Integer, Set<Move>> saferMoves = new TreeMap<Integer, Set<Move>>();
        for (Move move : getPossibleMovesFrom(position)) {
            Position newPosition = position.plusMove(move);
            int num = countReachablePositionsFrom(newPosition);
            if (!saferMoves.containsKey(num)) {
                saferMoves.put(num, new HashSet<Move>());
            }
            saferMoves.get(num).add(move);
        }
        return saferMoves.isEmpty() ? Collections.<Move>emptySet() : saferMoves.get(saferMoves.lastKey());
    }

    @Override
    public Set<Move> getSaferMovesFrom(Position position, Position target) {
        SortedMap<Integer, Set<Move>> saferMoves = new TreeMap<Integer, Set<Move>>();
        saferMoves.put(0, new HashSet<Move>());
        for (Move move : getPossibleMovesFrom(position)) {
            Position next = position.plusMove(move);
            Grid grid = copy();
            grid.addPosition(9, next);
            int worstNum = Integer.MAX_VALUE;
            Set<Move> possibleEnemyMoves = grid.getPossibleMovesFrom(target);
            grid.removePosition(next);
            for (Move enemyMove : possibleEnemyMoves) {
                Position enemyNext = target.plusMove(enemyMove);
                grid.addPosition(9, enemyNext);
                int num = Math.min(grid.getPossibleMovesFrom(next).size(), 2);
                if (num < worstNum) {
                    worstNum = num;
                }
                grid.removePosition(enemyNext);
            }
            if (!saferMoves.containsKey(worstNum)) {
                saferMoves.put(worstNum, new HashSet<Move>());
            }
            saferMoves.get(worstNum).add(move);
        }
        return saferMoves.get(saferMoves.lastKey());
    }

    @Override
    public Set<Move> getKillerMoves(Position position, Position target) {
        Set<Move> enemyPossibleMoves = getPossibleMovesFrom(target);
        if (enemyPossibleMoves.size() == 1) {
            Grid grid = copy();
            grid.removePosition(target);
            int reachableBefore = grid.countReachablePositionsFrom(target) - 1;
            int minReachable = reachableBefore;
            Move killer = null;
            for (Move move : getPossibleMovesFrom(position)) {
                Position next = position.plusMove(move);
                grid.addPosition(9, next);
                if (grid.getPossibleMovesFrom(target).isEmpty()) {
                    return Collections.singleton(move);
                }
                int reachableNum = grid.countReachablePositionsFrom(target);
                if (reachableNum < minReachable) {
                    minReachable = reachableNum;
                    killer = move;
                }
                grid.removePosition(next);
            }
            if (killer != null) {
                return Collections.singleton(killer);
            }
        }
        return Collections.emptySet();
    }

    @Override
    public void applyMove(IPlayer player, Move move) {
    }

    @Override
    public void applyMoveUntilBlocked(IPlayer player, Move move) {
    }

    @Override
    public Position getAnyPosition() {
        return new Position(width / 2, height / 2);
    }

    @Override
    public void addPosition(int playerIndex, Position position) {
        positions.put(position, playerIndex);
    }

    @Override
    public void removePosition(Position position) {
        positions.remove(position);
    }

    @Override
    public void removePlayer(int index) {
        Set<Position> toRemove = new HashSet<Position>();
        for (Map.Entry<Position, Integer> entry : positions.entrySet()) {
            if (entry.getValue().equals(index)) {
                toRemove.add(entry.getKey());
            }
        }
        for (Position position : toRemove) {
            positions.remove(position);
        }
    }
}

abstract class BasePlayer implements IPlayer {
    protected Move lastMove = Move.RIGHT;

    protected Move setAndReturnLastMove(Move move) {
        return lastMove = move;
    }
}

/**
 * - move toward the closest reachable target
 * - prefer to repeat the last move
 * - choose first if multiple choices
 */
class CarefulStraightInterceptor extends BasePlayer {
    @Override
    public Move getNextMove(Position me, Set<Position> others, Grid grid) {
        Set<Move> safer = grid.getSaferMovesFrom(me);
        Position target = grid.getClosestReachableTarget(me, others);
        if (target != null) {
            Set<Move> toward = grid.getMovesToward(me, target);
            if (toward.contains(lastMove) && safer.contains(lastMove)) {
                return lastMove;
            }
            for (Move move : toward) {
                if (safer.contains(move)) {
                    return setAndReturnLastMove(move);
                }
            }
        }
        if (!safer.isEmpty()) {
            if (safer.contains(lastMove)) {
                return lastMove;
            }
            return setAndReturnLastMove(safer.iterator().next());
        }
        return Move.IMPOSSIBLE;
    }
}

/**
 * - move toward the closest reachable target
 * - avoid moves that let the other player to reduce mobility
 */
class CarefulAggressiveInterceptor extends BasePlayer {
    @Override
    public Move getNextMove(Position me, Set<Position> others, Grid grid) {
        Set<Move> safer = grid.getSaferMovesFrom(me);
        Position target = grid.getClosestReachableTarget(me, others);
        if (target != null) {
            Set<Move> toward = grid.getMovesToward(me, target);
            Set<Move> safer2 = grid.getSaferMovesFrom(me, target);
            if (toward.contains(lastMove) && safer.contains(lastMove) && safer2.contains(lastMove)) {
                return lastMove;
            }
            for (Move move : toward) {
                if (safer.contains(move) && safer2.contains(move)) {
                    return setAndReturnLastMove(move);
                }
            }
            if (safer.contains(lastMove) && safer2.contains(lastMove)) {
                return lastMove;
            }
            for (Move move : safer2) {
                if (safer.contains(move)) {
                    return setAndReturnLastMove(move);
                }
            }
        }
        if (safer.contains(lastMove)) {
            return lastMove;
        }
        for (Move move : safer) {
            return setAndReturnLastMove(move);
        }
        return Move.IMPOSSIBLE;
    }
}

class MoveFilter {
    Set<Move> moves = null;

    MoveFilter() {
    }

    MoveFilter(Set<Move> moves) {
        this.moves = moves;
    }

    MoveFilter filter(MoveFilter moveFilter) {
        // stop filtering
        if (moves.size() < 2) {
            return this;
        }
        return moveFilter.apply(moves);
    }

    public Move getRandomMove() {
        if (moves.isEmpty()) {
            return Move.IMPOSSIBLE;
        }
        // TODO make it proper random
        return moves.iterator().next();
    }

    public void useIntersectionIfExists(Set<Move> incoming) {
        Set<Move> intersection = new HashSet<Move>(moves);
        intersection.retainAll(incoming);
        if (!intersection.isEmpty()) {
            moves = intersection;
        }
    }

    MoveFilter apply(Set<Move> moves) {
        this.moves = moves;
        return this;
    }
}

class LargerSpaceFilter extends MoveFilter {
    private final Grid grid;
    private final Position me;

    LargerSpaceFilter(Grid grid, Position me) {
        this.grid = grid;
        this.me = me;
    }

    @Override
    MoveFilter apply(Set<Move> moves) {
        super.apply(moves);
        SortedMap<Integer, Set<Move>> reachableNumByMoves = new TreeMap<Integer, Set<Move>>();
        for (Move move : moves) {
            Position next = me.plusMove(move);
            int num = grid.countReachablePositionsFrom(next);
            if (!reachableNumByMoves.containsKey(num)) {
                reachableNumByMoves.put(num, new HashSet<Move>());
            }
            reachableNumByMoves.get(num).add(move);
        }
        useIntersectionIfExists(reachableNumByMoves.get(reachableNumByMoves.lastKey()));
        return this;
    }
}

class MoveTowardFilter extends MoveFilter {
    private final Grid grid;
    private final Position me;
    private final Position target;

    MoveTowardFilter(Grid grid, Position position, Position target) {
        this.grid = grid;
        this.me = position;
        this.target = target;
    }

    @Override
    MoveFilter apply(Set<Move> moves) {
        super.apply(moves);
        if (target != null) {
            Set<Move> toward = grid.getMovesToward(me, target);
            useIntersectionIfExists(toward);
        }
        return this;
    }
}

class ImminentTrapFilter extends MoveFilter {
    private final Grid grid;
    private final Position me;
    private final Position target;

    ImminentTrapFilter(Grid grid, Position me, Position target) {
        this.grid = grid;
        this.me = me;
        this.target = target;
    }

    @Override
    MoveFilter apply(Set<Move> moves) {
        super.apply(moves);
        if (target != null) {
            Set<Move> safer2 = grid.getSaferMovesFrom(me, target);
            useIntersectionIfExists(safer2);
        }
        return this;
    }
}

class KillerMoveFilter extends MoveFilter {
    private final Grid grid;
    private final Position me;
    private final Position target;

    public KillerMoveFilter(Grid grid, Position me, Position target) {
        this.grid = grid;
        this.me = me;
        this.target = target;
    }

    @Override
    MoveFilter apply(Set<Move> moves) {
        super.apply(moves);
        if (target != null) {
            Set<Move> killerMoves = grid.getKillerMoves(me, target);
            useIntersectionIfExists(killerMoves);
        }
        return this;
    }
}

/**
 * - move toward the closest reachable target
 * - prefer safer moves that reduce mobility of the target
 * - avoid moves that let the target to reduce mobility
 */
class CarefulAggressiveInterceptor2 extends BasePlayer {
    @Override
    public Move getNextMove(Position me, Set<Position> others, Grid grid) {
        Position target = grid.getClosestReachableTarget(me, others);
        Move move = new MoveFilter(grid.getPossibleMovesFrom(me))
                .filter(new LargerSpaceFilter(grid, me))
                .filter(new KillerMoveFilter(grid, me, target))
                .filter(new ImminentTrapFilter(grid, me, target))
                // keep the moves that hurt the target
//                .filter(new MoveFilter(null))
                // keep the moves that don't close down space
//                .filter(new MoveFilter(null))
                .filter(new MoveTowardFilter(grid, me, target))
                .getRandomMove();
        return setAndReturnLastMove(move);
    }
}