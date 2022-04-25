package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class SuicideBehaviour implements Behaviour{

    private final Actor target;


    /**
     * Constructor.
     */
    public SuicideBehaviour(Actor subject) {
        this.target = subject;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Random rand = new Random();
        if (rand.nextInt(10) == 0) {
            actor.hurt(1000); //test with max hp next
        }

        return null;
    }

}
