package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.DrinkAction;
import game.items.WaterStorage;

public class DrinkBehaviour implements Behaviour{
    /**
     * Constructor.
     *
     */
    public DrinkBehaviour() {
    }
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for(Exit exit : map.locationOf(actor).getExits()){
            if(exit.getDestination().getGround().hasCapability(Status.FOUNTAIN)) {
                return new DrinkAction((WaterStorage) map.locationOf(actor).getGround());
            }
            for(Exit exitOfExit : exit.getDestination().getExits()){
                if(exitOfExit.getDestination().getGround().hasCapability(Status.FOUNTAIN)) {
                    return new MoveActorAction(exit.getDestination(), exit.getName());
                }
            }
        }
        return null;
    }

}
