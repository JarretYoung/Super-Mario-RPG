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
    public PowerStar() {
        super("Power Star", '*');
        this.addCapability(Status.INVINCIBLE);
        counter = 0;
    }

    @Override
    public String eatenFromGround(Actor by) {
        return super.eatenFromGround(by);
    }

    @Override
    public String eatenFromInventory(Actor by) {
        return super.eatenFromInventory(by);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        counter += 1;
        if(counter == 10) {
            if(currentLocation.getItems().contains(this))
                currentLocation.removeItem(this);
            else if(actor.getInventory().contains(this))
                actor.getInventory().remove(this);
        }
    }

    public int getCounter()
    {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
