package game.items;


import edu.monash.fit2099.engine.items.Item;
import game.actors.CurrencyCollector;

/**Class of Special items that implements interface TradeableItem
 * Items that give the consumer special capabilities
 * @author Jastej Gill
 * @version 2.0 29/4/2022
 *
 */
public abstract class SpecialItem extends Item implements TradeableItem{
    /**
     * Value of special item
     */
    private int value;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public SpecialItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Accessor for value of special item
     * @return integer value of special item
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Mutator for value of special item
     */
    @Override
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Traded method called when trade action is performed on special item
     * @param customer actor that purchases special item
     * @return String to execute method of result of item being traded
     */
    public String traded(CurrencyCollector customer) {
        CurrencyCollector actor = customer;
        String result = "";
        if(customer.getWallet().getBalance() >= this.getValue()) {
            result = actor + " " + "buys" + " " + this + " for " + this.getValue();
            customer.getWallet().removeBalance(this.getValue());
            actor.addItemToInventory((Item) this);
        }
        else
            result = actor + " does not have sufficient fund for " + this;
        return result;
    }


}
