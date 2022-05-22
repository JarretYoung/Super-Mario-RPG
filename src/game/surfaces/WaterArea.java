package game.surfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Water Area. Contains Shark and Squid Enenmies that follows and attacks player.
 * Player that does not have Snorkel will deal damage.
 *
 * @author Jastej Gill
 * @version 2.0 19/5/2022
 */
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

    /**
     * Called once per turn
     * Damages Player if player doesn't have Snorkel to simulate drowning
     */
    @Override
    public void tick(Location location) {
        if(location.containsAnActor()){
            if(location.getActor().hasCapability(Status.DROWNABLE) && !location.getActor().hasCapability(Status.AMPHIBIOUS)){
                location.getActor().hurt(5);
                for(int i = 0; i<location.getItems().size(); i++){
                    if(!location.getItems().get(i).hasCapability(Status.INK_ATTACK)){
                        location.getActor().removeCapability(Status.INK_ATTACK);
                    }
                }
            }
        }
    }
}
