package game.actors.npc;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.GetItemAction;
import game.actors.npc.NPC;
import game.items.Bottle;
import game.items.TradeManager;
import game.actions.TalkAction;
import game.actions.TradeAction;
import game.items.TradeableItem;
import game.monologue.Monologue_Toad;

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
        this.addItemToInventory(new Bottle());
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
        if(otherActor.hasCapability(Status.BUFFABLE) && this.hasCapability(Status.HAS_BOTTLE) && !otherActor.hasCapability(Status.HAS_BOTTLE))
            actions.add(new GetItemAction(this.getBottle(), this));

        actions.add(new TalkAction(this, new Monologue_Toad()));
        return actions;
    }

    private Bottle getBottle(){
        for(int i =0; i < this.getInventory().size(); i++){
            if(this.getInventory().get(i).hasCapability(Status.HAS_BOTTLE))
                return (Bottle) this.getInventory().get(i);
        }
        return null;
    }
}
