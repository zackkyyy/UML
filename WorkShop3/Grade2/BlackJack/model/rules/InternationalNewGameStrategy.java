package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Deck;
import BlackJack.model.Player;

class InternationalNewGameStrategy implements INewGameStrategy {

  public boolean newGame(Deck a_deck, Dealer a_dealer, Player a_player) {

    a_player.getNewCard(a_player,true);
    a_player.getNewCard(a_dealer,true);
    a_player.getNewCard(a_player,true);

    return true;
  }
}