package game.actors.enemies;


import game.Status;
import game.reset.Resettable;

/**
 * Flying Koopa are like can go onto High Grounds
 *
 * @author Garret Yong Shern Min
 * @version 1.0 18/5/2022
 */
public class FlyingKoopa extends Koopa implements Resettable {

    /**
     * Constructor.
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 20);
        this.setHitPoints_active(150); //This the Koopa's hp when it is in an active state
        this.addCapability(Status.CAN_JUMP_ONTO_HIGH_GROUND);
    }

}
