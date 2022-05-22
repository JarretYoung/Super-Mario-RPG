package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Fire item is sometimes dropped when an actor attacks.
 *
 * @author Clarissa Low Lup Hoong
 * @version 1.0 20/5/2022
 */
public class Fire extends Item {
    /**
     * Damage done to actor when actor steps on Fire
     */
    private int damage;
    /**
     * counts number of turns already on the ground
     */
    private int turnCounter;
    /**
     * Number of turns Fire item lasts on the ground
     */
    private int numTurnsLast;

    /**
     * Constructor
     * @param damage Damage done to actor, if actor steps on it
     * @param numTurnsLast Number of turns Fire Item will be left on the ground
     */
    public Fire(int damage, int numTurnsLast){
        super("Fire", 'v', false);
        this.damage = damage;
        this.turnCounter = 0;
        this.numTurnsLast = numTurnsLast;
    }

    /**
     * Each turn, check if fire should be still on ground.
     * If not, remove Fire item
     * @param location location of Fire item
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

