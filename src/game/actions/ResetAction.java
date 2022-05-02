package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

/**
 * Special Action for Resetting the map.
 *
 * @author Garret Yong Shern Min
 */
public class ResetAction extends Action {

    /**
     * Constructor for ResetAction
     */
    public ResetAction() {}


    @Override
    public String execute(Actor actor, GameMap map) {

        ResetManager itemsToBeReset = ResetManager.getInstance();

        itemsToBeReset.run();

        String result = "Map has been reset";
        return result;
    }

    @Override
    public String hotkey() {
        return "r";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " reset the map";
    }
}

