package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Interface for all Buffable enemies
 *
 * @author Jastej Gill
 * @version 1.0 17/5/2022
 */
public interface Buffable {
    /** This is an accessor to get the damage points of an Actor
     *
     * @return an integer value of the damage points of an Actor
     */
    public int getDamage();
    /** This is a setter to add upon the current damage points of an Actor
     *
     * @param addedDamage as integer value of how many damage points to add upon the current
     */
    public void addDamage(int addedDamage);
    /**
     * This method is used to heal an Actor by a certain amount
     *
     * @param points is the number of health points to heal by
     */
    public void heal(int points);
    /**
     * This method is used to add the capability: Buffable to the Actor
     */
    public void makeBuffable();
}
