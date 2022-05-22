package game.surfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class WaterArea extends Ground {
    /**
     * Constructor.
     */
    public WaterArea() {
        super('~');
        this.addCapability(Status.DROWNABLE);
    }

    /**
     * if actor can enter/ move into
     * @param actor the Actor to check
     * @return true false
     */

    @Override
    public boolean canActorEnter(Actor actor) {
        boolean flag = false;
        if(actor.hasCapability(Status.SWIMMABLE_ENEMY) || actor.hasCapability(Status.DROWNABLE) || actor.hasCapability(Status.AMPHIBIOUS))
            flag = true;
        return flag;
    }

    @Override
    public void tick(Location location) {
        if(location.containsAnActor()){
            if(location.getActor().hasCapability(Status.DROWNABLE) && !location.getActor().hasCapability(Status.AMPHIBIOUS)){
                location.getActor().hurt(5);
            }
        }
    }
}
