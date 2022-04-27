package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
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
    public Toad() {
        super("Toad", '0', 10);
        this.addCapability(Status.NPC);
        this.addItemToInventory(new Wrench());
        this.addItemToInventory(new SuperMushroom());
        this.addItemToInventory(new PowerStar());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(!this.itemPresentCheck(Wrench()))
            this.addItemToInventory(new Wrench());
        return null;
    }

    public boolean itemPresentCheck(TradeableItem item){
        boolean flag;
        flag = this.getInventory().contains(item);
        return flag;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
       ActionList actions = new ActionList();
       actions.add(new TradeAction(this));
       actions.add(new TalkAction(otherActor));
       return actions;
    }
}
