package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.ConsumeFromGroundAction;
import game.actions.ConsumeFromInventoryAction;
import game.surfaces.Dirt;

public class SpecialItem extends Item implements TradeableItem{
    private final CapabilitySet capabilitySet = new CapabilitySet();
    private int value;
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public SpecialItem(String name, char displayChar, Actor actor, Location location, String direction) {
        super(name, displayChar, true);
        this.addAction(this.getPickUpAction(actor));
        this.addAction(new ConsumeFromGroundAction(this, location, direction));

        if(actor.getInventory().contains(this))
            this.addAction(new ConsumeFromInventoryAction(this, location, direction));
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

    public String eatenFromGround(Actor by, Location at) {
        Actor actor = by;
        Location location = at;
        String result = actor + " " + "eats" + " " + this + " for ";
            // Add item capability to actor
            for(int i = 0; i < this.capabilitiesList().size(); i++)
            {
                actor.addCapability(this.capabilitiesList().get(i));
                result += this.capabilitiesList().get(i) + " ";
            }
            // Remove item from ground
        location.setGround(new Dirt());
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
