package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public interface TradeableItem{
    int getValue();

    int setValue();

    String traded(Actor actor, TradeableItem item, int value);
}
