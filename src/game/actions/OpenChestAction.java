package game.actions;

import edu.monash.fit2099.demo.mars.grounds.Floor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.surfaces.Chest;

/**
 * OpenChestAction class that extends abstract class Action class
 * Usage: Is used to open a chest
 *
 * @author Garret Yong Shern Min
 * @version 1.0 18/5/2022
 */
public class OpenChestAction extends Action {

    /**
     * The Actor that is to be talked to
     */
    protected Chest target;

    /**
     * The location of the chest
     */
    protected Location location;

    /**
     * Constructor for the OpenChestAction
     *
     * @param target is the actor that is meant to be talked to
     */
    public OpenChestAction(Chest target, Location location) {
        this.target = target;
        this.location = location;
    }

    /** Is the method that is meant to execute the open chest action
     *
     * This method opens a target chest
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String statement which carries a message to the actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String output = this.target.dropItems(this.location);
        location.setGround(new Floor());
        return output;

    }

    /** This method provides the description to be printed out on the menu when the time comes
     *
     * @param actor The actor performing the action.
     * @return a String description to be printed out on the menu when the time comes
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens Chest";
    }
}
