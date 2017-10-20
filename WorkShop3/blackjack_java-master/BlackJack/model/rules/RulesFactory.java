package BlackJack.model.rules;

public class RulesFactory {

  public IHitStrategy getHitRule() {
     return new Soft17Rule();
    //return new BasicHitStrategy();
  }

  public INewGameStrategy getNewGameRule() {
    return new AmericanNewGameStrategy();
  }

  public IWinStrategy getWinRule(){
    return new BasicWinStrategy();
  }
}