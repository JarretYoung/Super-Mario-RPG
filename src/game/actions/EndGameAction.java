package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.Crippleable;

import java.util.Random;

/**
 * EndGameAction class that extends abstract class Action class
 * Usage: Is used to end the game given certain requirements
 *
 * @author Garret Yong Shern Min
 * @version 1.0 16/5/2022
 */
public class EndGameAction extends Action {

    /**
     * The Actor that is to usher the end of the game
     */
    protected Actor usher;

    /**
     * Constructor.
     *
     * @param usher is the actor who will usher in the end of the game
     */
    public EndGameAction(Actor usher) {
        this.usher = usher;
    }

    /** Method run to execute this action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String which narrates what has occurred during execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result;

        if ( actor.hasCapability(Status.FINAL_BOSS_CLEARED) ) {
            result = "You have defeated Bowser and saved Princess Peach. :D";
            result += System.lineSeparator() + "Congratulations " + actor + ", you saved the Mushroom Kingdom! ";
            result += System.lineSeparator() + "And most importantly, thank YOU player, for playing! <3";

            actor.addCapability(Status.GAME_COMPLETE);
        } else {
            result = "Ayy, you have not completed the game, what bug did you find?";
        }

        map.removeActor(actor);
        return result;
    }

    /** This method provides the description to be printed out on the menu when the time comes
     *
     * @param actor The actor performing the action.
     * @return a String description to be printed out on the menu when the time comes
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlock and frees " + usher + "!";
    }
}
