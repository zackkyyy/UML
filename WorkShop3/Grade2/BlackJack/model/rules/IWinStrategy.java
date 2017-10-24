package BlackJack.model.rules;

import BlackJack.model.Player;

/**
 * Created by zack on 2017-10-17.
 */
public interface IWinStrategy {
    boolean isDealerWinner(Player a_player, Player a_dealer, int g_maxScore);

}
