package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.EatAction;
import game.actions.DrinkAction;
import game.actors.Buffable;
import game.items.water.MagicalWater;

public abstract class ConsumableItem extends Item {
    private boolean isLiquid;

    public void setLiquid(boolean liquid) {
        isLiquid = liquid;
    }

    public boolean getIsLIquid(){
        return  isLiquid;
    }

    /**
     * Message returned when item is eaten
     */
    private String statusMessage = "";

    /**
     * ConsumeAction for consuming items
     */
    private Action consumeAction;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public ConsumableItem(String name, char displayChar, boolean portable, boolean isLiquid) {
        super(name, displayChar, portable);
        if(isLiquid){
            if(this instanceof MagicalWater)
                consumeAction = new DrinkAction((MagicalWater) this);
            this.addAction(consumeAction);
        }
        else if(!isLiquid){
            consumeAction = new EatAction(this);
            this.addAction(consumeAction);
        }
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
                actor.getInventory().remove(this);

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

    public String drinked(Buffable by){
        return null;
    }

}
