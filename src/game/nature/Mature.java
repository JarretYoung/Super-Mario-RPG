package game.nature;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

import game.actors.enemies.DefaultKoopa;
import game.actors.enemies.FlyingKoopa;
import game.reset.Resettable;
import game.actors.enemies.Koopa;
import game.surfaces.Dirt;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tree's third stage
 */
public class Mature extends Tree implements Growable, Resettable {

    /**
     * mature tree chance to wither each tick
     */
    private double witherChance;
    /**
     * mature tree chance to spawn koopa each tick
     */
    private double spawnKoopaChance;

    /**
     * constructor
     */
    public Mature(){
        super ('T', "Mature Tree ",70,30);
        this.witherChance = 0.2;
        this.spawnKoopaChance = 0.15;
    }

    /**
     * interface implementation of Mature tree: withers will turn into dirt,
     * every 5 turn spawn sprout in surrounding
     * @param location is the location of this instance of Mature class
     */
    @Override
    public void grow(Location location) {

        // grow one new sprout (+) in one of the surrounding fertile squares, randomly.
        // If there is no available fertile square, it will stop growing sprouts. At the moment, the only fertile ground is Dirt.
        List<Exit> dirtExits = new ArrayList<>();

        // add exits which are Dirt to dirtExit
        for (Exit exit: location.getExits()){
            if (exit.getDestination().getGround().getDisplayChar() == '.'){
                dirtExits.add(exit);
            }
        }
        // randomly grow spouts at one of the dirt exits
        if (dirtExits != null){
            dirtExits.get(new Random().nextInt(dirtExits.size())).getDestination().setGround(new Sprout());
        }


    }

    /**
     * Mature tree age, might spawn koopa, and grow
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        super.setAge(super.getAge() + 1);

        //It has a 15% chance to spawn Koopa in every turn. If an actor stands on it, it cannot spawn Koopa.
        if (!location.containsAnActor()){
            if (new Random().nextDouble() <= this.spawnKoopaChance){
                if ( new Random().nextDouble() <= 0.5 ) {
                    location.addActor(new DefaultKoopa());
                } else {
                    location.addActor(new FlyingKoopa());
                }

            }
        }

        //20% to wither and die (becomes Dirt) in every turn.
        if (new Random().nextDouble() <= this.witherChance  ){
            location.setGround(new Dirt());
        }

        // every 5 turns, grow sprouts on random squares around it
        if (super.getAge() > 5){
            super.setAge(0);
            grow(location);
        }




    }
}
