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
        this.turnCounter += 1;
        if (this.turnCounter <3){
            if(location.containsAnActor()){
                location.getActor().hurt(this.damage);
            }
        }
        else{
            location.removeItem(this);
        }
    }
}

