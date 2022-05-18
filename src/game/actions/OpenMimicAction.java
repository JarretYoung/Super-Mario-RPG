package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.enemies.Mimic;

import java.util.Random;

public class OpenMimicAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Mimic target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction is the direction that the target is at
     */
    public OpenMimicAction(Mimic target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        this.target.removeCapability(Status.DORMANT);
        this.target.addCapability((Status.ACTIVE));
        actor.hurt(30);
        return actor + " has awoken a mimic!";
    }

    @Override
    public String menuDescription(Actor actor) {
        int randomInt = rand.nextInt(3);
        if (randomInt == 0) {
            return actor + " opens (hest";
        } else if (randomInt == 1) {
            return actor + " opens Ches7";
        } else if (randomInt == 2) {
            return actor + " opens Che5t";
        }
        return null;

    }
}
