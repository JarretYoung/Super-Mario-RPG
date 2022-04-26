package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Monologue;
import game.Status;

import java.util.Random;

/**
 * Special Action for attacking other Actors.
 */
public class TalkAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * Constructor.
     */
    public TalkAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {


        Monologue monologue = new Monologue();
        String result = monologue.speak(target);

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks to " + target;
    }
}
