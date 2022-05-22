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
 */
public class IncognitoAction extends Action {

    /**
     * Constructor
     */
    public IncognitoAction() {
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "?";
    }
}
