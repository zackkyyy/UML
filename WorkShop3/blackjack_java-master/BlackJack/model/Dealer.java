package BlackJack.model;

import BlackJack.model.rules.IHitStrategy;
import BlackJack.model.rules.INewGameStrategy;
import BlackJack.model.rules.IWinStrategy;
import BlackJack.model.rules.RulesFactory;

public class Dealer extends Player {

    private Deck m_deck;
    private INewGameStrategy m_newGameRule;
    private IHitStrategy m_hitRule;
    private IWinStrategy m_winRule;

    public Dealer(RulesFactory a_rulesFactory) {

        m_newGameRule = a_rulesFactory.getNewGameRule();
        m_hitRule = a_rulesFactory.getHitRule();
        m_winRule = a_rulesFactory.getWinRule();
    
    /*for(Card c : m_deck.GetCards()) {
      c.show(true);
      System.out.println("" + c.getValue() + " of " + c.getColor());
    }    */
    }


    public boolean newGame(Player a_player) {
        if (m_deck == null || isGameOver()) {
            m_deck = new Deck();
            clearHand();
            a_player.clearHand();
            return m_newGameRule.newGame(m_deck, this, a_player);
        }
        return false;
    }

    public boolean hit(Player a_player) {
        if (m_deck != null && a_player.calcScore() < g_maxScore && !isGameOver()) {
            a_player.getNewCard(a_player, true);

            return true;
        }
        return false;
    }

    public boolean stand(Player a_player) {
        if (m_deck != null) {
            showHand();
            for (Card c : getHand()) {
                c.show(true);
            }
            while (m_hitRule.doHit(this)) {
                a_player.getNewCard(this, true);

            }
            return true;
        }
        return false;
    }

    public boolean isDealerWinner(Player a_player) {
        return m_winRule.isDealerWinner(a_player, this, g_maxScore);
    }

    public boolean isGameOver() {
        if (m_deck != null && m_hitRule.doHit(this) != true) {
            return true;
        }
        return false;
    }
}