package game.items;

import game.actors.CurrencyCollector;

/**
 * Interface for all tradeable items
 */
public interface Crippleable{
    /**
     *Interface methods to be implemented
     *
     * @return the chance for a weapon to cripple
     */
    int getChanceToCripple();

    /** Mutator for tradeable item's value
     *
     * @param chance out of 100% to cause crippling
     */
    void setChanceToCripple(int chance);

}

