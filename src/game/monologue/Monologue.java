package game.monologue;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import game.Status;

import java.util.ArrayList;
import java.util.Random;

/**This class represents the generic Monologue that is used to deliver speech lines
 *
 * @author Garret Yong Shern Min
 * @version 2.0 30/4/2022
 */
public abstract class Monologue {
    /**
     * Is the list of statements
     */
    private ArrayList<String> dialogue = new ArrayList<>();

    /**
     * Is the constructor of the Monologue class
     */
    public Monologue() {}

    /** This is the generic method to return a random statement from the selection of dialogue provided in the
     *  constructor based on certain requirements
     *
     * @param actor an Actor whom is the target the dialogue is meant to be directed at
     * @return a string statement from the constructor
     */
    public abstract String speak(Actor actor);

    /** A getter for the list of dialogue
     *
     * @return a ArrayList of all the dialogue put into the class
     */
    public ArrayList<String> getDialogue() {
        return dialogue;
    }
}
