package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.actors.CurrencyCollector;

public class SpecialItem extends Item implements TradeableItem{
    private final CapabilitySet capabilitySet = new CapabilitySet();
    private int value;
    private String statusMessage = "";
    private Action consumeAction;
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public SpecialItem(String name, char displayChar) {
        super(name, displayChar, true);
        this.consumeAction = new ConsumeAction(this);
        addAction(consumeAction);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public boolean temporaryEffect() {
        return false;
    }

    public void removeConsumability() {
        this.removeAction(consumeAction);
    }



    @Override
    public String traded(CurrencyCollector customer) {
        CurrencyCollector actor = customer;
        String result = "";
        if(customer.getWallet().getBalance() >= this.getValue()) {
            result = actor + " " + "buys" + " " + this + " for " + this.getValue();
            customer.getWallet().removeBalance(this.getValue());
            actor.addItemToInventory(this);
        }
        else
            result = actor + " does not have sufficient fund for " + this;
        return result;
    }

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

}
