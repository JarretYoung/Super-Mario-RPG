package game.nature;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

import game.actors.Goomba;
import game.actors.Koopa;
import game.surfaces.Dirt;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mature extends Tree implements Growable {

    private double witherChance;

    public Mature(){
        super ('T', "Mature Tree ",70,30);
        this.witherChance = 0.2;
    }

    @Override
    public void grow(Location location) {

        // grow a new sprout (+) in one of the surrounding fertile squares, randomly.
        // If there is no available fertile square, it will stop growing sprouts. At the moment, the only fertile ground is Dirt.
        // location.getExits()
        // location.setGround(new Sprout());
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

    @Override
    public void tick(Location location) {
        super.setAge(super.getAge() + 1);

        //It has a 15% chance to spawn Koopa in every turn. If an actor stands on it, it cannot spawn Koopa.
        if (!location.containsAnActor()){
            if (new Random().nextDouble() <= 0.15){
                location.addActor(new Koopa());
            }
        }


        //20% to wither and die (becomes Dirt) in every turn.
        if (new Random().nextDouble() <= this.witherChance  ){
            location.setGround(new Dirt());
        }

        if (super.getAge() > 5){
            super.setAge(0);
            grow(location);
        }




    }
}
