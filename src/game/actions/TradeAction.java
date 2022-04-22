package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.TradeableItem;

public class TradeAction extends Action {
    private Actor target;
    private TradeableItem item;
    private Location traderLocation;

    public TradeAction(Actor target) {
        this.target = target;
        //this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return item.traded(actor, item, item.getValue());
    }

    @Override
    public String menuDescription(Actor actor) {
       return actor + " buys " + item + " for " + item.getValue() + "$";
    }
}