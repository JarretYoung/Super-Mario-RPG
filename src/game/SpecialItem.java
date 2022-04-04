package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;

import java.util.List;

public class SpecialItem extends Item {
    private String name;
    private char displayChar;
    private ActionList allowableActions;
    private final CapabilitySet capabilitySet = new CapabilitySet();
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public SpecialItem(String name, char displayChar, Actor actor) {
        super(name, displayChar, true);
        this.addAction(this.getPickUpAction(actor));
    }

    /**
     * Create and return an action to pick this Item up.
     * If this Item is not portable, returns null.
     *
     * @return a new PickUpItemAction if this Item is portable, null otherwise.
     */
    public PickUpItemAction getPickUpAction(Actor actor) {
            return new PickUpItemAction(this);
    }

    @Override
    public char getDisplayChar() {
        return displayChar;
    }

    /**
     * Getter.
     *
     * Returns an unmodifiable copy of the actions list so that calling methods won't
     * be able to change what this Item can do without the Item checking.
     * @return an unmodifiable list of Actions
     */
    public List<Action> getAllowableActions() {
        return allowableActions.getUnmodifiableActionList();
    }

    /**
     * Does this Item have the given Capability?
     *
     * @return true if and only if is Item has the given Capability
     */
    public boolean hasCapability(Enum<?> capability) {
        return capabilitySet.hasCapability(capability);
    }

    /**
     * Add a Capability to this Item.
     *
     * @param capability the Capability to add
     */
    public void addCapability(Enum<?> capability) {
        capabilitySet.addCapability(capability);
    }


    /**
     * Remove a Capability from this Item.
     *
     * @param capability the Capability to remove
     */
    public void removeCapability(Enum<?> capability) {
        capabilitySet.removeCapability(capability);
    }

}
