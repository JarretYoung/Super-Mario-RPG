package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.security.DrbgParameters;

public class Consume extends Action {
    protected Item itemConsumed;

    protected Actor consumer;

    public Consume(Item item, Actor actor)
    {
        this.itemConsumed = item;
        this.consumer = actor;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = actor + " " + "eats" + " " + itemConsumed + " for ";
        if(actor.getInventory().contains(itemConsumed)){
            // Add item capability to actor
            for(int i = 0; i < itemConsumed.capabilitiesList().size(); i++)
            {
                actor.addCapability(itemConsumed.capabilitiesList().get(i));
                result += itemConsumed.capabilitiesList().get(i) + " ";
            }
            // Remove item from inventory
            actor.getInventory().remove(itemConsumed);
        }
        else  {
            result = "Item not in inventory";
        }
        return result;
    }

    public String menuDescription(Actor actor) {
        return actor + " eats " + itemConsumed;
    }
}
