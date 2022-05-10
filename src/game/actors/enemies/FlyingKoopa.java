package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.items.SuperMushroom;
import game.reset.Resettable;

public class FlyingKoopa extends Enemy implements Resettable {

    /**
     * Health (while active)
     */
    private int hitPoints_active;
    /**
     * Constructor.
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 20);
        this.hitPoints_active = 150; //This the Koopa's hp when it is in an active state
        this.addCapability(Status.ACTIVE);
        this.addItemToInventory(new SuperMushroom());
        this.addCapability(Status.CAN_JUMP_ONTO_HIGH_GROUND);
    }

    /** This method is used to assign a new intrinsic weapon to the FlyingKoopa
     *
     * @return a new instance of intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punch");
    }
}
