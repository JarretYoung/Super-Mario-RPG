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

    void setValue(int value);

    public String traded(CurrencyCollector customer);

    /**
     * Adds tradeable item to TradeManager
     */
    default void addToTradeManager(){
        TradeManager.getInstance().addTradeableItem(this);
    }


}
