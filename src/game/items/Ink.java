package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Ink class that extends abstract class Item
 * Capabilities: Is an item to be dropped on the ground to blind and hurts any actor who stands on it
 *
 * @author Jastej Gill
 * @version 1.0 20/5/2022
 */
public class Ink extends Item {
    /**
     * The damage done on first contact
     */
    private int damage;
    /**
     * Is the timer till the duration of the Ink lasts
     */
    private int turnCounter;
    /**
     * Is the duration of the Ink
     */
    private int numTurnsLast;

    /**
     *
     * @param damage
     * @param numTurnsLast
     */
    public Ink(int damage, int numTurnsLast) {
        super("Ink", '`', false);
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
                if(turnCounter == 1)
                    location.getActor().hurt(this.damage);
                location.getActor().addCapability(Status.INK_ATTACK);
            }
        }
        else if (this.turnCounter == numTurnsLast){
            location.removeItem(this);
        }
    }
}
