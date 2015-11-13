package com.janosgyerik.practice.oj.codingame.misc.test1;

import java.util.*;

enum Color {
    Spade, Diamond, Club, Hearth
}

enum Value {
    Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace
}

class GameException extends RuntimeException {

}

class Card {
    public final Color color;
    public final Value value;

    public Card(Color c, Value v) {
        this.color = c;
        this.value = v;
    }
}

class Deck {
    protected List<Card> cards;

    private void initDeck(int nb_cards) {
        int nb_card_distribute = 0;
        while (nb_card_distribute < nb_cards) {
            for (Color c : Color.values()) {
                if (nb_card_distribute >= nb_cards)
                    break;
                for (Value v : Value.values()) {
                    if (nb_card_distribute >= nb_cards)
                        break;
                    cards.add(new Card(c, v));
                    nb_card_distribute++;
                }
            }
        }
    }

    public Deck(int nb_cards) {
        if (nb_cards % 4 != 0)
            throw new GameException();
        cards = new ArrayList<>(nb_cards);
        initDeck(nb_cards);
    }

    public int size() {
        return cards.size();
    }

    public Collection<Card> displayDeck() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}

class RiggedDeck extends Deck {
    public RiggedDeck(int nb_cards) {
        super(nb_cards);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards, new Random(0));
    }
}

class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        int cmp = o1.color.compareTo(o2.color);
        if (cmp != 0) {
            return cmp;
        }
        return o1.value.compareTo(o2.value);
    }
}

class Player {
    private Collection<Card> hand;

    private String name;

    public Player(String name) {
        this.name = name;
        hand = new TreeSet<>(new CardComparator());
    }

    public Collection<Card> getHand() {
        return hand;
    }

    public void addCard(Card c) {
        if (hand.contains(c)) {
            throw new GameException();
        }
        hand.add(c);
    }
}

class Game {
    private int nb_player;
    private int nb_card;
    private Deck deck;
    private List<Player> players;

    public Game(int nb_player, int nb_card) {
        this.nb_player = nb_player;
        this.nb_card = nb_card;
        this.players = initPlayers(nb_player);
        this.deck = initDeck(nb_card);
    }

    public Game(int nb_player, Deck deck) {
        this.players = initPlayers(nb_player);
        this.deck = deck;
    }

    private Deck initDeck(int nb_card) {
        return new Deck(nb_card);
    }

    private List<Player> initPlayers(int nb_player) {
        players = new ArrayList<>(nb_player);
        for (int i = 0; i < nb_player; ++i) {
            players.add(new Player("Player " + i));
        }
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    List<Player> getPlayers() {
        return players;
    }

    public void startGame() {
        shuffleDeck();
        distributeDeck();
    }

    public void distributeDeck() {
        int cardsPerPlayer = deck.size() / players.size();
        Iterator<Card> iter = deck.displayDeck().iterator();
        while (iter.hasNext()) {
            Card card = iter.next();
            for (Player player : players) {
                if (player.getHand().size() < cardsPerPlayer) {
                    try {
                        player.addCard(card);
                    } catch (GameException e) {
                        // skip
                    }
                }
            }
        }
    }

    public void shuffleDeck() {
        this.deck.shuffle();
    }

}
