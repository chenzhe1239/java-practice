package com.janosgyerik.practice.oj.codingame.GhostInTheCell;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Player {

    public static final int INCREASE_PRODUCTION_COST = 10;

    public static void main(String args[]) {
        Game game = new Game(new Scanner(System.in));

        int round = 0;
        int bombs = 2;

        while (true) {
            round++;

            game.parseRound();

            Commands commands = new Commands();

            game.factories().filter(f -> f.bomb != null).forEach(f -> f.evacuate(commands));

            //game.factories().forEach(f -> attackDefeatableNeutrals(commands, f));
            if (bombs > 0) {
                game.factories().forEach(f -> f.neighbors().filter(Factory::enemy).findFirst());
            }
            game.factories().forEach(f -> increaseProduction(commands, f));
            game.factories().forEach(f -> supportFriendsToIncreaseProduction(commands, f));
            game.factories().forEach(f -> takeClosestNeutral(commands, f));

            if (round > 40) game.factories().forEach(f -> attackClosestEnemies(commands, f));
            //game.factories().forEach(f -> attackAllEnemies(commands, f));

            commands.print();
        }
    }

    private static void attackClosestEnemies(Commands commands, Factory factory) {
        factory.neighbors()
                .filter(Factory::enemy)
                .filter(f -> f.troops() >= 0)
                .forEach(f -> commands.add(factory.send(f.id, f.cyborgs + f.troops())));
    }

    private static void supportFriendsToIncreaseProduction(Commands commands, Factory factory) {
        if (factory.production < 3 || factory.cyborgs < factory.troops() + INCREASE_PRODUCTION_COST) return;

        factory.neighbors()
                .filter(Factory::mine)
                .filter(f -> !f.disabled)
                .filter(f -> f.production < factory.production)
                .filter(f -> f.troops() == 0)
                .filter(f -> factory.distance(f) < f.distance(f.neighbors().filter(Factory::enemy).findFirst().orElseGet(() -> factory)))
                .forEach(f -> commands.add(factory.send(f.id, INCREASE_PRODUCTION_COST)));
    }

    private static void takeClosestNeutral(Commands commands, Factory factory) {
        if (factory.production < 3 || factory.cyborgs < factory.troops() + INCREASE_PRODUCTION_COST) return;

        factory.neighbors()
                .filter(f -> !f.mine())
                .findFirst()
                .ifPresent(f2 -> Stream.of(f2)
                        .filter(Factory::neutral)
                        .filter(f -> f.troops() == 0)
                        .filter(f -> factory.distance(f) < f.distance(f.neighbors().filter(Factory::enemy).findFirst().orElseGet(() -> factory)))
                        .forEach(f -> commands.add(factory.send(f.id, INCREASE_PRODUCTION_COST)))
                );
    }

    private static void attackAllEnemies(Commands commands, Factory factory) {
        List<Factory> targets = factory.neighbors()
                .filter(Factory::enemy)
                .filter(f2 -> f2.troops() >= 0)
                .collect(Collectors.toList());

        if (targets.isEmpty()) return;

        int squad = factory.cyborgs / targets.size();
        if (squad == 0) return;

        targets.forEach(f2 -> commands.add(factory.send(f2.id, squad)));
    }

    private static void increaseProduction(Commands commands, Factory factory) {
        if (factory.production < 3
                && factory.cyborgs >= INCREASE_PRODUCTION_COST
            //&& factory.neighbors().noneMatch(Factory::enemy)
            // TODO do not increase if at high risk of getting taken over
                ) {
            commands.add(factory.increaseProduction());
        }
    }

    static void attackDefeatableNeutrals(Commands commands, Factory factory) {
        Map<Integer, Integer> resistance = new HashMap<>();
        List<Factory> defeatable = factory.neighbors()
                .filter(Factory::neutral)
                .filter(neigh -> factory.canDefeat(neigh, resistance))
                .collect(Collectors.toList());

        defeatable.sort(Comparator.comparingInt(f -> resistance.get(f.id)));

        for (Factory neigh : defeatable) {
            int defence = resistance.get(neigh.id);
            if (factory.cyborgs >= defence) {
                commands.add(factory.send(neigh.id, defence));
            } else {
                break;
            }
        }
    }

    static class Game {
        private final Scanner scanner;
        private final Graph graph;
        final Factory[] factories;
        Set<Integer> bombs = new HashSet<>();

        Game(Scanner scanner) {
            int factoryCount = scanner.nextInt(); // the number of factories
            int linkCount = scanner.nextInt(); // the number of links between factories
            graph = createGraph(scanner, factoryCount, linkCount);
            factories = createFactories(factoryCount, graph);
            this.scanner = scanner;
        }

        public void parseRound() {
            Stream.of(factories).forEach(Factory::resetTroops);

            int entityCount = scanner.nextInt();
            Set<Integer> activeBombs = new HashSet<>();

            for (int i = 0; i < entityCount; i++) {
                int entityId = scanner.nextInt();
                String entityType = scanner.next();
                int arg1 = scanner.nextInt();
                int arg2 = scanner.nextInt();
                int arg3 = scanner.nextInt();
                int arg4 = scanner.nextInt();
                int arg5 = scanner.nextInt();

                if (entityType.equals("FACTORY")) {
                    Factory factory = factories[entityId];
                    factory.owner = arg1;
                    factory.cyborgs = arg2;
                    factory.production = arg3;
                    factory.disabled = arg4 > 0;
                } else if (entityType.equals("TROOP")) {
                    Troop troop = new Troop(arg1, arg2, arg3, arg4, arg5);
                    Factory factory = factories[troop.target];
                    if (troop.owner == 1) {
                        factory.expectedFriendly = troop.cyborgs;
                    } else {
                        factory.expectedEnemy = troop.cyborgs;
                    }
                } else if (entityType.equals("BOMB")) {
                    if (arg1 == -1) {
                        activeBombs.add(entityId);
                        if (bombs.add(entityId)) {
                            factories().filter(f -> f.bomb == null).forEach(f -> f.bomb = entityId);
                        }
                    }
                }
            }

            bombs.stream()
                    .filter(b -> !activeBombs.contains(b))
                    .forEach(b -> factories().filter(f -> b.equals(f.bomb)).forEach(f -> f.bomb = null));
        }

        public Stream<Factory> factories() {
            return Stream.of(factories)
                    .filter(Factory::mine)
                    .filter(f -> f.cyborgs > f.troops());
        }
    }

    static class Troop {
        final int owner;
        final int source;
        final int target;
        final int cyborgs;
        final int eta;

        public Troop(int owner, int source, int target, int cyborgs, int eta) {
            this.owner = owner;
            this.source = source;
            this.target = target;
            this.cyborgs = cyborgs;
            this.eta = eta;
        }

        @Override
        public String toString() {
            return "Troop{" +
                    "owner=" + owner +
                    ", source=" + source +
                    ", target=" + target +
                    ", cyborgs=" + cyborgs +
                    ", eta=" + eta +
                    '}';
        }
    }

    static Factory[] createFactories(int factoryCount, Graph graph) {
        Factory[] factories = new Factory[factoryCount];
        for (int id = 0; id < factoryCount; id++) {
            factories[id] = new Factory(id, graph, factories);
        }
        return factories;
    }

    static Graph createGraph(Scanner in, int factoryCount, int linkCount) {
        Graph g = new Graph(factoryCount);
        for (int i = 0; i < linkCount; i++) {
            int factory1 = in.nextInt();
            int factory2 = in.nextInt();
            int distance = in.nextInt();
            g.connect(factory1, factory2, distance);
        }
        g.map.values().forEach(list -> list.sort(Comparator.comparingInt(l -> l.distance)));
        return g;
    }

    static class Commands {
        List<Command> commands = new ArrayList<>();

        void print() {
            if (commands.isEmpty()) {
                commands.add(new Wait());
            }
            String cmd = commands.stream().map(Command::toString).collect(Collectors.joining(";"));
            System.out.println(cmd);
        }

        public void add(Command command) {
            commands.add(command);
        }
    }

    interface Command {

    }

    static abstract class AbstractCommand implements Command {
        final String cmd;

        protected AbstractCommand(String cmd) {
            this.cmd = cmd;
        }

        @Override
        public String toString() {
            return cmd;
        }
    }

    static class Wait extends AbstractCommand {
        protected Wait() {
            super("WAIT");
        }
    }

    static class Move extends AbstractCommand {
        protected Move(int source, int destination, int count) {
            super(String.format("MOVE %d %d %d", source, destination, count));
        }
    }

    static class IncreaseProduction extends AbstractCommand {
        protected IncreaseProduction(int factoryId) {
            super("INC " + factoryId);
        }
    }

    static class Link {
        int to, distance;

        Link(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }

    static class Graph {
        final Map<Integer, List<Link>> map;
        final Map<Integer, Integer> distances = new HashMap<>();

        Graph(int n) {
            this.map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(i, new ArrayList<>());
            }
        }

        void connect(int from, int to, int distance) {
            map.get(from).add(new Link(to, distance));
            map.get(to).add(new Link(from, distance));
            distances.put(from * map.size() + to, distance);
            distances.put(to * map.size() + from, distance);
        }

        public int distance(int from, int to) {
            return distances.get(from * map.size() + to);
        }
    }

    static class Factory {
        private final int id;
        private final Graph graph;
        private final Factory[] factories;

        int owner = 0;
        int cyborgs = 0;
        int production = 0;
        public int expectedFriendly = 0;
        public int expectedEnemy = 0;
        public Integer bomb;
        public boolean disabled = false;

        Factory(int id, Graph graph, Factory[] factories) {
            this.id = id;
            this.graph = graph;
            this.factories = factories;
        }

        boolean mine() {
            return owner == 1;
        }

        boolean enemy() {
            return owner == -1;
        }

        boolean neutral() {
            return owner == 0;
        }

        int troops() {
            return expectedEnemy - expectedFriendly;
        }

        void resetTroops() {
            expectedFriendly = 0;
            expectedEnemy = 0;
        }

        Stream<Factory> neighbors() {
            return graph.map.get(id).stream().map(link -> factories[link.to]);
        }

        private void reduceCyborgs(int count) {
            cyborgs -= count;
            if (cyborgs < 0) {
                cyborgs = 0;
            }
        }

        public Command send(int target, int count) {
            reduceCyborgs(count);
            factories[target].expectedFriendly += count;
            return new Move(this.id, target, count);
        }

        public Command increaseProduction() {
            reduceCyborgs(cyborgs);
            return new IncreaseProduction(id);
        }

        public int distance(Factory target) {
            return graph.distance(id, target.id);
        }

        public boolean canDefeat(Factory target, Map<Integer, Integer> defences) {
            int resistance = target.cyborgs + 1 + distance(target) * target.production + target.troops();
            defences.put(target.id, resistance);
            return cyborgs >= resistance;
        }

        @Override
        public String toString() {
            return "Factory{" +
                    "id=" + id +
                    ", cyborgs=" + cyborgs +
                    ", production=" + production +
                    '}';
        }

        public void evacuate(Commands commands) {
            neighbors()
                    .filter(Factory::neutral)
                    .findFirst()
                    .ifPresent(f -> commands.add(send(f.id, cyborgs)));
        }
    }
}
