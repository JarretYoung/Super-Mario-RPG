package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Syringe class that extends abstract class SpecialItem
 * Capabilities: Makes consumer heal over a period of time
 *
 * @author Garret Yong Shern Min
 * @version 1.0 20/5/2022
 */
public class Syringe extends EatAbleItem {

    /**
     * The amount that which the Syringe would heal the actor per turn
     */
    private int healing_per_turn;
    /**
     * Counter for number of turns that have passed
     */
    private int counter;

    /***
     * Constructor.
     */
    public Syringe() {
        super("Syringe", '%');
        counter = 0;
        this.setHealing_per_turn(10);
        this.setValue(300);
        this.setStatusMessage("The pain is dulling");
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
        this.addCapability(Status.HEAL_OVER_TIME);
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

        this.addCapability(Status.HEAL_OVER_TIME);
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
        if(actor.hasCapability(Status.HEAL_OVER_TIME)) {
            counter += 1;
            actor.heal(this.getHealing_per_turn());
            System.out.println(actor + " consumed Syringe - " + getRemainingTurns() + " turns remaining");
            if (getCounter() == 10) {
                if (actor.getInventory().contains(this))
                    actor.removeItemFromInventory(this);
                else if (actor.hasCapability(Status.HEAL_OVER_TIME)) {
                    actor.removeCapability(Status.HEAL_OVER_TIME);
                    actor.getInventory().remove(this);
                }
            }
        }
    }

    /** This method is a getter to obtain the healing to be delivered per turn
     *
     * @return the integer value of healing provided per turn
     */
    public int getHealing_per_turn() {
        return healing_per_turn;
    }

    /** This method is used to set the healing to be delivered per turn
     *
     * @param healing_per_turn is the integer value that the healing provided per turn to be set at
     */
    public void setHealing_per_turn(int healing_per_turn) {
        this.healing_per_turn = healing_per_turn;
    }
}