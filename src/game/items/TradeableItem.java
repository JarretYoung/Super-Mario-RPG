package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.TradeManager;

public interface TradeableItem{
    int getValue();

    void setValue(int value);

    default void addToTradeManager(){
        TradeManager.getInstance().addTradeableItem(this);
    }

    String traded(Actor actor);
}
