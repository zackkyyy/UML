package BlackJack.model;

import java.util.LinkedList;
import java.util.List;

public class Deck {

    private List<Card> m_cards;

    public Deck() {
        m_cards = new LinkedList<>();

        for (int cIx = 0; cIx < Card.color.Count.ordinal(); cIx++) {
            for (int vIx = 0; vIx < Card.value.Count.ordinal(); vIx++) {
                Card c = new Card(Card.color.values()[cIx], Card.value.values()[vIx]);
                addCard(c);
            }
        }

        shuffle();
    }


    public void addCard(Card a_cardToAdd) {
        m_cards.add(a_cardToAdd);
    }

    public Card getCard() {
        Card c = m_cards.get(0);
        m_cards.remove(0);

        return c;
    }

    public Iterable<Card> GetCards() {
        return m_cards;
    }

    private void shuffle() {
        for (int i = 0; i < 1017; i++) {
            int index = (int) (Math.random() * 171717.0) % m_cards.size();
            Card c = m_cards.get(index);
            m_cards.remove(index);
            addCard(c);
        }
    }

}