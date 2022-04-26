package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

public class Wrench extends WeaponItem implements TradeableItem{
    private int value;
    /**
     * Constructor.
     *
     */
    public Wrench() {

        super("wrench", '{', 50, "hits", 80);
        this.addCapability(Status.HAS_WRENCH);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String traded(Actor actor) {
        return null;
    }
}
