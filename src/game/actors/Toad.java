package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TradeAction;
import game.items.TradeableItem;

public class Toad extends Actor {
    /**
     * Constructor.
     */
    public Toad() {
        super("Toad", '0', 10);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
       ActionList actions = new ActionList();
       actions.add(new TradeAction(this));
       return actions;
    }
}
