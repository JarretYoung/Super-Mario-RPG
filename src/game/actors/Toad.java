package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.TradeManager;
import game.actions.TalkAction;
import game.actions.TradeAction;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.TradeableItem;
import game.items.Wrench;

public class Toad extends Actor {
    /**
     * Constructor.
     */
    private TradeManager tradeManager;
    public Toad() {
        super("Toad", '0', 10);
        this.addCapability(Status.NPC);
        this.tradeManager = TradeManager.getInstance();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
       ActionList actions = new ActionList();
       for(int i = 0; i < TradeManager.getInstance().getTradeableItems().size(); i++) {
           TradeableItem item = TradeManager.getInstance().getTradeableItems().get(i);
           actions.add(new TradeAction(this, item));
       }
       actions.add(new TalkAction(this));
       return actions;
    }
}
