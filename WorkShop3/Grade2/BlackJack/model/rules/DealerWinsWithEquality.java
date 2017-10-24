package BlackJack.model.rules;

import BlackJack.model.Player;

/**
 * Created by zack on 2017-10-17.
 */
public class DealerWinsWithEquality implements IWinStrategy {
    /**
     * this strategy will make the dealer is the winner in case they have the same score
     */

    @Override
    public boolean isDealerWinner(Player a_player, Player a_dealer, int g_maxScore) {
        if (a_player.calcScore() > g_maxScore && a_dealer.calcScore() <= g_maxScore) {
            return true;
        } else if  (a_player.calcScore() > g_maxScore && a_dealer.calcScore() > g_maxScore && a_dealer.calcScore() <= a_player.calcScore() ){
            return true;
        }else if (a_player.calcScore() < g_maxScore && a_dealer.calcScore() < g_maxScore && a_dealer.calcScore() >= a_player.calcScore()){
            return true;
        }else
            return false;
    }
}