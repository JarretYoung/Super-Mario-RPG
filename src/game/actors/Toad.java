package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.TradeManager;
import game.actions.TalkAction;
import game.actions.TradeAction;
import game.items.TradeableItem;
/**Class of  a Toad that extends NPC class
 *
 * @author Jastej Gill
 * @version 2.0 30/4/2022
 *
 */
public class Toad extends NPC {
    /**
     * Trade manager for Toad
     */
    private TradeManager tradeManager;

    /**
     * Constructor for the Toad class
     */
    public Toad() {
        super("Toad", '0');
        this.tradeManager = TradeManager.getInstance();
    }


    @Override
    /**
     * returns trade action if actor can trade or a do nothing action
     * @param actor the Actor buying
     * @param location the current Location
     * @param direction the direction of the toad from the Actor
     * @return trade action or do nothing action
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        for(int i = 0; i < TradeManager.getInstance().getTradeableItems().size(); i++) {
           TradeableItem item = TradeManager.getInstance().getTradeableItems().get(i);
           actions.add(new TradeAction(item));
        }

        actions.add(new TalkAction(this));
        return actions;
    }
}
