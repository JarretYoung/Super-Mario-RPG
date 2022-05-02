package game.nature;

import edu.monash.fit2099.engine.positions.Location;
import game.reset.Resettable;
import game.actors.enemies.Goomba;

import java.util.Random;

/**
 * Tree's first stage
 */
public class Sprout extends Tree implements Growable, Resettable {

    /**
     * chance for sprout to spawn goomba
     */
    private double spawnGoombaChance;
    /**
     * age reached to grow into next stage
     */
    private int maturityAge;

    /**
     * constructor for Sprout
     */
    public Sprout(){
        super ('+', "Sprout",90,10); // displayChar, 90% jumpSuccessRate, -10 damage points
        this.spawnGoombaChance = 0.1;
        this.maturityAge = 9;

    }

    /**
     * interface method by sprout, grow into sapling
     * @param location locaton
     */
    @Override
    public void grow(Location location) {
        location.setGround(new Sapling());
    }

    /**
     * sprout increase age each tick, spawns goomba
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        // increment Sprouts ages
        super.setAge(super.getAge() + 1);

        // grow when maturity age reached
        if (super.getAge() > this.maturityAge){
            grow(location);
        }

        //It has a 10% chance to spawn Goomba on its position in every turn. If any actor stands on it, it cannot spawn Goomba.
        if (!location.containsAnActor()){
            if (new Random().nextDouble() <= this.spawnGoombaChance){
                location.addActor(new Goomba());
            }
        }

    }


}
