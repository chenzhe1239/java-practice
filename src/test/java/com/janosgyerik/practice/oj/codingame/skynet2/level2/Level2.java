package com.janosgyerik.practice.oj.codingame.skynet2.level2;

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

        Set<Node> exits = new HashSet<Node>();
        for (int i = 0; i < e; ++i) {
            int ei = in.nextInt();
            Node node = nodes.get(ei);
            node.setExit();
            exits.add(node);
        }

        Set<Link> linksToExits = new HashSet<Link>();
        for (Link link : links) {
            if (link.isConnectedToExit()) {
                linksToExits.add(link);
            }
        }

        while (true) {
            int si = in.nextInt();

            Node agentNode = nodes.get(si);

            Link linkToCut = getConnectedLink(linksToExits, agentNode);

            if (linkToCut == null) {
                Map<Node, Set<Link>> nodesToExitLinks = partitionByExitLinks(linksToExits);

                TreeMap<Integer, Set<Node>> nodesByExitLinkCount = partitionByExitLinkCount(nodesToExitLinks.entrySet());

                Map.Entry<Integer, Set<Node>> mostAccessibleEntry = nodesByExitLinkCount.lastEntry();
                if (mostAccessibleEntry.getKey() > 1) {
                    Set<Node> nodesSet = mostAccessibleEntry.getValue();
                    linkToCut = getConnectedLink(nodesToExitLinks, nodesSet, agentNode);
                    if (linkToCut == null) {
                        Node[] nodesArr = nodesSet.toArray(new Node[nodesSet.size()]);
                        Node randomNode = nodesArr[(int) (Math.random() * nodesArr.length)];

                        Set<Link> linksSet = nodesToExitLinks.get(randomNode);
                        Link[] linksArr = linksSet.toArray(new Link[linksSet.size()]);
                        linkToCut = linksArr[(int) (Math.random() * linksArr.length)];
                    }
                } else {
                    linkToCut = linksToExits.iterator().next();
                }
            }

            linksToExits.remove(linkToCut);

            System.out.println(linkToCut);
        }
    }

    private static Map<Node, Set<Link>> partitionByExitLinks(Set<Link> linksToExits) {
        Map<Node, Set<Link>> nodesToExitLinks = new HashMap<Node, Set<Link>>();
        for (Link link : linksToExits) {
            Node nonExit = link.getNonExitNode();
            Set<Link> myLinksToExits = nodesToExitLinks.get(nonExit);
            if (myLinksToExits == null) {
                myLinksToExits = new HashSet<Link>();
                nodesToExitLinks.put(nonExit, myLinksToExits);
            }
            myLinksToExits.add(link);
        }
        return nodesToExitLinks;
    }

    private static TreeMap<Integer, Set<Node>> partitionByExitLinkCount(Set<Map.Entry<Node,Set<Link>>> nodesToExitLinks) {
        TreeMap<Integer, Set<Node>> nodesByExitLinkCount = new TreeMap<Integer, Set<Node>>();
        for (Map.Entry<Node, Set<Link>> entry : nodesToExitLinks) {
            Node node = entry.getKey();
            int count = entry.getValue().size();
            Set<Node> nodes = nodesByExitLinkCount.get(count);
            if (nodes == null) {
                nodes = new HashSet<Node>();
                nodesByExitLinkCount.put(count, nodes);
            }
            nodes.add(node);
        }
        return nodesByExitLinkCount;
    }

    private static Link getConnectedLink(Set<Link> links, Node node) {
        for (Link link : links) {
            if (link.isConnectedTo(node)) {
                return link;
            }
        }
        return null;
    }

    private static Link getConnectedLink(Map<Node,Set<Link>> nodesToExitLinks, Set<Node> nodes, Node targetNode) {
        for (Node node : nodes) {
            if (node.hasNeighbor(targetNode)) {
                return nodesToExitLinks.get(node).iterator().next();
            }
        }
        return null;
    }
}

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

    void setExit() {
        this.exit = true;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean hasNeighbor(Node node) {
        return neighbors.contains(node);
    }

    @Override
    public String toString() {
        return "" + id;
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

    public boolean isConnectedToExit() {
        return n1.isExit() || n2.isExit();
    }

    public boolean isConnectedTo(Node node) {
        return n1 == node || n2 == node;
    }

    @Override
    public String toString() {
        return String.format("%s %s", n1, n2);
    }

    public Node getNonExitNode() {
        return n1.isExit() ? n2 : n1;
    }
}

