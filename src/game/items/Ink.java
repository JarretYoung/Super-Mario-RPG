package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class Ink extends Item {
    private int damage;
    private int turnCounter;
    private int numTurnsLast;

    public Ink(){
        super("Ink", '`', false);
        this.turnCounter = 0;
        this.numTurnsLast = 5;
    }

    @Override
    public void tick(Location location) {
        if (this.turnCounter < numTurnsLast){
            this.turnCounter += 1;
            if(location.containsAnActor()){
                location.getActor().addCapability(Status.INK_ATTACK);
            }
        }
        else if (this.turnCounter == numTurnsLast){
            location.removeItem(this);
        }
    }
}
