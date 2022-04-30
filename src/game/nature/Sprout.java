package game.nature;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

import java.util.Random;

public class Sprout extends Tree implements Growable{

    public Sprout(){
        super ('+', "Sprout",90,10); // displayChar, 90% jumpSuccessRate, -10 damage points


    }

    @Override
    public void grow(Location location) {
        location.setGround(new Sapling());
    }

    @Override
    public void tick(Location location) {

        // Sprout ages
        super.setAge(super.getAge() + 1);

        if (super.getAge() > 9){
            grow(location);
        }

        //It has a 10% chance to spawn Goomba on its position in every turn. If any actor stands on it, it cannot spawn Goomba.
        if (!location.containsAnActor()){
            if (new Random().nextDouble() <= 0.1){
                location.addActor(new Goomba());
            }
        }

    }


}
