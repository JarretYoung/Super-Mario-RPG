package game.items.water;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Buffable;
import game.items.ConsumableItem;
import game.items.SpecialItem;

public abstract class MagicalWater extends ConsumableItem {
    /***
     * Constructor.
     * @param name the name of this Item
     */
    public MagicalWater(String name) {
        super(name, '`', false, true);
    }

    @Override
    public abstract String drinked(Buffable by);
}
