package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

/**
 * Created by zack on 2017-10-20.
 */
public class Soft17Rule extends BasicHitStrategy {
    private final int g_hitLimit = 17;


    @Override
    public boolean doHit(Player a_dealer) {
        for (Card c : a_dealer.getHand()) {

            if (c.getValue() == Card.value.Ace && a_dealer.calcScore() == g_hitLimit) {
                return true;
            }
        }
        return super.doHit(a_dealer);

    }
}
