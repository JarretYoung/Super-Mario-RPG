package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.CurrencyCollector;

/**
 * Interface for all tradeable items
 * @author Jastej Gill
 * @version 2.0 30/4/2022
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
     * Adds tradeable item to TradeManager
     */
    default void addToTradeManager(){
        TradeManager.getInstance().addTradeableItem(this);
    }

    /**
     * Traded method called when trade action is performed on special item
     * @param customer actor that purchases special item
     * @return String to execute method of result of item being traded
     */
    public String traded(CurrencyCollector customer);


}
