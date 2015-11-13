package com.janosgyerik.practice.oj.codingame.misc.test1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardGameTest {
    @Test
    public void testExample() {
        int nb_cards = 52;
        int nb_players = 4;
        Game g = new Game(nb_players, nb_cards);

        g.startGame();

        assertEquals(52, g.getDeck().size());
        assertEquals(4, g.getPlayers().size());
        assertEquals(13, g.getPlayers().get(0).getHand().size());
    }

    @Test(expected = GameException.class)
    public void handleDuplicateCards() {
        Player player = new Player("Jack");
        player.addCard(new Card(Color.Club, Value.Ace));
        player.addCard(new Card(Color.Club, Value.Ace));
    }

    @Test
    public void testRiggedGame() {
        int nb_cards = 104;
        int nb_players = 4;
        Deck deck = new RiggedDeck(nb_cards);
        Game g = new Game(nb_players, deck);

        g.startGame();

        assertEquals(104, g.getDeck().size());
        assertEquals(4, g.getPlayers().size());
        for (Player player : g.getPlayers()) {
            assertEquals(26, player.getHand().size());
        }
    }
}
