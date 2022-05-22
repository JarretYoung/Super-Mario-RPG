package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.behaviours.AttackBehaviour;
import game.items.Fire;
import game.items.Ink;
import game.reset.Resettable;

/**
 * Squid class that extends abstract class Enemy class
 *
 * This class is a mini boss which resides in the water
 *
 * @author Jastej Gill
 * @version 1.0 17/5/2022
 */
public class Squid extends Enemy{
    /**
     * Constructor.
     *
     */
    public Squid() {
        super("Squid", '&', 150);
        this.addCapability(Status.SWIMMABLE_ENEMY);
        this.getBehaviour().put(9, new AttackBehaviour(new Ink(20, 5)));
    }

    /** This method is used to assign a new intrinsic weapon to the Squid
     *
     * @return a new instance of intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomp");
    }
}
