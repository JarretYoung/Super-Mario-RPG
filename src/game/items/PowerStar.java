package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;

public class PowerStar extends SpecialItem {
    private int counter;
    /***
     * Constructor.
     */
    public PowerStar() {
        super("Power Star", '*');
        counter = 0;
        this.setValue(600);
        this.setStatusMessage("Mario is INVINCIBLE!");
        this.addToTradeManager();
    }

    public int getCounter()
    {
        return this.counter;
    }

    public int getRemainingTurns(){
        int max = 10;
        return max - getCounter();
    }

    public void resetCounter() {
        this.counter = 0;
    }

    @Override
    public boolean temporaryEffect() {
        return true;
    }

    @Override
    public String eatenFromGround(Actor by) {
        this.addCapability(Status.INVINCIBLE);
        this.resetCounter();
        return super.eatenFromGround(by);
    }

    @Override
    public String eatenFromInventory(Actor by) {
        Actor actor = by;

        this.addCapability(Status.INVINCIBLE);
        this.resetCounter();
        return super.eatenFromInventory(by);
    }

    @Override
    public void tick(Location location, Actor actor) {
        counter += 1;
        if(actor.hasCapability(Status.INVINCIBLE))
            System.out.println(actor + " consumed Power Star - " + getRemainingTurns() + " turns remaining");
        if(getCounter() == 10) {
            if(actor.getInventory().contains(this))
                actor.removeItemFromInventory(this);
            else if(actor.hasCapability(Status.INVINCIBLE)) {
                actor.removeCapability(Status.INVINCIBLE);
                actor.getInventory().remove(this);
            } else {
                location.removeItem(this);
            }
        }
    }


}
