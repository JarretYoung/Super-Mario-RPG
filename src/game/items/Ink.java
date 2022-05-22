package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Ink extends Item {
    private int damage;
    private int turnCounter;
    private int numTurnsLast;

    /***
     * Constructor.
     */
    public Ink(int damage, int numTurnsLast) {
        super("Ink", '`', false);
        this.damage = damage;
        this.turnCounter = 0;
        this.numTurnsLast = numTurnsLast;
    }

    @Override
    public void tick(Location location) {
        if (this.turnCounter < numTurnsLast){
            this.turnCounter += 1;
            if(location.containsAnActor()){
                if(turnCounter == 1)
                    location.getActor().hurt(this.damage);
                location.getActor().addCapability(Status.INK_ATTACK);
            }
        }
        else if (this.turnCounter == numTurnsLast){
            location.removeItem(this);
        }
    }
}
