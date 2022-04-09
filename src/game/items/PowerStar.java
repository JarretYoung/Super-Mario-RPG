package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class PowerStar extends SpecialItem {
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param actor
     */
    private int counter;
    private Actor actor;
    public PowerStar(String name, char displayChar) {
        super("Power Star", '*');
        this.addCapability(Status.INVINCIBLE);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        counter++;
    }

    public int getCounter()
    {
        return this.counter;
    }
}
