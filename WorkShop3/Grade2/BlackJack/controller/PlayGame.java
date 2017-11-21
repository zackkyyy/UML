package BlackJack.controller;

import BlackJack.model.Game;
import BlackJack.model.IObserver;
import BlackJack.view.IView;
import BlackJack.view.IView.command;
public class PlayGame implements IObserver {
    //
    private IView a_view;
    private Game a_game;

    public PlayGame(Game a_game, IView a_view) {
        this.a_view = a_view;
        this.a_game = a_game;
        subscribe(this);

    }

    public boolean play() {

        a_view.displayWelcomeMessage();
        if (a_game.isGameOver()) {
            a_view.displayDealerHand(a_game.getDealerHand(), a_game.getDealerScore());
            a_view.displayPlayerHand(a_game.getPlayerHand(), a_game.getPlayerScore());
            a_view.displayGameOver(a_game.isDealerWinner());
        }


        command input = a_view.getCommand();
        switch (input) {
            case PLAY:
                a_game.newGame();
                break;
            case HIT:
                a_game.hit();
                break;
            case STAND:
                a_game.stand();
                break;
            default:
                break;
        }
        return input != command.QUIT;
    }



    @Override
    public void update() {


        try {
            Thread.sleep(1500);
            a_view.blankPage();
            a_view.displayDealerHand(a_game.getDealerHand(), a_game.getDealerScore());
            a_view.displayPlayerHand(a_game.getPlayerHand(), a_game.getPlayerScore());


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void subscribe(IObserver a_subscriber) {
        this.a_game.addSubscribers(a_subscriber);
    }
}