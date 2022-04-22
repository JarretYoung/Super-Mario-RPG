package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.items.Item;

public class SpecialItem extends Item implements TradeableItem{
    private final CapabilitySet capabilitySet = new CapabilitySet();
    private int value;
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public SpecialItem(String name, char displayChar, Actor actor) {
        super(name, displayChar, true);
        this.addAction(this.getPickUpAction(actor));
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
}
