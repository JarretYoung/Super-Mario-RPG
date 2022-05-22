package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.EatAction;
import game.actions.DrinkAction;

/**
 * This class is the generic class for all items that can be consumed
 *
 * @author Jastej Gill
 * @version 1.0 18/5/2022
 */
public abstract class EatAbleItem extends SpecialItem {
    /**
     * Message returned when item is eaten
     */
    private String statusMessage = "";

    /**
     * Action to consume special item
     */
    private Action consumeAction;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public EatAbleItem(String name, char displayChar) {
        super(name, displayChar, true);

        setConsumeAction(new EatAction(this));
        addAction(getConsumeAction());
    }

    /** Accessor for the consumeAction
     *
     * @return the consumeAction
     */
    public Action getConsumeAction() {
        return consumeAction;
    }

    /** Setter for the consumeAction
     *
     * @param consumeAction the action to consume a special item
     */
    public void setConsumeAction(Action consumeAction) {
        this.consumeAction = consumeAction;
    }

    /**
     * Accessor for status message of special item
     * @return string of updated status of actor after consuming special item
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Mutator for status message of special item
     *
     * @param statusMessage is the message for the status applied to an Actor
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * Set special item's effect is temporary or permanent
     * @return false
     */
    public boolean temporaryEffect() {
        return false;
    }

    /**
     * Remove ability to consume special item
     */
    public void removeConsumability() {
        this.removeAction(consumeAction);
    }

    /**
     * Method called when consume action is performed on special item while item is on the ground
     * @param by actor that eats special item
     * @return String to execute method of result of item being eaten
     */
    public String eatenFromGround(Actor by) {
        Actor actor = by;
        String result = this.getStatusMessage();
        // Add item capability to actor
        for(int i = 0; i < this.capabilitiesList().size(); i++)
        {
            actor.addCapability(this.capabilitiesList().get(i));
        }
        if(temporaryEffect()) {
            actor.addItemToInventory(this);
            this.togglePortability();
            this.removeConsumability();
        }
        return result;
    }


    /**
     * Method called when consume action is performed on special item while item is in inventory
     * @param by actor that eats special item
     * @return String to execute method of result of item being eaten
     */
    public String eatenFromInventory(Actor by) {
        Actor actor = by;
        String result = this.getStatusMessage();
        if(actor.getInventory().contains(this)){
            // Add item capability to actor
            for(int i = 0; i < this.capabilitiesList().size(); i++)
            {
                actor.addCapability(this.capabilitiesList().get(i));
            }
            // Remove item from inventory
            if(!this.temporaryEffect())
                actor.removeItemFromInventory(this);

            else if(temporaryEffect()) {
                this.togglePortability();
                this.removeConsumability();
            }
        }
        else  {
            result = "Item not in inventory";
        }
        return result;
    }
}
