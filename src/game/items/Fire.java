package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;


public class Fire extends Item {
    private int damage;
    private int turnCounter;
    private int numTurnsLast;

    public Fire(int damage, int numTurnsLast){
        super("Fire", 'v', false);
        this.damage = damage;
        this.turnCounter = 0;
        this.numTurnsLast = numTurnsLast;
    }

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

