package game.items.water;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Buffable;

public class PowerWater extends MagicalWater{
    /***
     * Constructor.
     */
    public PowerWater() {
        super("Power Water");
    }

    @Override
    public String drinked(Buffable by) {
        Buffable actor = by;
        String result = actor + " has increased intrinsic damage by 15";
        int damage = actor.getDamage();
        actor.setDamage(damage + 15);
        return result;
    }
}
