package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/** This class represents the suicidal tendency that can be incorporated into an Actor to cause random instant death
 *
 * @author Garret Yong Shern Min
 * @version 1.0 18/5/2022
 */
public class SuicideBehaviour implements Behaviour{

    /**
     * Target about to suicide
     */
    private Actor target;

    /**
     * Chances for the target to suicide ( 1 / chance )
     */
    private int chance;


    /** Constructor for suicide behaviour
     *
     * @param subject is the target about to suicide
     * @param chances is the chance, 1 / chance , for the target to suicide
     */
    public SuicideBehaviour(Actor subject, int chances) {
        this.target = subject;
        this.chance = chances;
    }

    /** This is the method to return the action borne from the Behaviour
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Random rand = new Random();
        if (rand.nextInt(this.chance) == 0) {
            actor.hurt(1000);
        }

        if (!target.isConscious()) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
        }

        return null;
    }

}
