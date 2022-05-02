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


public class SuicideBehaviour implements Behaviour{

    private Actor target;

    private int chance;


    /**
     * Constructor.
     */
    public SuicideBehaviour(Actor subject, int chances) {
        this.target = subject;
        this.chance = chances;
    }

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
