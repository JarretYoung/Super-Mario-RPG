package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Buffable;
import game.items.WaterStorage;

/**
 * DrinkAction class that extends abstract class Action class
 * Usage: It is used to drink from the bottle
 *
 * @author Jastej Gill
 * @version 2.0 21/5/2022
 */
public class DrinkAction extends Action {
    private WaterStorage source;
    private Actor drinkedBy;

    /** Constructor for the DrinkAction class
     *
     * @param from is the source of the water
     * @param by is the consumer of the water in the bottle
     */
    public DrinkAction(WaterStorage from, Actor by) {
        this.source = from;
        this.drinkedBy = by;
    }

    /** Method run to execute this action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String which narrates what has occurred during execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.drinkedBy = actor;
        String str = "";
        if(source.hasCapability(Status.FOUNTAIN)){
            if(actor.hasCapability(Status.BUFFABLE) && !actor.hasCapability(Status.PLAYER))
                str =  source.DrinkedFrom((Buffable) actor);
        }
        else if(actor.hasCapability(Status.BUFFABLE) && actor.hasCapability(Status.HAS_BOTTLE)){
            str = source.DrinkedFrom((Buffable) actor);
        }
        return str;
    }

    /** This method provides the description to be printed out on the menu when the time comes
     *
     * @param actor The actor performing the action.
     * @return a String description to be printed out on the menu when the time comes
     */
    @Override
    public String menuDescription(Actor actor) {
        return drinkedBy + " drinks from " + source;
    }
}
