package com.janosgyerik.practice.oj.codingame.skynet2.level1;

import java.util.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int l = in.nextInt();
        int e = in.nextInt();

        Map<Integer, Node> nodes = new HashMap<Integer, Node>();
        for (int i = 0; i < n; ++i) {
            nodes.put(i, new Node(i));
        }

        Set<Link> links = new HashSet<Link>();
        for (int i = 0; i < l; ++i) {
            int n1 = in.nextInt();
            int n2 = in.nextInt();
            links.add(new Link(nodes.get(n1), nodes.get(n2)));
        }
        for (int i = 0; i < e; ++i) {
            int ei = in.nextInt();
            nodes.get(ei).setExit();
        }

        while (true) {
            int si = in.nextInt();

            Node agentNode = nodes.get(si);
            Node other = agentNode.getExitOrRandomNeighbor();

            System.out.println(String.format("%s %s", agentNode.id, other.id));
        }
    }
}


// TODO
// calculate distance from each exit to agent
// later: reuse previously calculated

// TODO
// get set of exits closest to agent, cut in random

class Node {
    final int id;
    Set<Node> neighbors = new HashSet<Node>();
    boolean exit = false;

    Node(int id) {
        this.id = id;
    }

    void addNeighbor(Node node) {
        neighbors.add(node);
    }

    void removeNeighbor(Node node) {
        neighbors.remove(node);
    }

    void setExit() {
        this.exit = true;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Node && ((Node) o).id == id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public boolean isExit() {
        return exit;
    }

    Node getExitOrRandomNeighbor() {
        for (Node neighbor : neighbors) {
            if (neighbor.isExit()) {
                return neighbor;
            }
        }
        return getRandomNeighbor(neighbors);
    }

    public Node[] getNodesArray(Set<Node> nodes) {
        return nodes.toArray(new Node[nodes.size()]);
    }

    public Node getRandomNeighbor(Set<Node> nodes) {
        Node[] nodesArray = getNodesArray(nodes);
        return nodesArray[(int) (Math.random() * nodesArray.length)];
    }

}

class Link {
    private final Node n1;
    private final Node n2;

    Link(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    boolean isInDanger() {
        return n1.isExit() || n2.isExit();
    }
}

