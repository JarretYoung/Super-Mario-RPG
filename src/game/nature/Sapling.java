package game.nature;

import edu.monash.fit2099.engine.positions.Location;
import game.currency.Coin;

import java.util.Random;

public class Sapling extends Tree implements Growable{
    private double spawnCoinChance;
    private int spawnCoinValue;
    private int maturityAge;
    public Sapling(){

        super ('t', "Sapling", 80,20 );
        this.spawnCoinChance = 0.1;
        this.spawnCoinValue = 20;
        this.maturityAge = 9;
    }

    @Override
    public void grow(Location location) {
        location.setGround(new Mature());
    }

    @Override
    public void tick(Location location) {
        super.setAge(super.getAge() + 1);

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
