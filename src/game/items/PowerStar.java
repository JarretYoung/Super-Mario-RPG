package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * PowerStar class that extends abstract class SpecialItem
 * Capabilities: Makes consumer Invincible
 *
 * @author Jastej Gill
 * @version 2.0 30/4/2022
 */
public class PowerStar extends EatAbleItem {
    /**
     * Counter for number of turns that have passed
     */
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

    /**
     * get counter to count number of turns
     * @return integer of counter
     */
    public int getCounter()
    {
        return this.counter;
    }

    /**
     * Find remaining turns before power star is removed
     * @return integer 10 - counter
     */
    public int getRemainingTurns(){
        int max = 10;
        return max - getCounter();
    }

    /**
     * Reset the counter to zero
     */
    public void resetCounter() {
        this.counter = 0;
    }

    /**
     * Make the effect of power star temporary
     * @return true
     */
    @Override
    public boolean temporaryEffect() {
        return true;
    }

    /**
     * Method called when consume action is performed on power star while item is on the ground
     * Adds status Invincible to actor that eats it
     * @param by actor that eats special item
     * @return String to execute method of result of item being eaten
     */
    @Override
    public String eatenFromGround(Actor by) {
        this.addCapability(Status.INVINCIBLE);
        this.resetCounter();
        return super.eatenFromGround(by);
    }

    /**
     * Method called when consume action is performed on power star while item is in inventory
     * Adds status Invincible to actor that eats it
     * @param by actor that eats special item
     * @return String to execute method of result of item being eaten
     */
    @Override
    public String eatenFromInventory(Actor by) {
        Actor actor = by;

        this.addCapability(Status.INVINCIBLE);
        this.resetCounter();
        return super.eatenFromInventory(by);
    }

    /**
     * Inform a carried power star of the passage of time.
     *
     * This method is called once per turn, if the power star is being carried.
     * @param location The location of the actor carrying this power star.
     * @param actor The actor carrying this power star.
     */
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
            }
        }
    }

    /**
     * Inform a power star on the ground of the passage of time.
     * This method is called once per turn, if the power star rests upon the ground.
     * @param location The location of the ground on which we lie.
     */
    public void tick(Location location) {
        counter += 1;
        if(getCounter() == 10)
            location.removeItem(this);
    }


}
