package game.monologue;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

import java.util.Random;

/**This class represents the Monologue that is used by Toad to deliver speech lines to the player
 *
 * @author Garret Yong Shern Min
 * @version 2.0 30/4/2022
 */
public class Monologue_Toad extends Monologue{

    /**
     *  A constructor for the Monologue_Toad class
     *
     */
    public Monologue_Toad() {
        this.getDialogue().add("The Princess is depending on you! You are our only hope.");
        this.getDialogue().add("Being imprisoned in these walls can drive a fungus crazy :(");
        this.getDialogue().add("You might need a wrench to smash Koopa's hard shells.");
        this.getDialogue().add("You better get back to finding the Power Stars.");
    }

    /** This method returns a random statement from the selection of dialogue provided in the constructor based on the
     *  status of the actor whom is being spoken to
     *
     *  In this class there are 4 statements:
     *  1 : The Princess is depending on you! You are our only hope.
     *  2 : Being imprisoned in these walls can drive a fungus crazy :(
     *  3 : You might need a wrench to smash Koopa's hard shells.
     *  4 : You better get back to finding the Power Stars.
     *
     *  Statement 3 cannot be returned if the actor has a Wrench
     *  Statement 4 cannot be returned if the actor has consumed a Power Star
     *
     * @param actor an Actor whom is the target the dialogue is meant to be directed at
     * @return a string statement from the constructor based on certain requirements of the actor's current status
     */
    @Override
    public String speak(Actor actor) {
        Random rand = new Random();

        // If the actor has a Wrench and has consumed a Super Star, only output the 1st and 2nd statement
        if ((actor.hasCapability(Status.HAS_WRENCH)) && (actor.hasCapability(Status.INVINCIBLE))){
            return (this.getDialogue().get(rand.nextInt(2)));

        // If the actor has a Wrench only, only output the 1st, 2nd and 4th statement
        } else if (actor.hasCapability(Status.HAS_WRENCH)) {
            int i = rand.nextInt(4);
            while (i == 2) {
                i = rand.nextInt(4);
            }
            return (this.getDialogue().get(i));

        // If the actor has consumed a Super Star only, only output 1st, 2nd and 3rd statement
        } else if (actor.hasCapability(Status.INVINCIBLE)) {
            return (this.getDialogue().get(rand.nextInt(3)));

        // If the actor has no notable statuses, any of the 4 would be outputted
        } else {
            return (this.getDialogue().get(rand.nextInt(4)));

        }
    }
}
