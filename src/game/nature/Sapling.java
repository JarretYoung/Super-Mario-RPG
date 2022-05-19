package game.nature;

import edu.monash.fit2099.engine.positions.Location;
import game.currency.Coin;
import game.reset.Resettable;

import java.util.Random;

/**
 * Tree's second stage
 */
public class Sapling extends Tree implements Growable, Resettable {
    /**
     * sapling chance to spawn coin
     */
    private double spawnCoinChance;
    /**
     * sapling spawned coin value
     */
    private int spawnCoinValue;
    /**
     * sapling maturity age to grow to next tree stage
     */
    private int maturityAge;

    /**
     * constructor
     */
    public Sapling(){

        super ('t', "Sapling", 80,20 );
        this.spawnCoinChance = 0.1;
        this.spawnCoinValue = 20;
        this.maturityAge = 9;
    }
    /**
     * interface method by sprout
     * @param location locaton
     */
    @Override
    public void grow(Location location) {
        location.setGround(new Mature());
    }

    /**
     * Sapling age, spawn coin eahc tick
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.setAge(super.getAge() + 1);
        super.tick(location);

        // grow every 10 turns
        if (super.getAge() > this.maturityAge){
            grow(location);
        }


        // has a 10% chance to produce/spawn a coin ($20) on its location at every turn.
        if (new Random().nextDouble() <= this.spawnCoinChance){
            location.addItem(new Coin(this.spawnCoinValue));
        }
    }
}
