package BlackJack.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {

  private List<Card> m_hand;
  protected final int g_maxScore = 21;
  private Deck m_deck= new Deck();
  private ArrayList<IObserver> m_subscribers;

    public Player()
  {
    m_subscribers=new ArrayList<IObserver>();
    m_hand = new LinkedList<Card>();
    System.out.println("Hello List World");
  }
  
  public void dealCard(Card a_addToHand)
  {
    m_hand.add(a_addToHand);
      for(IObserver a_subscriber: m_subscribers){
          a_subscriber.update();
      }
  }
  
  public Iterable<Card> getHand()
  {
    return m_hand;
  }
  
  public void clearHand()
  {
    m_hand.clear();
  }
  
  public void showHand()
  {
    for(Card c : m_hand)
    {
      c.show(true);

    }
  }
  
  public int calcScore()
  {
    // the number of scores is dependant on the number of scorable values
    // as it seems there is no way to do this check at compile time in java ?!
    // cardScores[13] = {...};
    int cardScores[] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11 };
    assert (cardScores.length == Card.value.Count.ordinal()) : "Card Scores array size does not match number of card values";
  
    
    int score = 0;

    for(Card c : getHand()) {
        if (c.getValue() != Card.value.Hidden)
        {
            score += cardScores[c.getValue().ordinal()];
        }
    }

    if (score > g_maxScore)
    {
        for(Card c : getHand())
        {
            if (c.getValue() == Card.value.Ace && score > g_maxScore)
            {
                score -= 10;
            }
        }
    }

    return score;
  }
    public void getNewCard(Player a_player , Boolean show){
        Card c = m_deck.getCard();
        c.show(show);
        a_player.dealCard(c);
    }


    public void addSubscribers(IObserver a_subscriber) {
        m_subscribers.add(a_subscriber);
    }
}