package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

/**
 * Special Action for Resetting the map.
 *
 * @author Garret Yong Shern Min
 * @version 1.0 21/4/2022
 */
public class ResetAction extends Action {

    /**
     * Constructor for ResetAction
     */
    public ResetAction() {}

    /** This method represents the execution for the ResetAction
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string output detailing the proceedings of this action
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        ResetManager itemsToBeReset = ResetManager.getInstance();

        itemsToBeReset.run();

        String result = "Map has been reset";
        return result;
    }

    /** This method is used to assign a unique hotkey for this action
     *
     * @return a character that will be used as a hotkey for this action
     */
    @Override
    public String hotkey() {
        return "r";
    }

    /** The menu description for this action
     *
     * @param actor The actor performing the action.
     * @return a string containing the menu description for this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " reset the map";
    }
}

