package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.TradeManager;
import game.items.TradeableItem;

public class TradeAction extends Action {
    private Actor target;
    private TradeableItem item;

    public TradeAction(Actor target, TradeableItem item) {
        this.target = target;
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return item.traded(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
           return actor + " buys " + item + " for " +item.getValue() + "$";
    }
}
