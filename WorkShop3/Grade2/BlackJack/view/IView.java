package BlackJack.view;

public interface IView {
    enum command{PLAY, HIT, STAND, INVALID, QUIT}
    void displayWelcomeMessage();

    int getInput();
    command getCommand();
    void displayCard(BlackJack.model.Card a_card);

    void displayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);

    void displayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);

    void displayGameOver(boolean a_dealerIsWinner);

    void blankPage();
}