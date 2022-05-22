package game.actors.enemies;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.reset.Resettable;

/**
 * Default Koopa is an ordinary Koopa.
 *
 *  @author Garret Yong Shern Min
 *  @version 1.0 18/5/2022
 */
public class DefaultKoopa extends Koopa implements Resettable {

    /**
     * Constructor.
     */
    public DefaultKoopa() {
        super("Koopa", 'K', 20);
        this.setHitPoints_active(100); //This the Koopa's hp when it is in an active state
    }
}
