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
/** This class dictates the behaviour of an Enemy to be drawn towards a fountain to gain its buffs
 *
 * @author Jastej Gill
 * @version 1.0 18/5/2022
 *
 */
public class DrinkBehaviour implements Behaviour{

    /**
     * Constructor.
     *
     */
    public DrinkBehaviour() {
    }


    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (map.locationOf(actor) != null) {
            // If actor is on fountain drink
            if (map.locationOf(actor).getGround().hasCapability(Status.FOUNTAIN)) {
                return new DrinkAction((WaterStorage) map.locationOf(actor).getGround(), actor);
            } else {
                for (Exit exit : map.locationOf(actor).getExits()) {
                    Location destination = exit.getDestination();
                    // If exit has fountain move towards fountain
                    if (destination.canActorEnter(actor) && destination.getGround().hasCapability(Status.FOUNTAIN)) {
                        return new MoveActorAction(destination, exit.getName());
                    }
                }
            }
        }
        return null;
    }


}
