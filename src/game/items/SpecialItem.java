package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

public class SpecialItem extends Item implements TradeableItem{
    private final CapabilitySet capabilitySet = new CapabilitySet();
    private int value;
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public SpecialItem(String name, char displayChar) {
        super(name, displayChar, true);
        addAction(new ConsumeAction(this));
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String traded(Actor actor) {
        return null;
    }

    public String eatenFromGround(Actor by) {
        Actor actor = by;
        String result = actor + " " + "eats" + " " + this + " for ";
            // Add item capability to actor
            for(int i = 0; i < this.capabilitiesList().size(); i++)
            {
                actor.addCapability(this.capabilitiesList().get(i));
                result += this.capabilitiesList().get(i) + " ";
            }
        return result;
    }

    public String eatenFromInventory(Actor by) {
        Actor actor = by;
        String result = actor + " " + "eats" + " " + this + " for ";
        if(actor.getInventory().contains(this)){
            // Add item capability to actor
            for(int i = 0; i < this.capabilitiesList().size(); i++)
            {
                actor.addCapability(this.capabilitiesList().get(i));
                result += this.capabilitiesList().get(i) + " ";
            }
            // Remove item from inventory
            actor.getInventory().remove(this);
        }
        else  {
            result = "Item not in inventory";
        }
        return result;
    }

}
