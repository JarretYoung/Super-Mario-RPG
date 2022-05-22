package game.items;

import game.actors.CurrencyCollector;

/**
 * Interface for all cripplable items
 *
 * @author Garret Yong Shern Min
 * @version 1.0 13/5/2022
 */
public interface Crippleable{
    /**
     * Getter for the change of a weapon to cripple
     *
     * @return the chance for a weapon to cripple
     */
    int getChanceToCripple();

    /** Mutator for the chance of a weapon to cripple
     *
     * @param chance out of 100% to cause crippling
     */
    void setChanceToCripple(int chance);

}

