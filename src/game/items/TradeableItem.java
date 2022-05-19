package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.CurrencyCollector;

/**
 * Interface for all tradeable items
 */
public interface TradeableItem{
    /**
     *Interface methods to be implemented
     *
     * @return the value of the Tradeable item
     */
    int getValue();

    /** Mutator for tradeable item's value
     *
     * @param value is the value to replace the current value
     */
    void setValue(int value);

    /**
     * Traded method called when trade action is performed on special item
     * @param customer actor that purchases special item
     * @return String to execute method of result of item being traded
     */
    public default String traded(CurrencyCollector customer) {
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

    /**
     * Adds tradeable item to TradeManager
     */
    default void addToTradeManager(){
        TradeManager.getInstance().addTradeableItem(this);
    }


}
