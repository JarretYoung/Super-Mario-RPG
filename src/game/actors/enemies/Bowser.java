package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.items.SuperMushroom;
import game.reset.Resettable;

public class Bowser extends Boss implements Resettable {

    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 200);
//        this.addItemToInventory(); add the scissors

    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punch");
    }
}
