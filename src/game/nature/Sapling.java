package game.nature;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

import java.util.Random;

public class Sapling extends Tree implements Growable{
    public Sapling(){
        super ('t', "Sapling", 80,20 );
    }

    @Override
    public void grow(Location location) {
        location.setGround(new Mature());
    }

    @Override
    public void tick(Location location) {
        super.setAge(super.getAge() + 1);

        // grow every 10 turns
        if (super.getAge() > 9){
            grow(location);
        }


        // has a 10% chance to produce/spawn a coin ($20) on its location at every turn.
        if (new Random().nextDouble() <= 0.1){
            location.addItem(new Coin(20));
        }
    }
}
