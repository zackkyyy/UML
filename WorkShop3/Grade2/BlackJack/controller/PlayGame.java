package BlackJack.controller;

import BlackJack.model.Game;
import BlackJack.model.IObserver;
import BlackJack.view.IView;

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
        //  a_view.displayDealerHand(a_game.getDealerHand(), a_game.getDealerScore());
        // a_view.displayPlayerHand(a_game.getPlayerHand(), a_game.getPlayerScore());


        int input = a_view.getInput();

        if (input == 'p') {
            a_game.newGame();
        } else if (input == 'h') {
            a_game.hit();
        } else if (input == 's') {
            a_game.stand();
        }

        return input != 'q';
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