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

    @Override
    public String execute(Actor actor, GameMap map) {

        String result;

        if ( actor.hasCapability(Status.FINAL_BOSS_CLEARED) ) {
            result = "You have defeated Bowser and saved Princess Peach";
            result += System.lineSeparator() + "Congratulations " + actor + ", you saved the Mushroom Kingdom";
            result += System.lineSeparator() + "And most importantly, thank YOU player, for playing";

            actor.addCapability(Status.GAME_COMPLETE);
        } else {
            result = "Ayy, you have not completed the game, what bug did you find?";
        }


        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks to " + usher;
    }
}
