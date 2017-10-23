package BlackJack.model.rules;

import BlackJack.model.Player;

/**
 * Created by zack on 2017-10-23.
 */
public class PlayerWinsWithEquality implements IWinStrategy {
    /**
    this strategy will make the player is the winner in case they have the same score
     */
    @Override
    public boolean isDealerWinner(Player a_player, Player a_dealer , int g_maxScore) {
        if (a_player.calcScore() > g_maxScore && a_dealer.calcScore() < g_maxScore) {
            return true;
        }
        return a_dealer.calcScore() > a_player.calcScore();

    }


}

