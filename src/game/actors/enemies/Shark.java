package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class Shark extends Enemy{
    /**
     * Constructor.
     *
     */
    public Shark() {
        super("Shark", '>', 15);
    }

    /** This method is used to assign a new intrinsic weapon to the Koopa
     *
     * @return a new instance of intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomp");
    }
}
