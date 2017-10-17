package BlackJack.model.rules;

import BlackJack.model.Player;

/**
 * Created by zack on 2017-10-17.
 */
public class BasicWinStrategy implements IWinStrategy {


    @Override
    public boolean isDealerWinner(Player a_player, Player a_dealer, int g_maxScore) {
        if (a_player.calcScore() > g_maxScore){
            return true;
        }else if (a_dealer.calcScore() > g_maxScore) {
            return false;
        }
        return a_dealer.calcScore() >= a_player.calcScore();
    }

    @Override
    public boolean isPlayerWinner(Player a_player, Player a_dealer, int g_maxScore) {
       if (a_player.calcScore() > g_maxScore){
            return true;
        }else if (a_dealer.calcScore() > g_maxScore){
            return false;
        }
        return a_dealer.calcScore() > a_player.calcScore();

    }

}
