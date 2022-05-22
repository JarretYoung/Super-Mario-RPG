package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An Action that doesn't do anything. (but does not print anything)
 *
 * This class is created mainly to conceal the actions and presence of an Actor
 *
 * Use this to implement hiding or similar actions in game clients.
 *
 * @author Garret Yong Shern Min
 * @version 1.0 21/5/2022
 */
public class IncognitoAction extends Action {

    /**
     * Constructor
     */
    public IncognitoAction() {
    }

    /** Method run to execute this action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String which narrates what has occurred during execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /** This method provides the description to be printed out on the menu when the time comes
     *
     * @param actor The actor performing the action.
     * @return a String description to be printed out on the menu when the time comes
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Something is watching you";
    }
}
