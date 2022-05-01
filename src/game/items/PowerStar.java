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
        counter = 0;
        this.setValue(600);
        this.setStatusMessage("Mario is INVINCIBLE!");
        this.addToTradeManager();
    }

    @Override
    public boolean temporaryEffect() {
        return true;
    }

    @Override
    public String eatenFromGround(Actor by) {
        this.addCapability(Status.INVINCIBLE);
        return super.eatenFromGround(by);
    }

    @Override
    public String eatenFromInventory(Actor by) {
        Actor actor = by;

        this.addCapability(Status.INVINCIBLE);
        this.setCounter(0);
        return super.eatenFromInventory(by);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        counter += 1;
        if(actor.hasCapability(Status.INVINCIBLE))
            System.out.println(actor + " consumes Power Star - " + getCounter() + " turns remaining");
        if(counter == 10) {
            if(currentLocation.getItems().contains(this))
                currentLocation.removeItem(this);
            else if(actor.getInventory().contains(this))
                actor.getInventory().remove(this);
            else if(actor.hasCapability(Status.INVINCIBLE))
                actor.removeCapability(Status.INVINCIBLE);
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
