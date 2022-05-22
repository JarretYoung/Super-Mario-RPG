package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Fire class that extends abstract class Item
 * Capabilities: Is an item to be dropped on the ground to burn any actor who stands on it
 *
 * @author Lup Hoong, Garret Yong Shern Min
 * @version 2.0 20/5/2022
 */
public class Fire extends Item {
    /**
     * Is the damage of this instance of Fire
     */
    private int damage;
    /**
     * Is the timer till the duration of the Fire lasts
     */
    private int turnCounter;
    /**
     * Is the duration of the Fire
     */
    private int numTurnsLast;

    /** Constructor for the Fire class
     *
     * @param damage is the damage that this class of the Fire deals when an Actor is on it
     * @param numTurnsLast is the duration of the Fire
     */
    public Fire(int damage, int numTurnsLast){
        super("Fire", 'v', false);
        this.damage = damage;
        this.turnCounter = 0;
        this.numTurnsLast = numTurnsLast;
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param location The location of the ground on which we lie.
     */
    @Override
    public void tick(Location location) {

        if (this.turnCounter < numTurnsLast){
            this.turnCounter += 1;
            if(location.containsAnActor()){
                location.getActor().hurt(this.damage);
            }
        }
        else if (this.turnCounter == numTurnsLast){
            location.removeItem(this);
        }
    }
}

