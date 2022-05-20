package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.surfaces.Dirt;

public class Fire extends Item {
    private int damage;
    private int turnCounter;
    private int numTurnsLast;

    public Fire(){
        super("Fire", 'v', false);
        this.damage = 20;
        this.turnCounter = 0;
        this.numTurnsLast = 3;
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
