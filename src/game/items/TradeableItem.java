package game.items;

import game.actors.CurrencyCollector;

/**
 * Interface for all tradeable items
 */
public interface TradeableItem{
    /**
     *Interface methods to be implemented
     */
    int getValue();

    /** Mutator for tradeable item's value
     *
     * @param value is the value to replace the current value
     */
    void setValue(int value);

    /** Method that runs when TradeAction is executed
     *
     * @param customer is the customer that will purchase the new item
     * @return a string value of the result of traded() method
     */
    public String traded(CurrencyCollector customer);

    /**
     * Adds tradeable item to TradeManager
     */
    default void addToTradeManager(){
        TradeManager.getInstance().addTradeableItem(this);
    }


}
