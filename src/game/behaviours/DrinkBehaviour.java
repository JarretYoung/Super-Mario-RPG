package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;
import game.actions.DrinkAction;
import game.items.WaterStorage;

import java.util.ArrayList;

public class DrinkBehaviour implements Behaviour{

    /**
     * Constructor.
     *
     */
    public DrinkBehaviour() {
    }
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();
        if (actor.isConscious()) {
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor) && destination.getGround().hasCapability(Status.FOUNTAIN)) {
                   return new DrinkAction((WaterStorage) destination.getGround());
                }
            }
        }
        return null;
    }

}
